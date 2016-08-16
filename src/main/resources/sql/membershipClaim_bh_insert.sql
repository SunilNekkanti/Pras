INSERT INTO membership_claims
(
claim_id_number, mbr_id, prvdr_id, ins_id, claim_type,
facility_type_code, bill_type_code, frequency_type_code, bill_type,  dischargestatus, MemEnrollId,
Diagnoses, product_label, product_lvl1, product_lvl2, product_lvl3, 
product_lvl4, product_lvl5, product_lvl6, product_lvl7, market_lvl1,
market_lvl2, market_lvl3, market_lvl4, market_lvl5, market_lvl6,
market_lvl7, market_lvl8, tin, dx_type_cd, proc_type_cd,
created_date, updated_date, created_by, updated_by, active_ind, file_id,pcp_prov_Id
)
SELECT  ClaimId, m.mbr_id, mp.prvdr_id,:insId, NULL,
NULLIF(lft.code,''), NULLIF(lbt.code,''), NULLIF(csv2BhClaim.FrequencyCode,''), csv2BhClaim.BillClassCode,  null dischargestatus, csv2BhClaim.MemEnrollId,
replace(group_concat( distinct replace(csv2BhClaim.Diagnoses,'.','')),'|',','), null product_label, null product_lvl1, null product_lvl2, null market_lvl1, 
null product_lvl4, null product_lvl5, null product_lvl6, null product_lvl7, null product_lvl1, 
null market_lvl2, null market_lvl3, null market_lvl4, null market_lvl5, null market_lvl6,
null market_lvl7, null market_lvl8, null tin,'ICD10', null proc_type_cd,
now(), now(), 'sarath', 'sarath', 'Y', :fileId,csv2BhClaim.ProvId
  FROM csv2Table_BH_Claim csv2BhClaim
 JOIN membership m on m.Mbr_MedicaidNo  =  csv2BhClaim.MedicaidId   
 LEFT OUTER JOIN contract c on trim(c.PCP_PROVIDER_NBR) = trim(csv2BhClaim.ProvId)
 LEFT OUTER JOIN provider prvdr on trim(prvdr.code)  =  trim(csv2BhClaim.ProvId)
 LEFT OUTER JOIN reference_contract rc on  rc.insurance_id=:insId and  (rc.prvdr_id= prvdr.prvdr_id or  c.ref_contract_Id = rc.ref_contract_Id)      
 LEFT OUTER JOIN membership_provider mp on mp.mbr_id  =  m.mbr_id  and rc.prvdr_id= mp.prvdr_id
LEFT OUTER JOIN lu_facility_type lft on lft.code = csv2BhClaim.FacilityCode
LEFT OUTER JOIN lu_bill_type lbt on lbt.description = csv2BhClaim.BillClassCode 
LEFT OUTER JOIN membership_claims mc on  mc.claim_id_number = ClaimId 
 WHERE    mc.claim_id_number is null 
GROUP BY ClaimId,m.mbr_id;



INSERT INTO membership_claims
(
claim_id_number,mbr_id,prvdr_id,ins_id,Claim_Type,facility_type_code,bill_type_code,
frequency_type_code,bill_type,dischargestatus,
MemEnrollId,Diagnoses,product_label,product_lvl1,product_lvl2,product_lvl3,product_lvl4,product_lvl5,product_lvl6,product_lvl7,
market_lvl1,market_lvl2,market_lvl3,market_lvl4,market_lvl5,market_lvl6,market_lvl7,market_lvl8,
tin,dx_type_cd,proc_type_cd,created_date,updated_date,created_by,updated_by,active_ind,file_id,pcp_prov_Id
)
SELECT 
ClaimNum,m.mbr_id,mp.prvdr_id,:insId, csv2bhClaim.ClaimType,null,null,
null,csv2bhClaim.BILLTYPE,csv2bhClaim.patientstatus,null,
 replace(CONCAT_WS(',',NULLIF(diag1,'') , NULLIF(diag2,'') , NULLIF(diag3,''), NULLIF(diag4,'') ) ,'.','') diagnoses,
