INSERT INTO membership_claims
(
claim_id_number, mbr_id, prvdr_id, ins_id, claim_type,
facility_type_code, bill_type_code, frequency_type_code, bill_type, ndc,
mony, drug_label_name, drug_version, dischargestatus, MemEnrollId,
Diagnoses, product_label, product_lvl1, product_lvl2, product_lvl3, 
product_lvl4, product_lvl5, product_lvl6, product_lvl7, market_lvl1,
market_lvl2, market_lvl3, market_lvl4, market_lvl5, market_lvl6,
market_lvl7, market_lvl8, tin, dx_type_cd, proc_type_cd,
created_date, updated_date, created_by, updated_by, active_ind, file_id
)
SELECT DISTINCT ClaimId, mi.mbr_id, mp.prvdr_id, mi.ins_id, NULL,
NULLIF(lft.code,''), NULLIF(lbt.code,''), NULLIF(csv2BhClaim.FrequencyCode,''), csv2BhClaim.BillClassCode, csv2BhClaim.NDC,
csv2BhClaim.Mony, csv2BhClaim.DrugLabelName, null drug_version, null dischargestatus, csv2BhClaim.MemEnrollId,
csv2BhClaim.Diagnoses, null product_label, null product_lvl1, null product_lvl2, null market_lvl1, 
null product_lvl4, null product_lvl5, null product_lvl6, null product_lvl7, null product_lvl1, 
null market_lvl2, null market_lvl3, null market_lvl4, null market_lvl5, null market_lvl6,
null market_lvl7, null market_lvl8, null tin,'ICD10', null proc_type_cd,
now(), now(), 'sarath', 'sarath', 'Y', :fileId 
  FROM csv2Table_BH_Claim csv2BhClaim
  
 JOIN membership m on m.Mbr_MedicaidNo  =  csv2BhClaim.MedicaidId
  
 JOIN membership_insurance mi on mi.mbr_id  =  m.mbr_id
 
 JOIN membership_provider mp on mp.mbr_id  =  mi.mbr_id 
 
 JOIN reference_contract rc on  rc.prvdr_id= mp.prvdr_id and rc.insurance_id=mi.ins_id
 
  JOIN contract c on c.ref_contract_Id = rc.ref_contract_Id and c.PCP_PROVIDER_NBR = csv2BhClaim.ProvAffiliationId
  
LEFT OUTER JOIN lu_facility_type lft on lft.code = csv2BhClaim.FacilityCode

LEFT OUTER JOIN lu_bill_type lbt on lbt.description = csv2BhClaim.BillClassCode
 
LEFT OUTER JOIN membership_claims mc on mc.mbr_id =  mi.mbr_id

WHERE  mc.mbr_id is null 
-- and csv2BhClaim.ClaimStartDate >= '2015-10-01'