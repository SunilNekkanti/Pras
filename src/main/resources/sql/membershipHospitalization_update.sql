UPDATE membership_hospitalization mh
JOIN membership_insurance mi on  mh.mbr_id =  mi.mbr_id
JOIN csv2AmgHospitalization csv2mh on mi.SRC_SYS_MBR_NBR  =  csv2mh.MEMBER_ID

set  mh.exp_dc_date =  DATE_FORMAT(str_to_date(csv2mh.exp_dc_dt , '%c/%e/%Y %H:%i'),'%Y-%c-%e')