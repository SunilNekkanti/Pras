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
	
	String QUERY_TYPE_INSERT= "_insert";
	
	String QUERY_TYPE_LOAD= "_load";
	
	String QUERY_TYPE_UPDATE= "_update";
	
	String SQL_QUERY_EXTN= ".sql";
	
	String FOLLOWUP_TYPE_HEDIS= "HEDIS_FOLLOWUP";
	
	String FOLLOWUP_TYPE_HOSPITALIZATION =  "HOSPITALIZATION_FOLLOWUP";
}
