INSERT INTO unprocessed_bh_claims
 (
 file_id, remarks,
 Uniuqe,MOS, MOP, MOS2, MOP2, ClaimId,ClaimLine,MemQnxtId,MemFullName,PlanId,
 Location,FacilityCode,FacilityType, BillClassCode,BillClasification,FrequencyCode,Frequency,POS,ClaimStartDate,ClaimEndDate,
 PaidDate,Admit,Discharge, admithour,RevCode,ServCode,ServCodeDesc,ClaimDetailStatus,AmountPaid,ProvId,
 ProvFullName,ProvSpecialty,Mony,DrugLabelName,MemDOB,MemAge,MemPCPFullName,MemPCPSpecialty,MedicaidId,MemEnrollId,
 PCPAffiliationId, ProvAffiliationId, GroupID, GroupName, Diagnoses, diag2, diag3, diag4, prindiag, Diag1_ICDVersion, 
 Diag2_ICDVersion, Diag3_ICDVersion, Diag4_ICDVersion, product, product_label, AllowAmt, Copay, deductible, cobamt,contractpaid,
 ineligibleamt, PharmacyName,Quantity,NPOS,RiskId,RunnDate,NDC,PHARMACY,Claims, Psychare,Simple_County, 
 CountyCode,Triangles,Cover,`Mod`,Gender,ProgramID,ContractID, ServProvName,ServProvID,ServProvSpecialty, 
 ServProvSpecialtyDesc,patientstatus,IPA,SubIPA,HICN, formtype,ReferralID, BillType,ClaimType,
 claim_cat, created_date, updated_date, created_by, updated_by, csv2table_bh_claim_id
 ) 
 select  
  :fileId,'Membership Not belong to us',
  Uniuqe, 
  case when MOS <> '' then cast(concat(MOS,'-01') as date) else NULL end MOS, 
  case when MOS <> '' then cast(concat(MOP,'-01') as date) else NULL end MOP, 
  case when MOS2 <> '' then STRING_TO_DATE(MOS2) else NULL end MOS2, 
  case when MOP2 <> '' then STRING_TO_DATE(MOP2) else NULL end MOP2, 
  ClaimId, ClaimLine, MemQnxtId, MemFullName, PlanId,
  Location, FacilityCode, FacilityType, BillClassCode, BillClasification, FrequencyCode, Frequency, POS, 
  case when ClaimStartDate <> '' then STRING_TO_DATE(ClaimStartDate) else NULL end ClaimStartDate, 
  case when ClaimEndDate <> '' then STRING_TO_DATE(ClaimEndDate) else NULL end ClaimEndDate,
  case when PaidDate <> '' then STRING_TO_DATE(PaidDate) else NULL end PaidDate,
  NULL Admit, NULL Discharge, NULL admithour, RevCode, ServCode, ServCodeDesc, ClaimDetailStatus, AmountPaid, ProvId, 
  ProvFullName, ProvSpecialty, Mony, DrugLabelName, 
  case when MemDOB <> '' then STRING_TO_DATE(MemDOB) else NULL end MemDOB, 
  MemAge, MemPCPFullName, MemPCPSpecialty, MedicaidId, MemEnrollId,
  PCPAffiliationId, ProvAffiliationId, GroupID, GroupName, csv2BhClaim.Diagnoses, NULL diag2, NULL diag3, NULL diag4, NULL prindiag, NULL Diag1_ICDVersion,
  NULL Diag2_ICDVersion, NULL Diag3_ICDVersion, NULL Diag4_ICDVersion, NULL product, NULL product_label, AllowAmt, Copay, NULL deductible, NULL cobamt, NULL contractpaid,
  NULL ineligibleamt, PharmacyName, Quantity, NPOS, RiskId, 
  case when RunnDate <> '' then STRING_TO_DATE(RunnDate) else NULL end RunnDate, 
  NDC, PHARMACY, Claims, Psychare, Simple_County,
  NULL CountyCode, Triangles, Cover, NULL `Mod`, NULL Gender, NULL ProgramID, NULL ContractID, NULL ServProvName, NULL ServProvID,
  NULL ServProvSpecialty, NULL ServProvSpecialtyDesc, NULL patientstatus, NULL IPA, NULL SubIPA, NULL HICN, NULL formtype, NULL ReferralID,NULL BillType, NULL ClaimType,
  NULL claim_cat, now(), now(), 'sarath', 'sarath', csv2table_bh_claim_id
from csv2table_bh_claim csv2BhClaim
 left join membership m  on csv2BhClaim.medicaidId = m.mbr_medicaidNO
 where m.mbr_id is null;