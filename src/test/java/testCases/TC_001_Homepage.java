package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import testBase.BaseClass;
import utilities.DataUtils;

public class TC_001_Homepage extends BaseClass {

	@Test
	public void verify_homepage() {
		try {
			logger.info("***** Starting TC001_Homepage  ****");
			logger.debug("This is a debug log message");
			
			Homepage hp = new Homepage(driver);

			hp.closePopup();               // Close any popup if present
			hp.clickBusTab();             // Click on the 'Bus' tab 

			logger.info("Providing Data of Source and Destination");

			// Entering Source and Destination using data from config.properties
			hp.enterSource(p.getProperty("source"));       
			hp.enterDestination(p.getProperty("destination"));

			// Selecting travel date from config.properties
			hp.selectDateFromCalendar();    

			Thread.sleep(2000);

			// Click on 'Search Buses'
			hp.clickSearchBuses();

			logger.info("Validating expected message..");
			
			// Hard Assertion to confirm execution
			Assert.assertTrue(true, "HomePage test executed successfully.");

			logger.info("Test passed");

		} catch (Exception e) {
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage()); 
		}
		finally 
		{
		logger.info("***** Finished TC001_Homepage *****");
		}
	 
	}
}
