insert into unprocessed_amg_claims
(
unprocess_amg_claim_id, file_id, remarks, IPA_CD, IPANAME, PCP_PROVIDER_NBR, PCPFIRSTNAME, PCPLASTNAME, SRC_SYS_MEMBER_NBR, MFNAME, 
MLNAME, MBRDOB, MBRGENDER, MEDICAIDNO, MEDICARENO, ACTIVITYDATE, ACTIVITYMONTH, CLAIMTYPE, SVCPROVIDERID, SVCPROVFULLNM, 
SVCPROVIDERCODE, SVCPROVIDERDESC, FACILITY_TYPE_CODE, FACILITY_TYPE_DESC, PARSTATUS, CLAIMNUMBER, CLM_LINE_SEQ_NBR, CLM_LINE_ADJ_SEQ_NBR, BILLTYPE, DRG, 
DRG_VERSION, DISCHARGESTATUS, PLACEOFSERVICE, DIAGNOSIS1, DIAGNOSIS2, DIAGNOSIS3, DIAGNOSIS4, DIAGNOSIS5, DIAGNOSIS6, DIAGNOSIS7,
DIAGNOSIS8, ICDPROCCODE1, ICDPROCCODE2, ICDPROCCODE3, ICDPROCCODE4, ICDPROCCODE5, DETAILSVCDATE, DETAILSVCTHRUDATE, REVENUECODE, PROCEDURECODE, 
PROCEDUREMODIFIER1, PROCEDUREMODIFIER2, QUANTITY, BILLEDAMOUNT, ALLOWEDAMOUNT, COINSURANCE, MEMBERCOPAY, DEDUCTIBLE, COBPAIDAMOUNT, NETAMT, 
CLAIMSTATUS, PROCESSINGSTATUS, CHECKDATE, RISK_ENTITY_TYPE, CONTRACT_YEAR, TIME_PERIOD, CONTRACT_PERIOD_START_DT, CONTRACT_PERIOD_END_DT, TRACK_MODEL, RISK_IND, 
ENCOUNTER_IND, PRODUCT_LABEL, PRODUCT_LVL1, PRODUCT_LVL2, PRODUCT_LVL3, PRODUCT_LVL4, PRODUCT_LVL5, PRODUCT_LVL6, PRODUCT_LVL7, MARKET_LVL1,
MARKET_LVL2, MARKET_LVL3, MARKET_LVL4, MARKET_LVL5, MARKET_LVL6, MARKET_LVL7, MARKET_LVL8, RISK_RECON_COS_DESC, TIN, DX_TYPE_CD, 
PROC_TYPE_CD, created_date, updated_date, created_by, updated_by
)
    
select NULL, :fileId, "Member not belonged to us", IPA_CD, IPANAME, PCP_PROVIDER_NBR, PCPFIRSTNAME, PCPLASTNAME, SRC_SYS_MEMBER_NBR, MFNAME, 
MLNAME, STRING_TO_DATE(csv2AmgClaim.MBRDOB) MBRDOB, 
MBRGENDER, MEDICAIDNO, MEDICARENO, 
STRING_TO_DATE(csv2AmgClaim.ACTIVITYDATE ), csv2AmgClaim.ACTIVITYMONTH, CLAIMTYPE, SVCPROVIDERID, SVCPROVFULLNM, 
SVCPROVIDERCODE, SVCPROVIDERDESC, FACILITY_TYPE_CODE, FACILITY_TYPE_DESC, PARSTATUS, CLAIMNUMBER, CLM_LINE_SEQ_NBR, CLM_LINE_ADJ_SEQ_NBR, BILLTYPE, DRG, 
DRG_VERSION, DISCHARGESTATUS, PLACEOFSERVICE, DIAGNOSIS1, DIAGNOSIS2, DIAGNOSIS3, DIAGNOSIS4, DIAGNOSIS5, DIAGNOSIS6, DIAGNOSIS7,
DIAGNOSIS8, ICDPROCCODE1, ICDPROCCODE2, ICDPROCCODE3, ICDPROCCODE4, ICDPROCCODE5, 
STRING_TO_DATE( DETAILSVCDATE )   , 
STRING_TO_DATE(DETAILSVCTHRUDATE )  , 
REVENUECODE, PROCEDURECODE, 
PROCEDUREMODIFIER1, PROCEDUREMODIFIER2, QUANTITY, SUBSTRING(BILLEDAMOUNT,1,10), SUBSTRING(ALLOWEDAMOUNT,1,10), COINSURANCE, MEMBERCOPAY, DEDUCTIBLE, SUBSTRING(COBPAIDAMOUNT,1,10), SUBSTRING(NETAMT,1,10),  
CLAIMSTATUS, PROCESSINGSTATUS, 
STRING_TO_DATE(CHECKDATE )   , 
RISK_ENTITY_TYPE, CONTRACT_YEAR,TIME_PERIOD, 
STRING_TO_DATE(CONTRACT_PERIOD_START_DT )   ,
STRING_TO_DATE(CONTRACT_PERIOD_END_DT)  , 
TRACK_MODEL, RISK_IND, 
ENCOUNTER_IND, csv2AmgClaim.PRODUCT_LABEL, PRODUCT_LVL1, PRODUCT_LVL2, PRODUCT_LVL3, PRODUCT_LVL4, PRODUCT_LVL5, PRODUCT_LVL6, PRODUCT_LVL7, MARKET_LVL1,
MARKET_LVL2, MARKET_LVL3, MARKET_LVL4, MARKET_LVL5, MARKET_LVL6, MARKET_LVL7, MARKET_LVL8, RISK_RECON_COS_DESC, TIN, DX_TYPE_CD, 
PROC_TYPE_CD, NOW(), NOW(), 'sarath', 'sarath'
from csv2table_amg_claim csv2AmgClaim
LEFT JOIN membership_insurance mi on mi.SRC_SYS_MBR_NBR  =  convert(csv2AmgClaim.SRC_SYS_MEMBER_NBR,unsigned)
where mi.mbr_id is null;