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
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pfchoice.core.dao.impl.MembershipHospitalizationDaoImpl;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

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
		try {
			Path path = FileSystems.getDefault()
					.getPath(SQL_DIRECTORY_PATH + entityClassName + queryType + SQL_QUERY_EXTN);
			insertQuery = new String(Files.readAllBytes(path.toAbsolutePath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOG.warn("exception " + e.getCause());
		}
		return insertQuery;
	}

	public static void convertToCsv(List<Object[]> dataToExport, String filename) throws FileNotFoundException {
		PrintWriter csvWriter = new PrintWriter(new File(filename));
		dataToExport.forEach(row -> {
			for (Object column : row) {
				if (column != null) {
					csvWriter.print(column.toString() + ",");
				} else {
					csvWriter.print(",");
				}
			}
			csvWriter.print("\n");
		});
		csvWriter.close();
	}

	public static void getHeapSize() {
		long heapsize = Runtime.getRuntime().totalMemory();
		System.out.println("heapsize is :: " + heapsize);
	}

	public static JasperReport getCompiledFile(String fileName, HttpServletRequest request) throws JRException {
		java.io.File reportFile = new java.io.File(
				request.getSession().getServletContext().getRealPath("/jasper/" + fileName + ".jasper"));
		// If compiled file is not found, then compile XML template
		if (!reportFile.exists()) {
			JasperCompileManager.compileReportToFile(
					request.getSession().getServletContext().getRealPath("/jasper/" + fileName + ".jrxml"),
					request.getSession().getServletContext().getRealPath("/jasper/" + fileName + ".jasper"));
		}
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void generateReportPDF(HttpServletResponse resp, Map parameters, JasperReport jasperReport,
			Connection conn) throws JRException, NamingException, SQLException, IOException {
		byte[] bytes = null;
		bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
		resp.reset();
		resp.resetBuffer();
		resp.setContentType("application/pdf");
		resp.setContentLength(bytes.length);
		ServletOutputStream ouputStream = resp.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();
	}

}
