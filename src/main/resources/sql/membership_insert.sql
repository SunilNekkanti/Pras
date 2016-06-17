drop table if exists temp_membership ;
create temporary table temp_membership  as
select  substring_index(c2m.Membername,',',1) as lastname,substring_index(c2m.Membername,',',-1) as firstname,
lg.gender_id sex, lc.code county,
c2m.dob,
case when c2m.status = 'ENR' then 1
     when c2m.status = 'DIS' then 3
     else  2 
	end as status,
c2m.MCDMCR,
:fileId fileid,
now() created_date,
now() updated_date,
'sarath' created_by,
'sarath' updated_by,
min(MEMBEREFFDT) MEMBEREFFDT
  from  csv2table_amg_roster c2m
join lu_gender lg on lg.code = c2m.sex
left outer join lu_county  lc on lc.description = c2m.county
left outer join lu_county_zip  lcz on  lcz.countycode = lc.code and lcz.zipcode = c2m.zip
group by MCDMCR;



insert ignore into membership (  Mbr_LastName,Mbr_FirstName,Mbr_GenderID,Mbr_CountyCode,Mbr_DOB,Mbr_Status,Mbr_MedicaidNo,file_id,created_date,updated_date,created_by,updated_by)
select lastname,firstname, sex,  county, DATE_FORMAT(str_to_date(dob , '%c/%e/%Y %H:%i'),'%Y-%c-%e'),
status,MCDMCR, :fileId fileid,tm.created_date,tm.updated_date,tm.created_by,tm.updated_by   from  temp_membership tm
LEFT OUTER JOIN membership m on m.Mbr_MedicaidNo = tm.MCDMCR
where m.Mbr_MedicaidNo is null;

update membership_insurance mi
join csv2table_amg_roster  b on  b.SBSB_ID=mi.SRC_SYS_MBR_NBR
set  mi.New_Medicare_Bene_Medicaid_Flag = 
case when b.Status ='ENR' then 'Y' else 'N' end ,
mi.effective_strt_dt =  cast( str_to_date(b.MEMBEREFFDT, '%m/%d/%y') as date) ,
effecctive_end_dt= 
case when cast(str_to_date( b.MEMBERTERMDT, '%m/%d/%y')  as date) = cast( str_to_date('1999-12-31', '%Y-%m-%d') as date) then
     cast(str_to_date('2099-12-31', '%Y-%m-%d')as date)
    else cast(str_to_date( b.MEMBERTERMDT, '%m/%d/%y')  as date) end    ,
mi.product = b.PRODUCT,
mi.product_label= b.PRODUCT,
mi.planID = b.PLAN
where mi.SRC_SYS_MBR_NBR is not null;

insert into membership_insurance 
(
ins_id,mbr_id,New_Medicare_Bene_Medicaid_Flag,activitydate,activityMonth,effective_strt_dt,effecctive_end_dt,
product,product_label,planID,SRC_SYS_MBR_NBR,risk_flag,created_date,updated_date,created_by,updated_by)
select 
:insId ins_id,
a.mbr_id,
case when a.Mbr_Status =1 then 'Y' else 'N' end as new_benefits,
CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE) activitydate,
DATE_FORMAT(NOW() ,'%m%y')  activityMonth,
cast( str_to_date(b.MEMBEREFFDT, '%m/%d/%y') as date) effective_strt_dt,
case when cast(str_to_date( b.MEMBERTERMDT, '%m/%d/%y')  as date) = cast( str_to_date('1999-12-31', '%Y-%m-%d') as date) then
     cast(str_to_date('2099-12-31', '%Y-%m-%d')as date)
    else cast(str_to_date( b.MEMBERTERMDT, '%m/%d/%y')  as date) end    effective_end_dt,
b.PRODUCT,
b.PRODUCT,
b.PLAN,
b.SBSB_ID,
'N' risk_flag,
now() created_date,
now() updated_date,
'sarath' created_by,
'sarath' updated_by
 from csv2table_amg_roster  b  
  join membership   a on   a.mbr_medicaidNo=b.MCDMCR  
  left outer join membership_insurance mi on mi.mbr_id=a.mbr_id and mi.SRC_SYS_MBR_NBR=b.SBSB_ID
  where   mi.mbr_id is  null 
 group by a.mbr_id,effective_strt_dt,effective_end_dt, b.PRODUCT,b.PRODUCT,b.PLAN;
 


update  membership_insurance set active_ind='N' where effecctive_end_dt < cast(now() as date);

