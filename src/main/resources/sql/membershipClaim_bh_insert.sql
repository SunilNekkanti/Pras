INSERT INTO membership_claims
(
claim_id_number, mbr_id, prvdr_id, ins_id, claim_type,
facility_type_code, bill_type_code, frequency_type_code, bill_type,  dischargestatus, MemEnrollId,
Diagnoses, product_label, product_lvl1, product_lvl2, product_lvl3, 
product_lvl4, product_lvl5, product_lvl6, product_lvl7, market_lvl1,
market_lvl2, market_lvl3, market_lvl4, market_lvl5, market_lvl6,
market_lvl7, market_lvl8, tin, dx_type_cd, proc_type_cd,
created_date, updated_date, created_by, updated_by, active_ind, file_id
)
SELECT  ClaimId, mi.mbr_id, mp.prvdr_id,mi.ins_id, NULL,
NULLIF(lft.code,''), NULLIF(lbt.code,''), NULLIF(csv2BhClaim.FrequencyCode,''), csv2BhClaim.BillClassCode,  null dischargestatus, csv2BhClaim.MemEnrollId,
replace(group_concat( distinct csv2BhClaim.Diagnoses),'|',','), null product_label, null product_lvl1, null product_lvl2, null market_lvl1, 
null product_lvl4, null product_lvl5, null product_lvl6, null product_lvl7, null product_lvl1, 
null market_lvl2, null market_lvl3, null market_lvl4, null market_lvl5, null market_lvl6,
null market_lvl7, null market_lvl8, null tin,'ICD10', null proc_type_cd,
now(), now(), 'sarath', 'sarath', 'Y', :fileId
  FROM csv2Table_BH_Claim csv2BhClaim
 JOIN membership m on m.Mbr_MedicaidNo  =  convert(csv2BhClaim.MedicaidId, unsigned)  
 JOIN membership_insurance mi on mi.mbr_id  =  m.mbr_id 
 LEFT OUTER JOIN contract c on trim(c.PCP_PROVIDER_NBR) = trim(csv2BhClaim.ProvId)
 LEFT OUTER JOIN provider prvdr on trim(prvdr.code)  =  trim(csv2BhClaim.ProvId)
 JOIN reference_contract rc on  rc.insurance_id=mi.ins_id and  (rc.prvdr_id= prvdr.prvdr_id or  c.ref_contract_Id = rc.ref_contract_Id)      
 JOIN membership_provider mp on mp.mbr_id  =  mi.mbr_id  and rc.prvdr_id= mp.prvdr_id
LEFT OUTER JOIN lu_facility_type lft on lft.code = csv2BhClaim.FacilityCode
LEFT OUTER JOIN lu_bill_type lbt on lbt.description = csv2BhClaim.BillClassCode 
LEFT OUTER JOIN membership_claims mc on  mc.claim_id_number = ClaimId 
 WHERE    mc.claim_id_number is null 
GROUP BY ClaimId 
-- and csv2BhClaim.ClaimStartDate >= '2015-10-01'