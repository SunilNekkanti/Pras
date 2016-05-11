package com.pfchoice.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

public class XlstoCSV 
{
public static void xls(File inputFile, File outputFile) 
{
        // For storing data into CSV files
        StringBuffer data = new StringBuffer();
        try 
        {
        FileOutputStream fos = new FileOutputStream(outputFile);

        // Get the workbook object for XLS file
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
        Cell cell;
        Row row;

        // Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) 
        {
                row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) 
                {
                        cell = cellIterator.next();
                        
                        switch (cell.getCellType()) 
                        {
                        case Cell.CELL_TYPE_BOOLEAN:
                                data.append(cell.getBooleanCellValue() + ",");
                                break;
                        
                        case Cell.CELL_TYPE_NUMERIC:
                        	
	                        	 if (DateUtil.isCellDateFormatted(cell))
	                             {
	                        		 SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy");
	                        		 String  strCellValue = dateFormat.format(cell.getDateCellValue());
	                        		 data.append(strCellValue +",");
	                             } else {
	                            	 data.append(cell.getNumericCellValue() + ",");
	                             }
                                
                                break;
                                
                        case Cell.CELL_TYPE_STRING:
                                String out=cell.getRichStringCellValue().getString();
                                if(out.contains(",")) {
                                    out = "\""+out+"\"";
                                }
                                data.append(out + ",");
                                break;

                        case Cell.CELL_TYPE_BLANK:
                                data.append("" + ",");
                                break;
                        
                        default:
                                data.append(cell + ",");
                        }
                }
                data.append('\r'); 
                data.append('\n'); 
        }

        fos.write(data.toString().getBytes());
        fos.close();
        }
        catch (FileNotFoundException e) 
        {
                e.printStackTrace();
        }
        catch (IOException e) 
        {
                e.printStackTrace();
        }
        }

}
 
