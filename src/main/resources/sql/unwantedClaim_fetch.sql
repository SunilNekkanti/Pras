select   claimType,MFNAME firstName ,MLNAME lastName,MBRDOB dob,MBRGENDER gender,  round(sum(claims),2) unwantedClaims
from 
(
select ins_id,prvdr_id,MFNAME,MLNAME,MBRDOB,MBRGENDER,report_month,activitymonth ,claimtype, sum(netamt)  claims from unprocessed_amg_claims group by ins_id,prvdr_id,report_month,claimtype,activitymonth,SRC_SYS_MEMBER_NBR
union all
select ins_id,prvdr_id,MFNAME,MLNAME,MBRDOB,MBRGENDER ,report_month,activitymonth ,claimtype, sum(amountpaid) claims from unprocessed_amg_pharmacy_claims group by ins_id,prvdr_id,report_month,activitymonth ,SRC_SYS_MEMBER_NBR
union all
     select   mc.ins_id,mc.prvdr_id,m.mbr_lastname,m.Mbr_FirstName,m.Mbr_DOB,lg.code MBRGENDER,  mc.report_month, mcd.activity_month,   mc.claim_type,  sum(round( mcd.membership_claims  ,2)) claims
 from       membership_claims mc 
	 join membership m on m.mbr_id=mc.mbr_id
	 join   lu_gender lg on lg.gender_id= m.Mbr_GenderID 
     join membership_claim_details mcd on mc.mbr_claim_id=mcd.mbr_claim_id 
     left join (select  prvdr_id, ins_id, mbr_id, CAP_PERIOD  from  membership_cap_report  group by prvdr_id,ins_id,mbr_id  ,CAP_PERIOD  HAVING sum(FUND_AMT) > 0  ) mcp on mcp.mbr_id = mc.mbr_id and mcp.ins_id=mc.ins_id and mc.prvdr_id=mcp.prvdr_id and mcd.activity_month=mcp.cap_period
  where case when mcp.mbr_id is not null then  case when mcp.prvdr_id is not null then mcp.cap_period is null else mcp.prvdr_id is   null end else mcp.mbr_id is null end  
 group by  mc.ins_id,mc.prvdr_id,m.mbr_lastname,m.Mbr_FirstName,m.Mbr_DOB,mcd.activity_month,mc.claim_type

) a 
where prvdr_id is not null and   a.ins_id = :insId and a.prvdr_id = :prvdrId and a.report_month =:reportMonth and a.activitymonth =:activityMonth
group by ins_id,prvdr_id,claimtype,report_month,activitymonth,MFNAME,MLNAME,MBRDOB 
order by ins_id,prvdr_id,report_month,activitymonth,claimtype,MFNAME,MLNAME,MBRDOB ;


