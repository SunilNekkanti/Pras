 drop table if exists hedisMeasureByICD;
 drop table if exists hedisMeasureByCPT;
  
 create temporary table hedisMeasureByCPT as
SELECT a.mbr_id,a.hedis_msr_rule_id, a.duedate
 FROM 
 (
 SELECT   
 mi.mbr_id,  hmr.hedis_msr_rule_id ,   
 DATE_ADD(m.mbr_dob, INTERVAL ceil(365.25 * ceil (datediff( current_date(),m.mbr_dob)/365.25))  DAY) duedate,
 null date_of_service,
 'Y' follow_up_ind,
 case when hmr.dose_count is null then 1 else hmr.dose_count end dosecount,  
 mbrClaimCPT.cpt_id claimCPT_ID, 
 hcptm.cpt_id ruleCPT_ID, 
 cpt.code,
 hmr.cpt_or_icd ,
 mprvdr.prvdr_id prvdrid

 FROM  hedis_measure_rule     hmr
 join  membership_insurance mi  on mi.ins_id = hmr.ins_id and hmr.ins_id= :insId
 join membership m on mi.mbr_id =m.mbr_id  and 
    case when hmr.lower_age_limit is not null then datediff( current_date(),m.mbr_dob)/365.25 >=  hmr.lower_age_limit else 1= 1 end and
        case when hmr.upper_age_limit is not null then datediff( current_date(),m.mbr_dob)/365.25 < hmr.upper_age_limit else 1=1 end and
        case when hmr.gender_id is not null and hmr.gender_id != 3 and  hmr.gender_id != 4 then hmr.gender_id = m.mbr_genderid else 1=1 end
 join membership_provider mprvdr  on  mi.mbr_id=mprvdr.mbr_id
 join hedis_cpt_measure hcptm on hcptm.hedis_msr_rule_Id =  hmr.hedis_msr_rule_Id  
  JOIN cpt_measure  cpt on    hcptm.cpt_id =cpt.cpt_id  
  left outer join lu_frequency_type lft on lft.code= hmr.frequency_type_code
 left OUTER join membership_claims mc on mc.mbr_id= mi.mbr_id and mc.ins_id=mi.ins_id 
 left OUTER join membership_claim_details mcd on mcd.mbr_claim_id = mc.mbr_claim_id  and  case when lft.noOfDays is not null then (current_date - mcd.activity_date) <= lft.noOfDays else  (current_date - mcd.activity_date) <=365 end
  LEFT OUTER JOIN cpt_measure  mbrClaimCPT on mbrClaimCPT.cpt_id = mcd.cpt_code  and mbrClaimCPT.cpt_id=cpt.cpt_id 
    LEFT outer Join membership_problems mp on mp.pbm_id =hmr.problem_id and mp.mbr_id=mi.mbr_id  and  mcd.claim_start_date >= mp.start_date
      where  hmr.effective_year= 2016 and
        case when  hmr.problem_flag = 'Y' then   hmr.problem_id is not null and mp.pbm_id is not null else 1=1 end  and
         hmr.cpt_or_icd in (0,2)      and 
  hmr.active_ind='Y'   and mprvdr.active_ind='Y' and mi.active_ind='Y'  
  group by mi.mbr_id, hmr.hedis_msr_rule_id
having      count( mbrClaimCPT.cpt_id) 	     < dosecount
) a ;

alter table hedisMeasureByCPT add key  mbr_id(mbr_id) , add key hedis_msr_rule_id(hedis_msr_rule_id);


  create temporary table hedisMeasureByICD  as  
SELECT a.mbr_id,a.hedis_msr_rule_id, a.duedate
 FROM 
 (
 SELECT   
 mi.mbr_id,  hmr.hedis_msr_rule_id ,   
 DATE_ADD(m.mbr_dob, INTERVAL ceil(365.25 * ceil (datediff( current_date(),m.mbr_dob)/365.25))  DAY) duedate,
 null date_of_service,
 'Y' follow_up_ind,
 case when hmr.dose_count is null then 1 else hmr.dose_count end dosecount,  
 hicdm.icd_id ruleICD_ID,
MbrClaimICD.icd_id  claimICD_ID,
 hmr.cpt_or_icd ,
 mprvdr.prvdr_id prvdrid

 FROM  hedis_measure_rule     hmr
 join  membership_insurance mi  on mi.ins_id = hmr.ins_id and hmr.ins_id= :insId
 join membership m on mi.mbr_id =m.mbr_id  and 
    case when hmr.lower_age_limit is not null then datediff( current_date(),m.mbr_dob)/365.25 >=  hmr.lower_age_limit else 1= 1 end and
        case when hmr.upper_age_limit is not null then datediff( current_date(),m.mbr_dob)/365.25 < hmr.upper_age_limit else 1=1 end and
  case when hmr.gender_id is not null and hmr.gender_id != 3 and  hmr.gender_id != 4 then hmr.gender_id = m.mbr_genderid else 1=1 end
 join membership_provider mprvdr  on  mi.mbr_id=mprvdr.mbr_id
  join hedis_icd_measure hicdm on   hicdm.hedis_msr_rule_Id =  hmr.hedis_msr_rule_Id 
  JOIN icd_measure  icd  on  icd.icd_id   = hicdm.icd_id  
    left outer join lu_frequency_type lft on lft.code= hmr.frequency_type_code
 left OUTER join membership_claims mc on mc.mbr_id= mi.mbr_id and mc.ins_id=mi.ins_id 
 left OUTER join membership_claim_details mcd on mcd.mbr_claim_id = mc.mbr_claim_id and  case when lft.noOfDays is not null then (current_date - mcd.activity_date) <= lft.noOfDays else (current_date - mcd.activity_date) <=365 end
    LEFT outer join  icd_measure MbrClaimICD on  REPLACE(mc.Diagnoses, ".","") LIKE CONCAT('%', REPLACE(icd.code, ".",""),'%')   and icd.icd_id =MbrClaimICD.icd_id
  LEFT outer Join membership_problems mp on mp.pbm_id =hmr.problem_id and mp.mbr_id=mi.mbr_id  and  mcd.claim_start_date >= mp.start_date
  where hmr.effective_year= 2016 and
        case when  hmr.problem_flag = 'Y' then   hmr.problem_id is not null and mp.pbm_id is not null else 1=1 end  and
        hmr.cpt_or_icd in(1,2)   and hmr.active_ind='Y'   and mprvdr.active_ind='Y' and mi.active_ind='Y'  
  group by mi.mbr_id, hmr.hedis_msr_rule_id
having       count( MbrClaimICD.icd_id)     < dosecount
) a ;

alter table hedisMeasureByICD add key  mbr_id(mbr_id) , add key hedis_msr_rule_id(hedis_msr_rule_id);

INSERT INTO membership_hedis_measure (
mbr_id,hedis_msr_rule_id,due_date,date_of_service,follow_up_ind,created_date,updated_date,created_by,updated_by,active_ind,file_id
 ) 
select 
 a.mbr_id,  a.hedis_msr_rule_id ,   a.duedate
, null date_of_service, 'Y' follow_up_ind, now() created_date,now() updated_date,'sarath' created_by, 'sarath' updated_by, 'Y'active_ind, :fileId file_id
 from 
 (select * from hedisMeasureByCPT 
 union 
 select * from hedisMeasureByICD) a 
 left outer join membership_hedis_measure mhm  on a.mbr_id=mhm.mbr_id and a.hedis_msr_rule_id=mhm.hedis_msr_rule_id
 where   case when mhm.mbr_id is not null then  mhm.hedis_msr_rule_id is null else  mhm.mbr_id is  null end;
