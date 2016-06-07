INSERT INTO membership_hedis_measure (
mbr_id,hedis_msr_rule_id,due_date,date_of_service,follow_up_ind,created_date,updated_date,created_by,updated_by,active_ind,file_id
 )
 SELECT mbr_id, hedis_msr_rule_id, duedate, date_of_service,follow_up_ind,created_date,updated_date,created_by,updated_by,active_ind, :fileId 
 FROM 
 (
 SELECT   
 mi.mbr_id,    hmr.hedis_msr_rule_id ,   
 DATE_ADD(m.mbr_dob, INTERVAL ceil(365.25 * ceil (datediff( current_date(),m.mbr_dob)/365.25))  DAY) duedate,
 null date_of_service,
 'Y' follow_up_ind,
now() created_date, now() updated_date,'sarath' created_by ,'sarath' updated_by,'Y' active_ind,
 case when hmr.dose_count is null then 1 else hmr.dose_count end dose_count
 FROM  hedis_measure_rule     hmr
 join  membership_insurance mi  on mi.ins_id = hmr.ins_id and hmr.ins_id=2
 join membership m on mi.mbr_id =m.mbr_id  and  datediff( current_date(),m.mbr_dob)/365.25 between hmr.lower_age_limit and hmr.upper_age_limit 
 and case when hmr.gender_id is not null and hmr.gender_id != 3 and  hmr.gender_id != 4 then hmr.gender_id = m.mbr_genderid else 1=1 end
 left OUTER join membership_claims mc on mc.mbr_id= mi.mbr_id 
 left OUTER join membership_claim_details mcd on mcd.mbr_claim_id = mc.mbr_claim_id 
 left OUTER join membership_provider mprvdr  on mprvdr.prvdr_id=mc.prvdr_id and mi.mbr_id=mprvdr.mbr_id           
 LEFT outer join hedis_cpt_measure hcptm on hcptm.hedis_msr_rule_Id =  hmr.hedis_msr_rule_Id  and  hcptm.cpt_id = mcd.cpt_code  
  LEFT OUTER JOIN cpt_measure  cpt on    hcptm.cpt_id =cpt.cpt_id   and  cpt.cpt_id = mcd.cpt_code 
  LEFT outer join hedis_icd_measure hicdm on   hicdm.hedis_msr_rule_Id =  hmr.hedis_msr_rule_Id 
  LEFT outer JOIN icd_measure  icd  on  icd.icd_id   = hicdm.icd_id  and mc.Diagnoses LIKE CONCAT('%', REPLACE(icd.code, ".",""),'%')   
  LEFT outer Join membership_problems mp on mp.pbm_id =hmr.problem_id and mp.mbr_id=mi.mbr_id  and  mcd.claim_start_date >= mp.start_date
  where  case when  hmr.cpt_or_icd = 2 then  mp.pbm_id is not null and hcptm.cpt_id is not null else 1=1 end
group by mi.mbr_id, mi.ins_id,hmr.hedis_msr_rule_id,  hicdm.icd_id,icd.icd_id
having    count( icd.icd_id) < dose_count
) a ;