csv2bhClaim.PRODUCT_LABEL,csv2bhClaim.PRODUCT,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null, 
case when STRING_TO_DATE(csv2BhClaim.ServiceStart) >= '2015-10-01' then 'ICD10' else null end ,null,
  now(),now(),'sarath','sarath','Y', :fileId ,csv2bhClaim.PCPID
  FROM csv2Table_bh_Claim1 csv2bhClaim
 JOIN membership  m on m.Mbr_MedicaidNo  =  csv2bhClaim.MedicaidNumber
 LEFT OUTER JOIN contract c on trim(c.PCP_PROVIDER_NBR) = trim(csv2BhClaim.PCPID)
 LEFT OUTER JOIN provider prvdr on trim(prvdr.code)  =  trim(csv2BhClaim.PCPID)
 LEFT OUTER JOIN reference_contract rc on  rc.insurance_id=:insId and  (rc.prvdr_id= prvdr.prvdr_id or  c.ref_contract_Id = rc.ref_contract_Id)      
 LEFT OUTER JOIN membership_provider mp on mp.mbr_id  =  m.mbr_id  and rc.prvdr_id= mp.prvdr_id
LEFT OUTER JOIN membership_claims mc on mc.claim_id_number =  csv2bhClaim.ClaimNum and mc.mbr_id=m.mbr_id 
  WHERE  mc.claim_id_number is null    
GROUP BY ClaimNum,m.mbr_id;


update membership_claims  mcc
join   
(
SELECT ClaimId,mbr_id, ProvId,GROUP_CONCAT(DISTINCT SUBSTRING_INDEX(SUBSTRING_INDEX(t.Diagnoses, ',', sub0.aNum), ',', -1)) AS Diagnoses
FROM 
( SELECT  ClaimId, mbr_id, ProvId, group_concat( distinct Diagnoses) Diagnoses
 from ( 
 SELECT   ClaimId, m.mbr_id, ProvId,replace(replace(csv2BhClaim.Diagnoses ,'|',','),'.','') Diagnoses
  FROM csv2Table_BH_Claim csv2BhClaim
 JOIN membership m on m.Mbr_MedicaidNo  =  csv2BhClaim.MedicaidId   
  union 
 select claim_id_number,mbr_id,pcp_prov_Id as ProvId,Diagnoses  from membership_claims mc
 )a
 group by ClaimId, mbr_id, ProvId)t
INNER JOIN
(
    SELECT 1 + units.i + tens.i * 10 AS aNum, units.i + tens.i * 10 AS aSubscript
    FROM (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) units
    CROSS JOIN (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 ) tens
) sub0
  ON (1 + LENGTH(t.Diagnoses) - LENGTH(REPLACE(t.Diagnoses, ',', ''))) >= sub0.aNum
GROUP BY ClaimId,mbr_id, ProvId

) a on mcc.claim_id_number =a.ClaimId  and mcc.mbr_id=a.mbr_id and mcc.pcp_prov_Id=a.ProvId
set mcc.Diagnoses =a.Diagnoses;





update membership_claims  mcc
join   
(
SELECT ClaimNum,mbr_id, PCPID,GROUP_CONCAT(DISTINCT SUBSTRING_INDEX(SUBSTRING_INDEX(t.Diagnoses, ',', sub0.aNum), ',', -1)) AS Diagnoses
FROM 
( SELECT  ClaimNum, mbr_id, PCPID, group_concat( distinct Diagnoses) Diagnoses
 from ( 
 SELECT   ClaimNum, m.mbr_id,PCPID,CONCAT_WS(',',NULLIF(diag1,'') , NULLIF(diag2,'') , NULLIF(diag3,''), NULLIF(diag4,'') )  Diagnoses
  FROM csv2Table_BH_Claim1 csv2BhClaim
 JOIN membership m on m.Mbr_MedicaidNo  =   csv2BhClaim.MedicaidNumber  
    union 
 select claim_id_number as ClaimNum,mbr_id,pcp_prov_Id as PCPID,Diagnoses  from membership_claims mc 
 )a 
 group by ClaimNum, mbr_id, PCPID)t
INNER JOIN
(
    SELECT 1 + units.i + tens.i * 10 AS aNum, units.i + tens.i * 10 AS aSubscript
    FROM (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) units
    CROSS JOIN (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 ) tens
) sub0
  ON (1 + LENGTH(t.Diagnoses) - LENGTH(REPLACE(t.Diagnoses, ',', ''))) >= sub0.aNum
GROUP BY ClaimNum,mbr_id, PCPID

) a on mcc.claim_id_number =a.ClaimNum  and mcc.mbr_id=a.mbr_id and mcc.pcp_prov_Id=a.PCPID
set mcc.Diagnoses =a.Diagnoses;

