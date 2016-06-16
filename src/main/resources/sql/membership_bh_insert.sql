drop table if exists temp_bh_membership ;
create temporary table temp_bh_membership  
select pcpgroup , PcpName , pcpaddress1 , 
SUBSTRING_INDEX(SUBSTRING_INDEX(Pay2Mail, ',', 1), ',', -1) pcpcity, 
SUBSTRING_INDEX(SUBSTRING_INDEX(Pay2Mail, ' ', -2), ' ', 1) pcpstate, 
SUBSTRING_INDEX(SUBSTRING_INDEX(Pay2Mail, ' ', -1), ' ', -1) pcpzipcode, status , pcpStatus , lastname ,
MCDMCR , sex , dob ,   effstartdate memeffstartdate ,
case when status = 'Termed Membership' then  effenddate 
     else cast(str_to_date('2099-12-31', '%Y-%m-%d')as date) end  memeffenddate,
case when pcpStatus = 'PCP EFF' then effenddate else effstartdate end  pcpstartdate,
case when pcpStatus = 'PCP Term' then effenddate end pcpenddate, 
 phone , MemberCounty , firstname , REPLACE(upper(SUBSTRING(address1, 1, LOCATE(SUBSTRING_INDEX(address1, ' ', -3),address1)-2)), 'TEMPLE', '') address1, REPLACE(upper(SUBSTRING_INDEX(SUBSTRING_INDEX(address1, ' ', -3), ' ', 1)), 'TERRACE', 'TEMPLE TERRACE') city, SUBSTRING_INDEX(SUBSTRING_INDEX(address1, ' ', -2), ' ', 1) state, 
 SUBSTRING(  SUBSTRING_INDEX(SUBSTRING_INDEX(address1, ' ', -1), ' ', -1), 1,5) zipcode from csv2table_bh_roster;


insert ignore into membership (  Mbr_LastName,Mbr_FirstName,Mbr_GenderID,Mbr_CountyCode,Mbr_DOB,Mbr_Status,Mbr_MedicaidNo,file_id,created_date,updated_date,created_by,updated_by)
 select lastname,firstname ,lg.gender_id,lc.code,DATE_FORMAT(str_to_Date(dob,'%c/%e/%Y %H:%i'),'%Y-%c-%e'),
 case when trim(a.status)='Current Membership' then 2
      when trim(a.status)='New Membership' then 1
      when trim(a.status)='Termed Membership' then 3 end status,
      MCDMCR,:fileId fileId
      now() created_date,
      now() updated_date,'sarath' created_by,'sarath' updated_by 
 from temp_bh_membership a
 join lu_gender lg on lg.code=a.sex
 left outer join lu_county lc on ucase(trim(lc.description)) = ucase(trim(a.membercounty))
 left outer join lu_county_zip lcz on lcz.countycode=lc.code and lcz.zipcode=a.zipcode
 left outer join membership m on m.Mbr_MedicaidNo=a.MCDMCR
 where m.mbr_id is null  
 group by MCDMCR
 having max(memeffenddate) ;

update membership_insurance mi
 join membership m on mi.mbr_id= m.mbr_id
join temp_bh_membership  b on  b.mcdmcr =m.Mbr_MedicaidNo
set  mi.New_Medicare_Bene_Medicaid_Flag = 
case when b.Status ='New Membership' then 'Y' else 'N' end ,
 mi.effecctive_end_dt =  cast( str_to_date(b.memeffenddate, '%c/%e/%Y') as date) 
where m.Mbr_MedicaidNo is not null;


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
cast( str_to_date(b.memeffstartdate, '%c/%e/%Y') as date) effective_strt_dt,
  cast( str_to_date(b.memeffenddate, '%c/%e/%Y') as date)
       endeffective_end_dt,
null PRODUCT,
null PRODUCT,
null PLAN,
null SBSB_ID,
'N' risk_flag,
now() created_date,
now() updated_date,
'sarath' created_by,
'sarath' updated_by
  from temp_bh_membership  b  
  join membership   a on   a.mbr_medicaidNo=b.MCDMCR  
  left outer join membership_insurance mi on mi.mbr_id=a.mbr_id  
  where   mi.mbr_id is  null 
 group by a.mbr_id, effective_strt_dt, PRODUCT, PLAN;


update  membership_insurance set active_ind='N' where effecctive_end_dt < cast(now() as date);

update  membership_provider mp 
join (
	 select
 p.prvdr_id,
 m.mbr_id    ,  date_format(str_to_date(b.pcpstartdate,'%c/%e/%Y %H:%i'), '%Y-%c-%e') eff_start_date
    ,   DATE_FORMAT(str_to_date(b.pcpenddate,'%c/%e/%Y %H:%i'),'%Y-%c-%e') eff_end_date    
  from  membership m  
  join temp_bh_membership b on convert(b.mcdmcr, unsigned integer) =m.mbr_medicaidNo
  join provider p on ucase(p.name) LIKE CONCAT('%',TRIM(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(ucase(PcpName), ',', 2), ',', 1),'.','')),'%') 
         and  ucase(p.name) LIKE CONCAT('%',TRIM(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(ucase(PcpName), ',', 2), ',', -1),'.','')),'%')   
