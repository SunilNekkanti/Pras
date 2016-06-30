INSERT INTO membership_claims
(
claim_id_number,mbr_id,prvdr_id,ins_id,claim_type,facility_type_code,bill_type_code,
frequency_type_code,bill_type,dischargestatus,
MemEnrollId,Diagnoses,product_label,product_lvl1,product_lvl2,product_lvl3,product_lvl4,product_lvl5,product_lvl6,product_lvl7,
market_lvl1,market_lvl2,market_lvl3,market_lvl4,market_lvl5,market_lvl6,market_lvl7,market_lvl8,
tin,dx_type_cd,proc_type_cd,created_date,updated_date,created_by,updated_by,active_ind,file_id
)
SELECT DISTINCT
CLAIMNUMBER,mi.mbr_id,mp.prvdr_id,mi.ins_id, CLAIMTYPE,lft.code,null,
null,csv2AmgClaim.BILLTYPE,csv2AmgClaim.DISCHARGESTATUS,null,
CONCAT_WS(',',NULLIF(DIAGNOSIS1,'') , NULLIF(DIAGNOSIS2,'') , NULLIF(DIAGNOSIS3,''), NULLIF(DIAGNOSIS4,'') ,NULLIF(DIAGNOSIS5,'') , NULLIF(DIAGNOSIS6,'') , NULLIF(DIAGNOSIS7,'') ,NULLIF(DIAGNOSIS8,'')) diagnoses,
csv2AmgClaim.PRODUCT_LABEL,csv2AmgClaim.PRODUCT_LVL1,csv2AmgClaim.PRODUCT_LVL2,csv2AmgClaim.PRODUCT_LVL3,csv2AmgClaim.PRODUCT_LVL4,csv2AmgClaim.PRODUCT_LVL5,
csv2AmgClaim.PRODUCT_LVL6,csv2AmgClaim.PRODUCT_LVL7,csv2AmgClaim.MARKET_LVL1,csv2AmgClaim.MARKET_LVL2,csv2AmgClaim.MARKET_LVL3,csv2AmgClaim.MARKET_LVL4,
csv2AmgClaim.MARKET_LVL5,csv2AmgClaim.MARKET_LVL6,csv2AmgClaim.MARKET_LVL7,csv2AmgClaim.MARKET_LVL8,
csv2AmgClaim.TIN,csv2AmgClaim.DX_TYPE_CD,csv2AmgClaim.PROC_TYPE_CD,
  now(),now(),'sarath','sarath','Y', :fileId 
  FROM csv2Table_Amg_Claim csv2AmgClaim
 JOIN membership_insurance mi on mi.SRC_SYS_MBR_NBR  =  csv2AmgClaim.SRC_SYS_MEMBER_NBR 
 JOIN membership_provider mp on mp.mbr_id  =  mi.mbr_id 
 JOIN reference_contract rc on  rc.prvdr_id= mp.prvdr_id and rc.insurance_id=mi.ins_id
 JOIN contract c on c.ref_contract_Id = rc.ref_contract_Id and c.PCP_PROVIDER_NBR = csv2AmgClaim.PCP_PROVIDER_NBR
LEFT OUTER JOIN lu_facility_type lft on lft.description = csv2AmgClaim.FACILITY_TYPE_DESC
LEFT OUTER JOIN membership_claims mc on mc.mbr_id =  mi.mbr_id
WHERE  mc.mbr_id is null
