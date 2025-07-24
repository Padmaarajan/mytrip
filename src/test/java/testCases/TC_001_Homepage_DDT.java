package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import testBase.BaseClass;
import utilities.DataUtils;

public class TC_001_Homepage_DDT extends BaseClass {

	
	@Test(dataProvider = "homepageData", dataProviderClass = DataUtils.class)
	public void verifyHomePage(String source, String destination, String month, String day, String year, String format, String rowIndexStr) throws InterruptedException {
		
		logger.info("**** Starting TC001_Homepage_DDT *****");

		try {
	    	  int rowIndex = Integer.parseInt(rowIndexStr);  // Convert String to int
	        Homepage hp = new Homepage(driver);

	        hp.closePopup(); 
	        hp.clickBusTab(); 
	        hp.enterSource(source);
	        hp.enterDestination(destination);
	        hp.selectDatefromExcel(rowIndex);  // 🔁 Dynamic row index
	        hp.clickSearchBuses();

	        System.out.println("Test completed for: " + source + " → " + destination);

        
		// Hard Assertion to confirm execution
		Assert.assertTrue(true, "Homepage test executed successfully.");

	}
		catch (Exception e) {
			// In case of failure, test will fail with this message
			Assert.fail("Test failed: " + e.getMessage());
		}
		logger.info("**** Finished TC001_Homepage_DDT *****");

    }
}


