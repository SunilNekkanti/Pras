package com.pfchoice.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlstoCSV {

	public static void xls(File inputFile, File outputFile)
			throws InvalidFormatException, FileNotFoundException, IOException {
		// For storing data into CSV files
		StringBuilder data = new StringBuilder();
		FileOutputStream fos = new FileOutputStream(outputFile);
		InputStream excelFileToRead = new FileInputStream(inputFile);
		String fileExtn = GetFileExtension(inputFile.getName());
		XSSFWorkbook wb_xssf; // Declare XSSF WorkBook
		HSSFWorkbook wb_hssf; // Declare HSSF WorkBook
		Sheet sheet = null; // sheet can be used as common for XSSF and HSSF
		OPCPackage pkg = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		if (fileExtn.equalsIgnoreCase("xlsx")) {
			pkg = OPCPackage.open(excelFileToRead);
			wb_xssf = new XSSFWorkbook(pkg);
			sheet = wb_xssf.getSheetAt(0);
			pkg.flush();
			pkg.close();
		} else if (fileExtn.equalsIgnoreCase("xls")) {
			wb_hssf = new HSSFWorkbook(excelFileToRead);
			sheet = wb_hssf.getSheetAt(0);
		}

		Cell cell;
		Row row;

		// Decide which rows to process
		int rowStart = Math.max(2, sheet.getFirstRowNum());
		int rowEnd = Math.min(65000, sheet.getLastRowNum());

		for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
			row = sheet.getRow(rowNum);
			PrasUtil.getHeapSize();
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
			fos.write(data.toString().getBytes());
		} finally {

			fos.close();
		}
	}

	private static String GetFileExtension(String fname2) {
		String fileName = fname2;
		String ext = "";
		int mid = fileName.lastIndexOf(".");
		ext = fileName.substring(mid + 1, fileName.length());
		return ext;
	}

}
