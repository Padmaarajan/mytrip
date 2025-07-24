package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import testBase.BaseClass;

public class DataUtils extends BaseClass {

	//Date Provider from config
	
	public static String getFormattedFutureDateFromConfig() {
        try {
            String month = p.getProperty("dateMonth");
            int day = Integer.parseInt(p.getProperty("dateDay"));
            int year = Integer.parseInt(p.getProperty("dateYear"));
            String format = p.getProperty("dateFormat");

            // Parse the month
            SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            Date parsedMonth = monthFormat.parse(month);

            // Create and set calendar
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedMonth);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.DAY_OF_MONTH, day);

            // Format the date
            SimpleDateFormat outputFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            return outputFormat.format(cal.getTime());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


	// ✅ Method to Generate Formatted Date using Excel + properties
	public static String getFormattedDateFromExcel(int rowIndex) {
	    try { 
	        FileInputStream fis = new FileInputStream("D:\\JAVA\\Pracitse2025\\mytrip\\testData\\myTripData_01.xlsx");
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheet("Sheet");

	        if (sheet == null) {
	            workbook.close();
	            fis.close();
	            throw new RuntimeException("Sheet 'Sheet' not found in Excel file.");
	        }
	        
	        
	        // Assuming you want the first row of actual data (row index 1)
	        String month = sheet.getRow(rowIndex).getCell(2).toString();  // Column index 2 = dateMonth
	        int day = (int) sheet.getRow(rowIndex).getCell(3).getNumericCellValue();
	        int year = (int) sheet.getRow(rowIndex).getCell(4).getNumericCellValue();
	        String format = p.getProperty("dateFormat");  // Still taken from config

	        workbook.close(); 
	        fis.close();

	        // Parse month name
	        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
	        Date parsedMonth = monthFormat.parse(month);

	        // Set Calendar
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(parsedMonth);
	        cal.set(Calendar.YEAR, year);
	        cal.set(Calendar.DAY_OF_MONTH, day);

	        // Format Date
	        SimpleDateFormat outputFormat = new SimpleDateFormat(format, Locale.ENGLISH);
	        return outputFormat.format(cal.getTime());
 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	   
 
	//Data Provider for Data Driven Testing in Homepage
	   
		@DataProvider(name = "homepageData")
		public Object[][] getData() throws IOException {
			
			String path=".\\testData\\myTripData_01.xlsx";
		 	
			ExcelUtility xlutil = new ExcelUtility(path);
			
			int totalrows=xlutil.getRowCount("Sheet");	    
			int totalcols=xlutil.getCellCount("Sheet",1);
			 
			String[][] Homedata=new String[totalrows][totalcols+1]; // +1 for row index
			
			for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
			{		
				for(int j=0;j<totalcols;j++)  //0    i is rows j is col
				{
					Homedata[i-1][j]= xlutil.getCellData("Sheet",i, j);  //1,0
				}
				Homedata[i - 1][totalcols] = String.valueOf(i); // Add row index as last element
			}
		return Homedata;//returning two dimension array
					
		}
		
		@DataProvider(name = "paymentMethods")
		public static Object[][] getPaymentMethods() {
			return new Object[][] {
				{"UPI Options"},
				{"Credit & Debit Cards"},
				{"Book Now Pay Later"},
				{"Net Banking"},
				{"Gift Cards & e-wallets"},
				{"GooglePay"}
			};
		}
			
}














