package com.filepath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	private static XSSFRow Row;
	private static XSSFCell Cell;
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	
	public static void setExcelFile(String path, String sheetname) throws Exception 
	{
		
		try
		{
		File f = new File(path); //create an object of file to access xlsx
		FileInputStream fis = new FileInputStream(f);//create an object of fileipstream to read xlsx
		wb = new XSSFWorkbook(fis);//create workbook instance refers to xlsx
		sheet = wb.getSheet(sheetname);//create sheet object
		}
		catch(Exception e) 
		{
			throw(e);
		}
	}
	
	public static int getRowCountInSheet(){
	       int rowcount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	       return rowcount;
	    }
	
	public static String getCellData(int rowNum,int colNum) throws Exception{
		try{
			Cell=sheet.getRow(rowNum).getCell(colNum);//getting cellvalue from rownum&colnum
			String CellData=Cell.getStringCellValue();//getting the string value from cell
			return CellData;
		}
		catch(Exception e) 
		{
			throw(e);
		}
	}
	
	public static int getnumericCell(int rowNum,int colNum) throws Exception {
		try{
			Cell=sheet.getRow(rowNum).getCell(colNum);//getting cellvalue from rownum&colnum
			int CellData=(int) Cell.getNumericCellValue();//getting the numeric value from cell
			return CellData;
		}
		catch(Exception e) 
		{
			throw(e);
		}
	}

	/*public static void setCellData(String result,int rownum,int colnum) throws Exception{
		try{
			//creating new cell in a row and setting value in it
			sheet.getRow(rownum).createCell(colnum).setCellValue(result);
			
			FileOutputStream opstream=new FileOutputStream(Constant.Path_Testdata+Constant.File_Testdata);
			wb.write(opstream);
			opstream.flush();
			opstream.close();
			
		}catch(Exception e){
			throw(e);
		}
	}
	*/

	
}
