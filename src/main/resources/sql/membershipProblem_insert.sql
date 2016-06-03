INSERT INTO membership_claim_details (
mbr_id,pbm_id,start_date,resolved_date,created_date,updated_date,
created_by,updated_by,active_ind,file_id
 )
SELECT DISTINCT mc.mbr_id, p.pbm_id, mcd.claim_start_date start_date, null resolved_date,
 now() created_date, now() updated_date,'sarath' created_by ,'sarath' updated_by,'Y', :fileId
 FROM membership_claims mc 
 JOIN membership_claim_details mcd ON mc.mbr_claim_id = mcd.mbr_claim_id
JOIN membership_insurance mi ON mi.mbr_id  =  mc.mbr_id  AND mi.ins_id=2
JOIN problems p ON mi.ins_id=p.ins_id
JOIN problems_icd  pbmicd ON p.pbm_id = pbmicd.pbm_id
JOIN icd_measure  icd ON icd.icd_id = pbmicd.icd_id AND Diagnoses LIKE CONCAT('%', REPLACE(code, ".",""),'%') 
where mc.DX_TYPE_CD = 'ICD10'