where 	  b.pcpenddate is not null and  b.pcpenddate != ''
group by m.mbr_id,p.prvdr_id,DATE_FORMAT(str_to_date(( b.pcpstartdate) , '%c/%e/%Y %H:%i'),'%Y-%c-%e')
) a on mp.mbr_id = a.mbr_id and mp.prvdr_id= a.prvdr_id and mp.eff_start_date =a.eff_start_date
set  mp.eff_end_date = a.eff_end_date ;


insert into membership_provider (mbr_id,prvdr_id,file_id,eff_start_date,eff_end_date,created_date,updated_date,created_by,updated_by)
select
 m.mbr_id, 
 p.prvdr_id,
 :fileId file_id,
 DATE_FORMAT(str_to_date(b.pcpstartdate,'%c/%e/%Y %H:%i'), '%Y-%c-%e') eff_start_date,
 DATE_FORMAT(str_to_date(b.pcpenddate,'%c/%e/%Y %H:%i'),'%Y-%c-%e') eff_end_date,
 now() created_date,
 now() updated_date,
 'sarath' created_by,
'sarath' updated_by
  from  membership m  
  join temp_bh_membership b on convert(b.mcdmcr, unsigned integer) =m.mbr_medicaidNo
  join provider p on ucase(p.name) LIKE CONCAT('%',TRIM(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(ucase(PcpName), ',', 2), ',', 1),'.','')),'%') 
         and  ucase(p.name) LIKE CONCAT('%',TRIM(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(ucase(PcpName), ',', 2), ',', -1),'.','')),'%')  
  left outer join  membership_provider mp  on mp.prvdr_id = p.prvdr_id and mp.mbr_id =m.mbr_id
where 	case when mp.mbr_id is not null  then mp.prvdr_id is null else mp.mbr_id is   null end
group by m.mbr_id,p.prvdr_id,DATE_FORMAT(str_to_date(( b.pcpstartdate) , '%c/%e/%Y %H:%i'),'%Y-%c-%e')

update  membership_provider set active_ind='N' where eff_end_date is not null;

 
insert into membership_activity_month (mbr_id,ins_id,prvdr_id,activity_month,file_id,created_date,updated_date,created_by,updated_by) 
select 
mi.mbr_id,
mi.ins_id,
mp.prvdr_id,
DATE_FORMAT(NOW() ,'%Y%m')  activityMonth, 
:fileId fileId,
now() created_date,
now() updated_date,
'sarath' created_by,
'sarath' updated_by 
 from temp_bh_membership  b 
  join membership m on convert(b.mcdmcr, unsigned integer) =m.mbr_medicaidNo
  join membership_insurance mi on  mi.mbr_id=m.mbr_id
  join membership_provider mp on  mp.mbr_id=mi.mbr_id
  join provider p  on  ucase(p.name) LIKE CONCAT('%',TRIM(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(ucase(PcpName), ',', 2), ',', 1),'.','')),'%') 
		and ucase(p.name) LIKE CONCAT('%',TRIM(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(ucase(PcpName), ',', 2), ',', -1),'.','')),'%')    and p.prvdr_id=mp.prvdr_id
left outer join membership_activity_month mam on mam.mbr_id= mi.mbr_id and mam.ins_id=mi.ins_id and mam.prvdr_id=mp.prvdr_id and mam.activity_Month=DATE_FORMAT(NOW() ,'%Y%m')
where mam.activity_Month is null  and mp.active_ind='Y'
 group by mi.mbr_id,mi.ins_id,mp.prvdr_id,activityMonth  ;

 

insert ignore into reference_contact (mbr_id, created_date,updated_date,created_by,updated_by) 
select m.Mbr_Id ,now() created_date,now()updated_date, 'sarath','sarath' from membership m
left outer join reference_contact rc on rc.mbr_id =m.mbr_id
where rc.mbr_id is null ;

insert ignore into contact (ref_cnt_id,home_phone,mobile_phone,address1,address2,city,zipcode,statecode,file_id,created_Date,updated_date,created_by,updated_by)
select
c.ref_cnt_id,
a.phone,
a.phone,
a.address1,
null address2,
a.city,
a.zipcode,
d.statecode,
:fileId fileId ,
now() created_date,
now() updated_date,
'sarath',
'sarath'
from temp_bh_membership a
join membership b on  b.Mbr_MedicaidNo = a.mcdmcr
join reference_contact c on  c.mbr_id = b.mbr_id  
join lu_state_zip d on  d.zipcode = a.zipcode
join lu_state e on e.shot_name =  a.state and  e.code=d.statecode
left outer join contact cnt on cnt.ref_cnt_id = c.ref_cnt_id
where cnt.ref_cnt_id is null
group by b.Mbr_id; 

drop table if exists temp_bh_membership  ;
