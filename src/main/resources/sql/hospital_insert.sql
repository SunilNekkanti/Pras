INSERT INTO hospital (
code,name,file_id,created_date,updated_date,created_by,updated_by,active_ind
)
SELECT DISTINCT HOSP_ID, HOSP_NAME, :fileId, now(),now(),'sarath','sarath','Y' FROM 
csv2AmgHospitalization mh
LEFT OUTER JOIN hospital  h on CONVERT(h.code,UNSIGNED INTEGER)  = convert(mh.HOSP_ID ,  unsigned integer)
WHERE h.HOS_ID  IS NULL
