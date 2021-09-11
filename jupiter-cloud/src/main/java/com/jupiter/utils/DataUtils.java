package com.jupiter.utils;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtils {

	public Object[][] getData(String methodName) {

		Object[][] tabArray = null;
		
		try {

			FileInputStream file = new FileInputStream(new File(new File("").getAbsolutePath() + File.separator + "Data" + File.separator + methodName + ".xlsx"));
			
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Integer rowcount = sheet.getPhysicalNumberOfRows();

			for (int rowi = 0; rowi < rowcount; rowi++) {

				if(rowi!=0) {
					Row row = sheet.getRow(rowi);
					if (tabArray == null) {

						tabArray = new Object[(rowcount-1)][row.getPhysicalNumberOfCells()];
					}

					for (int col = 0; col < row.getPhysicalNumberOfCells(); col++) {

						tabArray[rowi-1][col] = row.getCell(col).getStringCellValue();
					}
				} 
				

			}
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabArray;
	}
		
	
	public static void main(String[] args) {
		new DataUtils().getData("");
	}
}
