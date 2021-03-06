/**
 * 
 */
package com.pfchoice.common.util;

import static com.pfchoice.common.SystemDefaultProperties.SQL_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.SQL_QUERY_EXTN;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pfchoice.springmvc.controller.service.DBConnection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * @author sarath
 * @param <T>
 *
 */
public class PrasUtil {

	private static final Logger LOG = LoggerFactory.getLogger(PrasUtil.class);

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
	
	
	/**
	 * @return
	 */
	public static Map<String, String> getDatePartList() {

		Map<String, String> datePartList = new HashMap<>();
		datePartList.put("", "Select");
		datePartList.put("month", "Month");
		datePartList.put("Year", "Year");

		return datePartList;
	}

	/**
	 * @param entityClass
	 * @param queryType
	 * @return
	 */
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
		@SuppressWarnings("unused")
		long heapsize = Runtime.getRuntime().totalMemory();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void generateReportXLSX(HttpServletResponse resp, Map parameters, JasperReport jasperReport,
			Connection conn) throws JRException, NamingException, SQLException, IOException {
		
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
		
        File outputFile = new File("c:\\softwares\\reportOutput.xlsx");
        
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        configuration.setDetectCellType(true);//Set configuration as you like it!!
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        
        byte[] bytes = new byte[(int) outputFile.length()];
            //convert file into array of bytes
        FileInputStream fileInputStream = new FileInputStream(outputFile);
	    fileInputStream.read(bytes);
	    fileInputStream.close();
	    
		resp.reset();
		resp.resetBuffer();
		 
		resp.setContentType("application/vnd.ms-excel");
	    resp.setHeader("Content-Disposition", "attachment; filename=" + "reportOutput.xlsx"); //This is downloaded as .xhtml
	      
		resp.setContentLength(bytes.length);
		ServletOutputStream ouputStream = resp.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();
		resp.flushBuffer();
	}
	
	public static void generateReport(HttpServletRequest request, HttpServletResponse response,
			DBConnection dBConnection, final  String fileName, final HashMap<String, Object> rptParams,final String reportFormat ) throws JRException, NamingException, SQLException, IOException{
		
		JasperReport jp =  getCompiledFile(fileName, request);
		switch (reportFormat) {
		case "XLS":
		case "XLSX":
		case "xlsx":
		case "xls":
			generateReportXLSX(response, rptParams, jp, dBConnection.getConnection());
			break;
		case "CSV":
		case "csv":
			 generateReportPDF(response, rptParams, jp, dBConnection.getConnection());
			break;
		case "PDF":
		case "pdf":
		default:
			 generateReportPDF(response, rptParams, jp, dBConnection.getConnection());
			break;
		}
	}
	
	
	public static String  printCriteriaQuery (Criteria  criteria ){
		CriteriaImpl criteriaImpl = (CriteriaImpl)criteria;
    	SessionImplementor session = criteriaImpl.getSession();
    	SessionFactoryImplementor factory = session.getFactory();
    	CriteriaQueryTranslator translator=new CriteriaQueryTranslator(factory,criteriaImpl,criteriaImpl.getEntityOrClassName(),CriteriaQueryTranslator.ROOT_SQL_ALIAS);
    	String[] implementors = factory.getImplementors( criteriaImpl.getEntityOrClassName() );

    	CriteriaJoinWalker walker = new CriteriaJoinWalker((OuterJoinLoadable)factory.getEntityPersister(implementors[0]), 
    	                        translator,
    	                        factory, 
    	                        criteriaImpl, 
    	                        criteriaImpl.getEntityOrClassName(), 
    	                        session.getLoadQueryInfluencers()   );

    	String sql=walker.getSQLString();
		return sql;
	}
	
	public static Date getDateWithFormatFromString(String dateInString, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
	     try {

	         Date date = formatter.parse(dateInString);
	         System.out.println(date);
	         System.out.println(formatter.format(date));
	         return date;
	     } catch (ParseException e) {
	         e.printStackTrace();
	     }
	     return null;
	     
	}
	
	public static String getDateWithFormatFromString(Date date, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            String dateString =   formatter.format(date);
	         System.out.println(formatter.format(date));
	         return dateString;
	     
	}
}
