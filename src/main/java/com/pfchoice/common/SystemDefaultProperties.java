package com.pfchoice.common;

/**
 *
 * Some system level properties.
 *
 * @author Sarath
 */
public interface SystemDefaultProperties {

	String MAP_RANGE_DEFAULT = "1000";

	Integer DEFAULT_PAGE_NO = 0;

	Integer SMALL_LIST_SIZE = 10;

	Integer MEDIUM_LIST_SIZE = 100;

	Integer LARGE_LIST_SIZE = 10000;

	Integer HUGE_LIST_SIZE = 100000;

	String ID = "username";

	String CREDENTIAL = "credential";

	String SQL_DIRECTORY_PATH = "c:\\softwares\\gitworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Pras\\WEB-INF\\classes\\sql\\";

	String FILES_UPLOAD_DIRECTORY_PATH = "C:\\ProgramData\\MySQL\\MySQL Server 5.6\\Uploads\\";

	String EMAIL_ATTACHMENTS_FILES_DIRECTORY_PATH = "C:\\Softwares\\";

	String QUERY_TYPE_INSERT = "_insert";
	
	String QUERY_TYPE_BH_INSERT = "_bh_insert";

	String QUERY_TYPE_LOAD = "_load";
	
	String QUERY_TYPE_BH_LOAD = "_bh_load";

	String QUERY_TYPE_UPDATE = "_update";
	
	String QUERY_TYPE_BH_UPDATE = "_bh_update";

	String SQL_QUERY_EXTN = ".sql";

	String FOLLOWUP_TYPE_HEDIS = "HEDIS_FOLLOWUP";

	String FOLLOWUP_TYPE_HOSPITALIZATION = "HOSPITALIZATION_FOLLOWUP";

	String FOLLOWUP_TYPE_CLAIM = "CLAIM_FOLLOWUP";

	String FILE_TYPE_AMG_MBR_HOSPITALIZATION = "AMG Membership Hospitalization1";

	String FILE_TYPE_AMG_MBR_CLAIM = "AMG Membership Claim";

	String FILE_TYPE_BH_MBR_CLAIM = "BH Membership Claim";
	
	String FILE_TYPE_AMG_MBR_ROSTER = "AMG Membership Roster";

	String FILE_TYPE_BH_MBR_ROSTER = "BH Membership Roster";

	int FILTER_BY_PROCESSING_DATE = 0;

	int FILTER_BY_HOSPOTALIZATION_DATE = 1;

	int ALL = 9999;

	int CLAIM = 0;

	int HOSPITALIZATION = 1;
	
	int ACCEPTABLE_CLAIM = 1;
	
	int UNACCEPTABLE_CLAIM = 2;

}
