package com.pfchoice.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.pfchoice.springmvc.controller.ReportsController;

public class XlstoCSV {

	private static final Logger LOG = LoggerFactory.getLogger(ReportsController.class.getName());
	private static int rownum = 0;
	public static void xls(File inputFile, File outputFile)
			throws InvalidFormatException, FileNotFoundException, IOException {
		// For storing data into CSV files
		

		
	     if (!inputFile.exists()) {
	         System.err.println("Not found or not a file: " + inputFile.getPath());
	         return;
	     }
	     int minColumns = 1;

	     // The package open is instantaneous, as it should be.
			XLSX2CSV xlsx2csv = new XLSX2CSV(inputFile, outputFile);
			try {
				xlsx2csv.process();
			} catch (OpenXML4JException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		/*	
		StringBuilder data = new StringBuilder();
		FileOutputStream fos = new FileOutputStream(outputFile);
		InputStream  fis = new FileInputStream(inputFile);
		String fileExtn = GetFileExtension(inputFile.getName());
		
		LOG.info("xls tocsv");
		XSSFWorkbook wb_xssf; // Declare XSSF WorkBook
		HSSFWorkbook wb_hssf; // Declare HSSF WorkBook
		Sheet sheet = null; // sheet can be used as common for XSSF and HSSF
		OPCPackage pkg = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		LOG.info("creating workbook");
		
		LOG.info("started xlsx file processsing");
		
		data = parseExcel(inputFile);
		XSSFWorkbook xssfWb = new XSSFWorkbook(OPCPackage.open(inputFile));
		SXSSFWorkbook wb = new SXSSFWorkbook(xssfWb, 100);
		sheet = wb.getSheetAt(0);
		//parseExcel(inputFile);
		LOG.info("before workbook builder");
		
		ZipOutputStream zos = new ZipOutputStream(fos);
		
		ZipFile templateZip = new ZipFile(inputFile);
		Enumeration<ZipEntry> templateEntries = (Enumeration<ZipEntry>) templateZip.entries();
		try {
		  while (templateEntries.hasMoreElements()) {
		    // copy all template content to the ZipOutputStream zos
		    // except the sheet itself
		  }
		  zos.putNextEntry(new ZipEntry("sheet1"));
		  OutputStreamWriter sheetOut = new OutputStreamWriter(zos, "UTF-8");
		  try {
		    sheetOut.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		    sheetOut.write("<worksheet><sheetData>");
		    // write the content – rows and cells
		    sheetOut.write("</sheetData></worksheet>");
		  }
		  finally { sheetOut.close();
		  }
		  }
		finally { zos.close(); 
		fos.close();
		is.close();
		}
		LOG.info("created worksheet");
		
		Workbook workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheetAt(0);
		LOG.info("creating worksheet");


		Cell cell;
		Row row;

		// Decide which rows to process
		int rowStart = Math.max(2, sheet.getFirstRowNum());
		int rowEnd = Math.min(65000, sheet.getLastRowNum());

		for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
			row = sheet.getRow(rowNum);
			if (row == null) {
				// This whole row is empty
				// Handle it as needed
				continue;
			}

			int lastColumn = Math.max(row.getLastCellNum(), 1);
			for (int cn = 0; cn < lastColumn; cn++) {
				cell = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);

				if (cell == null) {
					data.append("" + ",");
				} else {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						data.append(cell.getBooleanCellValue() + ",");
						break;

					case Cell.CELL_TYPE_NUMERIC:

						if (DateUtil.isCellDateFormatted(cell)) {
							data.append(dateFormat.format(cell.getDateCellValue()) + ",");
						} else {
							data.append(NumberToTextConverter.toText(cell.getNumericCellValue()) + ",");
						}

						break;

					case Cell.CELL_TYPE_ERROR:
						data.append("" + ",");
						break;

					case Cell.CELL_TYPE_FORMULA:
						data.append("" + ",");
						break;

					case Cell.CELL_TYPE_STRING:
						data.append("\"" + cell.getRichStringCellValue().getString() + "\",");
						break;

					case Cell.CELL_TYPE_BLANK:
						data.append("" + ",");
						break;

					default:
						data.append(cell + ",");
					}
				}
			}
			data.append('\r');
			data.append('\n');
		}

		
		try {
			LOG.info("xls2csv write 1");
			fos.write(data.toString().getBytes());
			LOG.info("xls2csv write 2");
		} finally {
			LOG.info("xls2csv close");
			fos.close();
			PrasUtil.getHeapSize();
		}
	}

	private static String GetFileExtension(String fname2) {
		String fileName = fname2;
		String ext = "";
		int mid = fileName.lastIndexOf(".");
		ext = fileName.substring(mid + 1, fileName.length());
		return ext;
	}

	public static Workbook  create(POIFSFileSystem fs) throws IOException {
			return new HSSFWorkbook(fs);
		}
		
	public static Workbook  create(OPCPackage pkg) throws IOException {
			return new XSSFWorkbook(pkg);
	}
	
	public static Workbook  create(InputStream inp) throws IOException, InvalidFormatException {
		// If clearly doesn't do mark/reset, wrap up
		if(! inp.markSupported()) {
			inp = new PushbackInputStream(inp, 8);
		}
		
		if(POIFSFileSystem.hasPOIFSHeader(inp)) {
			return new HSSFWorkbook(inp);
		}
		if(POIXMLDocument.hasOOXMLHeader(inp)) {
			return new XSSFWorkbook(OPCPackage.open(inp));
		}
		throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
	}
	
	
	public static StringBuilder parseExcel(File file) throws IOException {
		StringBuilder data = new StringBuilder();
        OPCPackage container;
        try {
            container = OPCPackage.open(file.getAbsolutePath());
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(container);
            XSSFReader xssfReader = new XSSFReader(container);
            StylesTable styles = xssfReader.getStylesTable();
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            while (iter.hasNext()) {
                InputStream stream = iter.next();

              data = data.append(processSheet(styles, strings, stream));
                stream.close();
            }
            if(container != null) container.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        }
        	return data;
   }

       protected static StringBuilder  processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, InputStream sheetInputStream) throws IOException, SAXException {
    	   StringBuilder data = new StringBuilder();
        InputSource sheetSource = new InputSource(sheetInputStream);
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxFactory.newSAXParser();
            XMLReader sheetParser = saxParser.getXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(styles, strings, new SheetContentsHandler() {

            @Override
                public void startRow(int rowNum) {
                }
                @Override
                public void endRow() {
                	data.append('\r');
        			data.append('\n');
                }
                @Override
                public void cell(String cellReference, String formattedValue) {
                	data.append(formattedValue + ",");
                }
                @Override
                public void headerFooter(String text, boolean isHeader, String tagName) {

                }

            }, 
            false//means result instead of formula
            );
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
        return data;
       }

*/

	}
}
