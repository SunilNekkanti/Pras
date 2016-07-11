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
 DATE_FORMAT(str_to_date(csv2BhClaim.ClaimStartDate, '%c/%e/%Y'),'%Y-%c-%e'), 
 DATE_FORMAT(str_to_date(csv2BhClaim.ClaimEndDate, '%c/%e/%Y'),'%Y-%c-%e'),
 DATE_FORMAT(str_to_date(csv2BhClaim.PaidDate, '%c/%e/%Y %H:%i'),'%Y-%c-%e'),
 csv2BhClaim.RevCode, cpt.cpt_id, NULL cpt_code_modifier1, null cpt_code_modifier2, csv2BhClaim.ClaimDetailStatus, 
 roomType.id, null risk_recon_cos_des, null amount_paid, csv2BhClaim.AllowAmt, null co_insurance, 
 csv2BhClaim.Copay, null deductible, null cob_paid_amount, null processing_status, null pharmacy_name, 
 NULLIF(csv2BhClaim.Quantity,''),  csv2BhClaim.NPOS, csv2BhClaim.RiskId, DATE_FORMAT(str_to_date(csv2BhClaim.RunnDate, '%c/%e/%Y %H:%i'),'%Y-%c-%e'), csv2BhClaim.NDC,
 csv2BhClaim.PHARMACY, csv2BhClaim.Claims, csv2BhClaim.Psychare, csv2BhClaim.Simple_County, csv2BhClaim.Triangles,  
csv2BhClaim.Cover, now() created_date, now() updated_date, 'sarath' created_by ,'sarath' updated_by,'Y', :fileId,
  csv2BhClaim.Mony, csv2BhClaim.DrugLabelName, null drug_version
  
  FROM csv2Table_BH_Claim csv2BhClaim
  JOIN membership_claims mc on  mc.claim_id_number= csv2BhClaim.ClaimId  
  LEFT OUTER JOIN cpt_measure  cpt on cpt.code =  NULLIF(csv2BhClaim.ServCode,'')
  LEFT OUTER JOIN lu_place_of_service roomType on ucase(roomtype.name) = ucase(trim(POS))
  LEFT OUTER JOIN membership_claim_details mcd on mcd.mbr_claim_id =  mc.mbr_claim_id
  WHERE mcd.mbr_claim_id is null 