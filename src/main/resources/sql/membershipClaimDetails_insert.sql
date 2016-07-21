insert into membership_claim_details 
 (mbr_claim_id,claim_line_seq_nbr,clm_line_adj_seq_nbr,activity_date,activity_month,claim_start_date,claim_end_date,paid_date,revenue_code,cpt_code,cpt_code_modifier1,cpt_code_modifier2,
claim_status,location_id,risk_recon_cos_des,amount_paid,allow_amt,co_insurance,co_pay,deductible,cob_paid_amount,processing_status,pharmacy_name,
quantity,npos,risk_id,runn_date,ndc,mony,drug_label_name,drug_version,pharmacy,membership_claims,psychare,simple_county,triangles,cover,
created_date,updated_date,created_by,updated_by,active_ind,file_id
 )
 SELECT distinct mc.mbr_claim_id,
 csv2AmgClaim.CLM_LINE_SEQ_NBR,csv2AmgClaim.CLM_LINE_ADJ_SEQ_NBR,
 case when csv2AmgClaim.ACTIVITYDATE is not null then DATE_FORMAT(str_to_date(csv2AmgClaim.ACTIVITYDATE , '%c/%e/%Y %H:%i'),'%Y-%c-%e') else null end ,csv2AmgClaim.ACTIVITYMONTH,
 case when csv2AmgClaim.DETAILSVCDATE is not null then DATE_FORMAT(str_to_date(csv2AmgClaim.DETAILSVCDATE, '%c/%e/%Y %H:%i'),'%Y-%c-%e') else null end,
 case when csv2AmgClaim.DETAILSVCTHRUDATE is not null then DATE_FORMAT(str_to_date(csv2AmgClaim.DETAILSVCTHRUDATE, '%c/%e/%Y %H:%i'),'%Y-%c-%e') else null end,
 case when csv2AmgClaim.CHECKDATE is not null and csv2AmgClaim.CHECKDATE != 'NULL' then DATE_FORMAT(str_to_date(csv2AmgClaim.CHECKDATE, '%c/%e/%Y %H:%i'),'%Y-%c-%e') else null end,
 csv2AmgClaim.REVENUECODE,cpt.cpt_id, NULLIF(csv2AmgClaim.PROCEDUREMODIFIER1,'') ,
 NULLIF(csv2AmgClaim.PROCEDUREMODIFIER2,'') ,
 csv2AmgClaim.CLAIMSTATUS,roomType.id,csv2AmgClaim.RISK_RECON_COS_DESC, csv2AmgClaim.BILLEDAMOUNT,csv2AmgClaim.ALLOWEDAMOUNT,
 csv2AmgClaim.COINSURANCE,csv2AmgClaim.MEMBERCOPAY,csv2AmgClaim.DEDUCTIBLE,csv2AmgClaim.COBPAIDAMOUNT,
 csv2AmgClaim.PROCESSINGSTATUS,null pharmacy_name, csv2AmgClaim.QUANTITY, null npos, csv2AmgClaim.RISK_IND,
 null runn_date, null ndc, null mony,csv2AmgClaim.DRG,csv2AmgClaim.DRG_VERSION, null pharmacy,NETAMT, null psychare, null simple_county, null triangles, 
 null cover,   now() created_date, now() updated_date,'sarath' created_by ,'sarath' updated_by,'Y', :fileId
  FROM csv2Table_Amg_Claim csv2AmgClaim
  JOIN membership_insurance mi on mi.SRC_SYS_MBR_NBR  =  convert(csv2AmgClaim.SRC_SYS_MEMBER_NBR,unsigned) and mi.ins_id=:insId
  JOIN membership_claims mc on  mc.claim_id_number= csv2AmgClaim.CLAIMNUMBER   and  mi.mbr_id =mc.mbr_id and  mi.ins_id=mc.ins_id
  left outer join cpt_measure  cpt on cpt.code =  NULLIF(csv2AmgClaim.PROCEDURECODE,'')
  LEFT OUTER JOIN lu_place_of_service roomType on roomtype.code = PLACEOFSERVICE
LEFT OUTER JOIN membership_claim_details mcd on mcd.mbr_claim_id =  mc.mbr_claim_id and mc.mbr_id=mi.mbr_id and mc.ins_id=mi.ins_id
where mcd.mbr_claim_id is null 