update  membership_provider mp 
join (
 select mp.mbr_id,mp.prvdr_id, DATE_FORMAT(str_to_date(( b.PROVEFFDT) , '%c/%e/%Y %H:%i'),'%Y-%c-%e') eff_start_date,
 DATE_FORMAT(str_to_date(b.PROVTERMDT, '%c/%e/%Y %H:%i'),'%Y-%c-%e') eff_end_date from  membership_provider mp 
join  membership_insurance mi on  mp.mbr_id = mi.mbr_id 
join  csv2table_amg_roster  b on convert( b.SBSB_ID , unsigned integer) =mi.SRC_SYS_MBR_NBR
join  provider   d  on convert(  b.PRPRNPI,   unsigned integer)  = CONVERT( d.code , unsigned integer) and d.prvdr_id = mp.prvdr_id    
where 	  b.PROVTERMDT is not null and  b.PROVTERMDT != ''
group by mp.mbr_id,mp.prvdr_id,DATE_FORMAT(str_to_date(( b.PROVEFFDT) , '%c/%e/%Y %H:%i'),'%Y-%c-%e')
) a on mp.mbr_id = a.mbr_id and mp.prvdr_id= a.prvdr_id and mp.eff_start_date =a.eff_start_date
set  mp.eff_end_date = a.eff_end_date ;
 
insert into membership_provider (mbr_id,prvdr_id,file_id,eff_start_date,eff_end_date,created_date,updated_date,created_by,updated_by) 
select 
  mi.mbr_id, 
 d.prvdr_id,
:fileId file_id,
DATE_FORMAT(str_to_date(( b.PROVEFFDT) , '%c/%e/%Y %H:%i'),'%Y-%c-%e') eff_start_date,
case when b.PROVTERMDT is null then null
	 when b.PROVTERMDT = '' then null
     else  DATE_FORMAT(str_to_date(b.PROVTERMDT, '%c/%e/%Y %H:%i'),'%Y-%c-%e')
     end eff_end_date,
 now() created_date,
 now() updated_date,
 'sarath' created_by,
'sarath' updated_by
 from csv2table_amg_roster  b
 join  provider   d  on  CONVERT( d.code , unsigned integer) =convert(  b.PRPRNPI,   unsigned integer) 
 join membership_insurance mi on mi.SRC_SYS_MBR_NBR = convert( b.SBSB_ID , unsigned integer) 
 join  membership   m on  m.mbr_id=mi.mbr_id 
 left outer join membership_provider mp on mp.prvdr_id= d.prvdr_id and mp.mbr_id=m.mbr_id 
 where case when mp.mbr_id is not null then mp.prvdr_id is null  else 1=1 end 
group by b.MCDMCR,b.PRPRNPI,DATE_FORMAT(str_to_date(( b.PROVEFFDT) , '%c/%e/%Y %H:%i'),'%Y-%c-%e'); 

update  membership_provider set active_ind='N' where eff_end_date is not null;


 
insert into membership_activity_month (mbr_id,ins_id,prvdr_id,activity_month,file_id,created_date,updated_date,created_by,updated_by) 
select 
mi.mbr_id,
mi.ins_id,
mp.prvdr_id,
:activityMonth  activityMonth, 
:fileId fileId,
now() created_date,
now() updated_date,
'sarath' created_by,
'sarath' updated_by 
 from csv2table_amg_roster  b     
  join membership_insurance mi on  mi.SRC_SYS_MBR_NBR=b.SBSB_ID
  join membership_provider mp on  mp.mbr_id=mi.mbr_id
  join provider p  on   CONVERT( p.code , unsigned integer) =convert(  b.PRPRNPI,   unsigned integer)  and p.prvdr_id=mp.prvdr_id
  
left outer join membership_activity_month mam on mam.mbr_id= mi.mbr_id and mam.ins_id=mi.ins_id and mam.prvdr_id=mp.prvdr_id and mam.activity_Month=DATE_FORMAT(NOW() ,'%Y%m')
where mam.activity_Month is null  and mp.active_ind='Y' and mi.active_ind='Y'
 group by mi.mbr_id,mi.ins_id,mp.prvdr_id,activityMonth  ;
 
 

insert ignore into reference_contact (mbr_id, created_by,updated_by) 
select m.Mbr_Id , 'sarath','sarath' from membership m
left outer join reference_contact rc on rc.mbr_id =m.mbr_id
where rc.mbr_id is null ;


insert ignore into contact (ref_cnt_id,home_phone,mobile_phone,address1,address2,city,zipcode,statecode,file_id,created_Date,updated_date,created_by,updated_by)
select
c.ref_cnt_id,
a.phone,
a.phone,
a.address1,
a.address2,
a.city,
a.zip,
d.statecode,
:fileId fileId ,
now() created_date,
now() updated_date,
'sarath',
'sarath'
from csv2table_amg_roster a
join membership b on  b.Mbr_MedicaidNo = a.mcdmcr
join reference_contact c on  c.mbr_id = b.mbr_id  
join lu_state_zip d on  d.zipcode = a.zip
join lu_state e on e.shot_name =  a.state and  e.code=d.statecode
left outer join contact cnt on cnt.ref_cnt_id = c.ref_cnt_id
where cnt.ref_cnt_id is null
group by b.Mbr_id;
 drop table if exists temp_membership  ;
