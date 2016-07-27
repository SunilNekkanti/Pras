 insert into membership_claim_details 
 (mbr_claim_id, claim_line_seq_nbr, clm_line_adj_seq_nbr,
 activity_date, activity_month,
 claim_start_date,
 claim_end_date,
 paid_date,
 revenue_code, cpt_code, cpt_code_modifier1, cpt_code_modifier2, claim_status,
 location_id, risk_recon_cos_des, amount_paid, allow_amt, co_insurance,
 co_pay, deductible, cob_paid_amount, processing_status, pharmacy_name,
quantity, npos, risk_id, runn_date, ndc,
pharmacy, membership_claims, psychare, simple_county, triangles,
cover, created_date, updated_date, created_by, updated_by, active_ind, file_id,
 mony, drug_label_name, drug_version
 )
 
 SELECT distinct mc.mbr_claim_id, csv2BhClaim.ClaimLine, null clm_line_adj_seq_nbr, 
 cast(concat(csv2BhClaim.MOS,'-01') as date) ,
 date_format(cast(concat(csv2BhClaim.MOS,'-01') as date) ,'%Y%m'),
 cast(csv2BhClaim.ClaimStartDate as date), 
 cast(csv2BhClaim.ClaimEndDate as date),
 cast(csv2BhClaim.PaidDate as date),
 csv2BhClaim.RevCode, cpt.cpt_id, NULL cpt_code_modifier1, null cpt_code_modifier2, csv2BhClaim.ClaimDetailStatus, 
 roomType.id, null risk_recon_cos_des, csv2BhClaim.amountpaid, csv2BhClaim.AllowAmt, null co_insurance, 
 csv2BhClaim.Copay, null deductible, null cob_paid_amount, null processing_status, null pharmacy_name, 
 NULLIF(csv2BhClaim.Quantity,''),  csv2BhClaim.NPOS, csv2BhClaim.RiskId, cast(csv2BhClaim.RunnDate as date), csv2BhClaim.NDC,
 csv2BhClaim.PHARMACY, csv2BhClaim.Claims, csv2BhClaim.Psychare, csv2BhClaim.Simple_County, csv2BhClaim.Triangles,  
csv2BhClaim.Cover, now() created_date, now() updated_date, 'sarath' created_by ,'sarath' updated_by,'Y', :fileId,
  csv2BhClaim.Mony, csv2BhClaim.DrugLabelName, null drug_version
  
  FROM csv2Table_BH_Claim csv2BhClaim
  JOIN membership m on m.Mbr_MedicaidNo  =  convert(csv2BhClaim.MedicaidId, unsigned)  
 JOIN membership_insurance mi on mi.mbr_id  =  m.mbr_id and mi.ins_id=:insId
  JOIN membership_claims mc on  mc.claim_id_number= csv2BhClaim.ClaimId   and  mi.mbr_id =mc.mbr_id and  mi.ins_id=mc.ins_id
  LEFT OUTER JOIN cpt_measure  cpt on cpt.code =  NULLIF(csv2BhClaim.ServCode,'')
  LEFT OUTER JOIN lu_place_of_service roomType on ucase(roomtype.name) = ucase(trim(POS))
  LEFT OUTER JOIN membership_claim_details mcd on mcd.mbr_claim_id =  mc.mbr_claim_id
  WHERE mcd.mbr_claim_id is null ;
  
  
  
  insert into membership_claim_details 
  (mbr_claim_id, claim_line_seq_nbr, clm_line_adj_seq_nbr,
 activity_date, activity_month,
 claim_start_date,
 claim_end_date,
 paid_date,
 revenue_code, cpt_code, cpt_code_modifier1, cpt_code_modifier2, claim_status,
 location_id, risk_recon_cos_des, amount_paid, allow_amt, co_insurance,
 co_pay, deductible, cob_paid_amount, processing_status, pharmacy_name,
quantity, npos, risk_id, runn_date, ndc,
pharmacy, membership_claims, psychare, simple_county, triangles,
cover, created_date, updated_date, created_by, updated_by, active_ind, file_id,
 mony, drug_label_name, drug_version
 )
SELECT distinct mc.mbr_claim_id, csv2BhClaim.claimline, null clm_line_adj_seq_nbr, 
cast(str_to_date(csv2BhClaim.activity_date ,'%m/%d/%Y') as date),    
 date_format(cast(str_to_date(csv2BhClaim.activity_date ,'%m/%d/%Y') as date) ,'%Y%m'),
 cast(str_to_date(csv2BhClaim.ServiceStart ,'%m/%d/%Y')as date), 
 cast(str_to_date(csv2BhClaim.ServiceEnd,'%m/%d/%Y') as date),
cast(str_to_date(csv2BhClaim.activity_date ,'%m/%d/%Y') as date),
 csv2BhClaim.revcode, cpt.cpt_id, NULL cpt_code_modifier1, null cpt_code_modifier2, csv2BhClaim.ParStatus, 
 roomType.id, null risk_recon_cos_des, csv2BhClaim.paid, null, null co_insurance, 
 csv2BhClaim.Copay,  csv2BhClaim.deductible,csv2BhClaim.cobamt , null processing_status, null pharmacy_name, 
 NULLIF(csv2BhClaim.Qty,''), null  , csv2BhClaim.Risk, null runndate, null,
 null, csv2BhClaim.billed, csv2BhClaim.fromfile, csv2BhClaim.memcounty, null,  
null , now() created_date, now() updated_date, 'sarath' created_by ,'sarath' updated_by,'Y', 1,
 null mony, csv2BhClaim.drg, null drug_version
  
  FROM csv2Table_BH_Claim1 csv2BhClaim
  JOIN membership m on m.Mbr_MedicaidNo  =  csv2BhClaim.MedicaidNumber  
  JOIN membership_claims mc on  mc.claim_id_number= csv2BhClaim.ClaimNum   and  m.mbr_id =mc.mbr_id and  mc.ins_id=:insId
  LEFT OUTER JOIN cpt_measure  cpt on cpt.code =  NULLIF(csv2BhClaim.servcode,'')
  LEFT OUTER JOIN lu_place_of_service roomType on ucase(roomtype.name) = ucase(trim(POS))
  LEFT OUTER JOIN membership_claim_details mcd on mcd.mbr_claim_id =  mc.mbr_claim_id
  WHERE mcd.mbr_claim_id is null  ;
