drop table if exists temp_membership_cap_report ;
create temporary table temp_membership_cap_report  as
select  
 STRING_TO_DATE(CAP_PAY_DT) CAP_PAY_DT ,  STRING_TO_DATE(CAP_PERIOD) CAP_PERIOD, PAYEE_ID, PAYEE_NAME, PCP_ID,   MEMBER_ID,  STRING_TO_DATE(YMD_FROM) MEMBEREFFDT , 
 MBR_RISK_POP, CAP_KEY, RATE_TIER, REC_TYPE, FUND_RATE, FUND_AMT, MM, FUND_NAME, LOBD_ID, PRODUCT_DESC,
:fileId fileId,
:insId ins_id,
now() created_date,
now() updated_date,
'sarath' created_by,
'sarath' updated_by
  from  csv2table_cap_report c2m;

alter table temp_membership_cap_report add key MEMBER_ID(MEMBER_ID);
 

INSERT ignore into membership_cap_report 
( CAP_PAY_DT, CAP_PERIOD, PAYEE_ID, PAYEE_NAME, ins_id, prvdr_id, mbr_id, eff_start_date, MBR_RISK_POP, CAP_KEY, RATE_TIER, REC_TYPE, FUND_RATE, FUND_AMT, MM, FUND_NAME, LOBD_ID, PRODUCT_DESC,
file_id, created_date, updated_date, created_by, updated_by )
 SELECT   CAP_PAY_DT ,  date_format(CAP_PERIOD,'%Y%m'), PAYEE_ID, PAYEE_NAME, tm.ins_id,
 rc.prvdr_id,  mi.mbr_id, 
MEMBEREFFDT,  MBR_RISK_POP, CAP_KEY, RATE_TIER, REC_TYPE, FUND_RATE, FUND_AMT, MM, FUND_NAME, LOBD_ID, PRODUCT_DESC,
 tm.fileId ,tm.created_date,tm.updated_date,tm.created_by,tm.updated_by 
 from   temp_membership_cap_report tm
  left join (select distinct mbr_id, ins_id ,SRC_SYS_MBR_NBR from  membership_insurance mi) mi on  mi.SRC_SYS_MBR_NBR=tm.MEMBER_ID and mi.ins_id=tm.ins_id 
  left JOIN contract c on c.PCP_PROVIDER_NBR like concat ('%', convert(tm.PCP_ID, unsigned) ,'%')
  left JOIN reference_contract rc on  c.ref_contract_Id = rc.ref_contract_Id 
where rc.prvdr_id is not null and tm.PAYEE_ID is not null and tm.PAYEE_ID != '';
 
