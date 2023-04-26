package com.tutorials.qa.testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class SupplyTestData {
	public static FileInputStream ip;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    
    @DataProvider(name = "validCredentialsSupplyTestData") 
	public  static Object[][] supplyTestData (){
		Object[][] data = { {"khettar123@gmail.com", "serene0709"},
				          { "nadr912@gmail.com", "Serene2021#" },
				          { "nadr91299@gmail.com", "Serene0709"} };

		return data; 

    }
	@DataProvider(name = "TutorialsExcelDatawithDataProvider")
	public static Object[][] excelSheetDataSupply() {

			Object[][] data = SupplyTestData.getTutorialsTestDataFromExcelSheet("Login");
		return data;
					
}
	
	  public static Object[][] getTutorialsTestDataFromExcelSheet(String sheetName) {
	 try {
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorials\\qa\\testData\\\\src\\test\\java\\com\\tutorials\\qa\\testData\\TutorialsNinjaTestData.xlsx");
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
    try {
		workbook = new XSSFWorkbook(ip);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
    sheet = workbook.getSheet(sheetName);
     int rows = sheet.getLastRowNum();
     int cols = sheet.getRow(0).getLastCellNum();
     Object[][] data = new Object[rows][cols];
     for(int i=0 ; i<rows; i++) {
    	 XSSFRow row = sheet.getRow(i+1);
    	 for(int j=0; j<cols; j++) {
    		 XSSFCell cell = row.getCell(j);
    		 CellType cellType = cell.getCellType();
    		 switch (cellType) {
    		 
    		 case STRING:
    			 data[i][j] = cell.getStringCellValue();
    			break;
    			
    		 case NUMERIC:
    			 data[i][j] =Integer.toString((int)cell.getNumericCellValue());
    			 break;
    			 
    		 case BOOLEAN:
    			 data[i][j] = cell.getBooleanCellValue();
    			 break;
    		 
    		 } 
    	 }
     }
     return data;
    }
}