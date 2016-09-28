INSERT INTO pharmacy (
pharmacy_id, `name`,address_1,address_2,city,state_code,
zip, phone_number,ncp_dpn_number,npi_number, pharmacy_number,file_id,
created_date, updated_date, created_by, updated_by,active_ind
)
SELECT NULL, PHARMACYNAME, PHARMACYADDRLINE1,PHARMACYADDRLINE2,
PHARMACYCITYNAME, PHARMACYSTATECODE, PHARMACYZIP, PHARMACYPHONENUMBER, PHARMACYNCPDPNUMBER,
PHARMACYNPINUMBER, PHARMACYNUMBER, :fileId, now(),now(),'sarath','sarath','Y' 
FROM 
csv2table_amg_pharmacy csv2talbe_phar
LEFT OUTER JOIN  pharmacy phar  on TRIM(csv2talbe_phar.PHARMACYNAME)  =  phar.name
WHERE   phar.pharmacy_id IS NULL
group by PHARMACYNAME;



INSERT INTO membership_claims
( claim_id_number, mbr_id, prvdr_id, pcp_prov_Id, ins_id, report_Month, claim_type,
 location_id, facility_type_code, bill_type_code, frequency_type_code,  
 bill_type, dischargestatus, MemEnrollId, Diagnoses, 
 product_label, product_lvl1, product_lvl2, product_lvl3, product_lvl4, product_lvl5, product_lvl6, product_lvl7, 
 market_lvl1, market_lvl2, market_lvl3, market_lvl4, market_lvl5, market_lvl6, market_lvl7, market_lvl8, 
 risk_recon_cos_des, tin, dx_type_cd, proc_type_cd, created_date, updated_date, created_by, updated_by, active_ind, file_id
 )

SELECT 
CLAIMNUMBER,mi.mbr_id,rc.prvdr_id, phar.PCP_PROVIDER_NBR,mi.INS_ID,:reportMonth,CLAIMTYPE,NULL location_id,
NULL facility_type_code,NULL bill_type_code, NULL frequency_type_code, NULL bill_type,NULL dischargestatus,
NULL MemEnrollId,NULL Diagnoses,phar.PRODUCT_LABEL,phar.PRODUCT_LVL1,phar.PRODUCT_LVL2,phar.PRODUCT_LVL3,phar.PRODUCT_LVL4,phar.PRODUCT_LVL5,
phar.PRODUCT_LVL6,phar.PRODUCT_LVL7,phar.MARKET_LVL1,phar.MARKET_LVL2,phar.MARKET_LVL3,phar.MARKET_LVL4,phar.MARKET_LVL5,phar.MARKET_LVL6,
phar.MARKET_LVL7,phar.MARKET_LVL8,phar.RISK_RECON_COS_DESC,phar.TIN,NULL,NULL,NOW(),NOW(),'SARATH','SARATH','Y' , :fileId

FROM csv2table_amg_pharmacy phar
join membership_insurance mi  on mi.SRC_SYS_MBR_NBR =phar.SRC_SYS_MEMBER_NBR and mi.ins_id=:insId
LEFT OUTER JOIN contract c on c.PCP_PROVIDER_NBR like concat ('%', trim(phar.PCP_PROVIDER_NBR) ,'%')
LEFT OUTER JOIN reference_contract rc on  c.ref_contract_Id = rc.ref_contract_Id  
LEFT JOIN membership_claims mc on mc.claim_id_number= phar.CLAIMNUMBER
where mc.claim_id_number is null
group by  CLAIMNUMBER,mi.mbr_id,rc.prvdr_id, phar.PCP_PROVIDER_NBR,mi.INS_ID;



