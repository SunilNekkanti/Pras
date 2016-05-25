/**
 * 
 */
package com.pfchoice.common.util;

import static com.pfchoice.common.SystemDefaultProperties.SQL_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.SQL_QUERY_EXTN;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pfchoice.core.dao.impl.MembershipHospitalizationDaoImpl;

/**
 * @author sarath
 * @param <T>
 *
 */
public class PrasUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(MembershipHospitalizationDaoImpl.class);
	/**
	 * 
	 */
	private PrasUtil() {
		
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, String> getActiveIndMap() {
		Map<String, String> activeIndMap = new HashMap<>();
		activeIndMap.put("Y", "Yes");
		activeIndMap.put("N", "No");
		return activeIndMap;
	}

	/**
	 * 
	 * @return
	 */
	public static String getPricipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			return userDetail.getUsername();
		}

		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static SortedSet<Integer> getEffectiveYearList() {

		SortedSet<Integer> effYearList = new TreeSet<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");

		LocalDate currYear = LocalDate.now();
		LocalDate nextYear = currYear.plus(1, ChronoUnit.YEARS);
		LocalDate prevYear = currYear.minus(1, ChronoUnit.YEARS);

		effYearList.add(Integer.parseInt(prevYear.format(formatter)));
		effYearList.add(Integer.parseInt(currYear.format(formatter)));
		effYearList.add(Integer.parseInt(nextYear.format(formatter)));

		return effYearList;
	}

	/**
	 * 
	 * @return
	 */
	public static SortedSet<Integer> getHedisEffectiveYearList() {

		SortedSet<Integer> effYearList = new TreeSet<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");

		LocalDate currYear = LocalDate.now();
		LocalDate nextYear = currYear.plus(1, ChronoUnit.YEARS);

		effYearList.add(Integer.parseInt(currYear.format(formatter)));
		effYearList.add(Integer.parseInt(nextYear.format(formatter)));

		return effYearList;
	}
	
	public static <T> String getInsertQuery(final Class<T> entityClass, final String queryType) {
		String insertQuery = null;
		String entityClassName = entityClass.getSimpleName();
		try{
			Path path = FileSystems.getDefault().getPath(SQL_DIRECTORY_PATH + entityClassName + queryType + SQL_QUERY_EXTN);
			insertQuery =  new String(Files.readAllBytes(path.toAbsolutePath()), StandardCharsets.UTF_8);
		}catch(IOException e){
			LOG.warn("exception "+e.getCause());
		}
		return insertQuery;
	}
	
	
	public static void convertToCsv(List<Object[]> dataToExport, String  filename) throws  FileNotFoundException {
		PrintWriter csvWriter = new PrintWriter(new File(filename)) ;
        dataToExport.forEach(row -> {
        	for(Object column : row ){
        		if(column != null){
        			csvWriter.print(column.toString()+",");
        		}else{
        			csvWriter.print(",");
        		}
        	}
        	csvWriter.print("\n");
        });
        csvWriter.close();
    }
	
	public static Projection formProjection(String[] properties) {
        ProjectionList list = Projections.projectionList();
        for (int i = 0; i < properties.length; ++i)
            list.add(Projections.property(properties[i]), properties[i]);
        return list;
  }
}
