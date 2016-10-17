CREATE DEFINER=`root`@`localhost` PROCEDURE `CLM_REPORT`(IN tableName CHAR(40), IN insId int(11), in prvdrId int(11), in rptMonth varchar(100), in category char(20), in adminRole char(1), in rosterCap varchar(100))
BEGIN

  
SET SESSION group_concat_max_len = 100000;
SET @sql = NULL;

SET @sql5 = NULL;
SET @sql5    = CONCAT( 'drop table if exists claim_report_17');

PREPARE stmt FROM @sql5;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
create table claim_report_17

select   report_month,activity_month, risk_recon_cos_des, claim_type ,is_roster,is_cap, sum(membership_claims)
from (
                select distinct  mam.mbr_id,mam.ins_id,mam.prvdr_id,mc.report_month,mc.claim_type,mc.claim_id_number, mam.is_roster,mam.is_cap,  mcd.*  from  membership_activity_month mam
                                left join membership_claims mc on mc.mbr_id=mam.mbr_id and mc.prvdr_id=mam.prvdr_id and mc.ins_id=mam.ins_id
                                left join membership_claim_details mcd on mcd.mbr_claim_id=mc.mbr_claim_id and mcd.activity_month=mam.activity_month
         join risk_recon rc on  rc.name= mcd.risk_recon_cos_des and  case when 9999 = 9999 then 1=1 else risk_recon_id =20 end
  where mam.ins_id =2    and  report_month=201608
  and case when 'isCap' = 'isCap' then is_cap='Y' else   1=1 end
  and case when 'isCap'   ='isRoster' then is_roster='Y' else 1=1 end
 
 
  UNION
                select  distinct  mc.mbr_id,mc.ins_id,mc.prvdr_id,mc.report_month,mc.claim_type,mc.claim_id_number,mam.is_roster,mam.is_cap,mcd.*  from  membership_claims mc
                                                join membership_claim_details mcd on mcd.mbr_claim_id=mc.mbr_claim_id 
                                left join membership_activity_month mam on mcd.activity_month=mam.activity_month and mc.mbr_id=mam.mbr_id and mc.prvdr_id=mam.prvdr_id and mc.ins_id=mam.ins_id
         join risk_recon rc on  rc.name= mcd.risk_recon_cos_des and  case when 9999 = 9999 then 1=1 else risk_recon_id =20 end
  where mam.ins_id =2     and  report_month=201608
  and case when 'isCap' = 'isCap' then is_cap='Y' else   1=1 end
  and case when 'isCap'   ='isRoster' then is_roster='Y' else 1=1 end
 
  )a
  join risk_recon rc on  rc.name= a.risk_recon_cos_des and  case when 9999 = 9999 then 1=1 else risk_recon_id =20 end
  group by      report_month, activity_month, risk_recon_id;
  
   SET @sql = NULL;
SELECT
  GROUP_CONCAT(DISTINCT
    CONCAT(
      'MAX(IF(activity_month = ''',
      activity_month,
      ''', `sum(membership_claims)`, NULL)) AS `',
      activity_month,'`'
    )
  ) INTO @sql
FROM claim_report_17;

SET @sql = CONCAT('SELECT report_month,risk_recon_cos_des, ', @sql, ' FROM claim_report_17
group by report_month,risk_recon_cos_des');




PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;  
  
  select @sql;

END