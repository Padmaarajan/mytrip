package testCases;

import org.testng.Assert; 
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;
import pageObjects.BusDetailsPage;
import pageObjects.PassengerDetailsPage;
import testBase.BaseClass;

public class TC_003_CustomerDetailsPage extends BaseClass {

	
	@Test
	public void testCustomerDetailsPage() throws InterruptedException {
		
		// MANUALLY call TC_001 test case
        TC_001_Homepage homepageTest = new TC_001_Homepage();
        
        homepageTest.driver = this.driver;       // Reuse current driver
        homepageTest.p = this.p;                 // Reuse properties if needed
        homepageTest.logger = this.logger;       // Optional: reuse logger

        homepageTest.verify_homepage();          // ✅ Call homepage to execute

        
        TC_002_BusDetailsPage BusPage = new TC_002_BusDetailsPage();

        BusPage.driver  = this.driver;
        BusPage.p = this.p;
        BusPage.logger = this.logger;
        
        BusPage.verifyBusBookingPage();  //call Buspage to execute
        
        logger.info("Start of TC_003_CustomerDetailsPage");
        
        PassengerDetailsPage cus_page = new PassengerDetailsPage(driver);
        
        try {
        	

	    //  Test Scenario 12: Enter passenger's name
        	cus_page.enterPassengerName(p.getProperty("Cusname")); 

	    //  Test Scenario 13: Enter passenger's age
        	cus_page.enterPassengerAge(p.getProperty("age"));

	    //  Test Scenario 14: Select passenger's gender
        	cus_page.selectPassengerGender(); 

	    //  Test Scenario 15: Enter passenger's contact email
        	cus_page.enterContactEmail(p.getProperty("email"));

	    //  Test Scenario 16: Enter passenger's mobile number
        	cus_page.enterMobileNumber(p.getProperty("number"));

	    //  Test Scenario 17: Accept terms & conditions (Check the checkbox)
        	cus_page.clickCheckBox();   

	    //  Test Scenario 18: Click the final continue button to proceed to payment
        	cus_page.clickContinueButton(); 
	    
	    //Hard Assertion to Confirm Execution**
        Assert.assertTrue(true, "testCustomersDetailsPage method executed successfully.");
	    
        } catch (Exception e) {
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage()); 
		}
		finally 
		{
		logger.info("***** Finished TC003_CustomerDetailsPage *****");
		} 
	}
	
}