INSERT INTO membership_claim_details 
(  mbr_claim_id, claim_line_seq_nbr, clm_line_adj_seq_nbr, activity_date, activity_month, claim_start_date, claim_end_date, paid_date, revenue_code, cpt_code,
cpt_code_modifier1, cpt_code_modifier2, claim_status, location_id, risk_recon_cos_des, amount_paid, allow_amt, co_insurance, co_pay, deductible, cob_paid_amount, processing_status,
 pharmacy_name, quantity, npos, risk_id, runn_date, ndc, ndc_description, mail_order_ind, number_of_refills, days_supply, ingredient_cost, dispensing_fee, sales_tax, check_mail_date,
 presdeanumber, pres_npi_number, prescribing_physician, prescriber_spec_code, generic_indicator, therapeutic_class, medicareb_indicator, medd_covered_drug_flag, pharmacy_id, pharmacy, 
 membership_claims, psychare, simple_county, triangles, cover, created_date, updated_date, created_by, updated_by, active_ind, file_id, mony, drug_label_name, drug_version
)
 
select
mc.mbr_claim_id,1 claim_line_seq_nbr,null clm_line_adj_seq_nbr,STRING_TO_DATE(ACTIVITYDATE) activity_date,ACTIVITYMONTH	 ,STRING_TO_DATE(ACTIVITYDATE) claim_start_date,STRING_TO_DATE(ACTIVITYDATE) claim_end_date,
STRING_TO_DATE(DATEFILLED) ,null revenue_code,null cpt_code,null cpt_code_modifier1,null cpt_code_modifier2,TRANSACTIONSTATUS claim_status,null location_id,RISK_RECON_COS_DESC,AMOUNTPAID amount_paid,null  allow_amt,
null  co_insurance,null COPAY, null deductible, null cob_paid_amount,  TRANSACTIONSTATUS processing_status,  PHARMACYNAME, METRICQUANTITY, null npos,null risk_id,null runn_date,
NDCNUMBER,NDCDESCRIPTION, MAILORDERIND,	 NUMBEROFREFILLS, DAYSSUPPLY, INGREDIENTCOST, DISPENSINGFEE, SALESTAX,STRING_TO_DATE(CHECKMAILDATE),if(trim(phar.PRESDEANUMBER)='',null,phar.PRESDEANUMBER),
if(trim(phar.PRESNPINUMBER)='',null,phar.PRESNPINUMBER),phar.PRESCRIBINGPHYSICIAN,phar.PRESCRIBERSPECCODE	,phar.GENERICINDICATOR,
phar.THERAPEUTICCLASS	,phar.MEDICAREBINDICATOR	,phar.MEDDCOVEREDDRUGFLAG,null pharmacyid,AMOUNTPAID pharmacy,AMOUNTPAID  membership_claims,null psychare,null simple_county,null triangles,null cover,now() created_date,now()  updated_date, 
'sarath' created_by, 'sarath' updated_by, 'Y'active_ind, :fileId file_id, null mony, null drug_label_name, null drug_version 
 from  csv2table_amg_pharmacy phar
 join membership_claims  mc on mc.claim_id_number= phar.CLAIMNUMBER
 left outer join membership_claim_details mcd on mcd.mbr_claim_id =mc.mbr_claim_id  
 where mcd.mbr_claim_id is null;
 
  insert into unprocessed_amg_pharmacy_claims
(
	unprocessed_amg_pharmacy_claim_id, file_id, remarks, prvdr_id, ins_id, IPA_CD, IPANAME, PCP_PROVIDER_NBR, PCPFIRSTNAME, PCPLASTNAME, SRC_SYS_MEMBER_NBR, 
    MFNAME, MLNAME, MBRDOB, MBRGENDER, MEDICAIDNO, MEDICARENO, ACTIVITYDATE, ACTIVITYMONTH, CLAIMTYPE, DATEFILLED, CLAIMNUMBER, 
    PHARMACYNUMBER, PHARMACYNAME, PHARMACYADDRLINE1, PHARMACYADDRLINE2, PHARMACYCITYNAME, PHARMACYSTATECODE, PHARMACYZIP, 
    PHARMACYPHONENUMBER, PHARMACYNCPDPNUMBER, PHARMACYNPINUMBER, PRESCRIPTIONNUMBER, NDCNUMBER, NDCDESCRIPTION, MAILORDERIND, 
    NUMBEROFREFILLS, METRICQUANTITY, DAYSSUPPLY, INGREDIENTCOST, DISPENSINGFEE, COPAY, SALESTAX, AMOUNTPAID, CHECKMAILDATE, 
    TRANSACTIONSTATUS, PRESDEANUMBER, PRESNPINUMBER, PRESCRIBINGPHYSICIAN, PRESCRIBERSPECCODE, GENERICINDICATOR, THERAPEUTICCLASS, 
    MEDICAREBINDICATOR, MEDDCOVEREDDRUGFLAG, RISK_ENTITY_TYPE, CONTRACT_YEAR, TIME_PERIOD, CONTRACT_PERIOD_START_DT, 
    CONTRACT_PERIOD_END_DT, TRACK_MODEL, RISK_IND, PRODUCT_LABEL, PRODUCT_LVL1, PRODUCT_LVL2, PRODUCT_LVL3, PRODUCT_LVL4, 
    PRODUCT_LVL5, PRODUCT_LVL6, PRODUCT_LVL7, MARKET_LVL1, MARKET_LVL2, MARKET_LVL3, MARKET_LVL4, MARKET_LVL5, MARKET_LVL6, 
    MARKET_LVL7, MARKET_LVL8, RISK_RECON_COS_DESC, TIN, created_date, updated_date, created_by, updated_by
)
    
select NULL, :fileId, "Member not belonged to us", prvdr_id, :insId, IPA_CD, IPANAME, csv2AmgPhar.PCP_PROVIDER_NBR, PCPFIRSTNAME, PCPLASTNAME, SRC_SYS_MEMBER_NBR, 
    MFNAME, MLNAME, STRING_TO_DATE(MBRDOB), MBRGENDER, MEDICAIDNO, MEDICARENO, STRING_TO_DATE(csv2AmgPhar.ACTIVITYDATE), csv2AmgPhar.ACTIVITYMONTH, CLAIMTYPE, 
    STRING_TO_DATE(DATEFILLED), CLAIMNUMBER,     PHARMACYNUMBER, PHARMACYNAME, PHARMACYADDRLINE1, PHARMACYADDRLINE2, PHARMACYCITYNAME, 
    PHARMACYSTATECODE, PHARMACYZIP, PHARMACYPHONENUMBER, PHARMACYNCPDPNUMBER, PHARMACYNPINUMBER, PRESCRIPTIONNUMBER, NDCNUMBER,
    NDCDESCRIPTION, MAILORDERIND, NUMBEROFREFILLS, METRICQUANTITY, DAYSSUPPLY, INGREDIENTCOST, DISPENSINGFEE, COPAY, SALESTAX, AMOUNTPAID, 
    CHECKMAILDATE, TRANSACTIONSTATUS, PRESDEANUMBER, PRESNPINUMBER, PRESCRIBINGPHYSICIAN, PRESCRIBERSPECCODE, GENERICINDICATOR, 
    THERAPEUTICCLASS, MEDICAREBINDICATOR, MEDDCOVEREDDRUGFLAG, RISK_ENTITY_TYPE, CONTRACT_YEAR, TIME_PERIOD, STRING_TO_DATE(CONTRACT_PERIOD_START_DT), 
    STRING_TO_DATE(CONTRACT_PERIOD_END_DT), TRACK_MODEL, RISK_IND, csv2AmgPhar.PRODUCT_LABEL, PRODUCT_LVL1, PRODUCT_LVL2, PRODUCT_LVL3, PRODUCT_LVL4, 
    PRODUCT_LVL5, PRODUCT_LVL6, PRODUCT_LVL7, MARKET_LVL1, MARKET_LVL2, MARKET_LVL3, MARKET_LVL4, MARKET_LVL5, MARKET_LVL6, 
    MARKET_LVL7, MARKET_LVL8, RISK_RECON_COS_DESC, TIN, NOW(), NOW(), 'sarath', 'sarath'
from csv2table_amg_pharmacy csv2AmgPhar
LEFT JOIN membership_insurance mi on mi.SRC_SYS_MBR_NBR  =  convert(csv2AmgPhar.SRC_SYS_MEMBER_NBR,unsigned)
LEFT OUTER JOIN contract c on c.PCP_PROVIDER_NBR like concat ('%', trim(csv2AmgPhar.PCP_PROVIDER_NBR) ,'%')
LEFT OUTER JOIN reference_contract rc on  c.ref_contract_Id = rc.ref_contract_Id  
where mi.mbr_id is null;