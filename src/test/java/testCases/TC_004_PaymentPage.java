package testCases;

import org.testng.Assert; 
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;
import pageObjects.BusDetailsPage;
import pageObjects.PassengerDetailsPage;
import pageObjects.PaymentPage;
import testBase.BaseClass;
import utilities.DataUtils;

public class TC_004_PaymentPage extends BaseClass {


	@Test (dataProvider = "paymentMethods", dataProviderClass = DataUtils.class)
	public void testPaymentPage(String methodName) throws InterruptedException {
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

		TC_003_CustomerDetailsPage PayPage = new TC_003_CustomerDetailsPage();

		PayPage.driver  = this.driver;
		PayPage.p = this.p;
		PayPage.logger = this.logger;

		PayPage.testCustomerDetailsPage();  //call Buspage to execute


		logger.info("Start of TC_004_PaymentPage");

		PaymentPage paypage = new PaymentPage(driver);


		 try {  
			 paypage.isPaymentMethodAvailable(methodName);  

			 paypage.getTotalPrice();
		    
		    //Hard Assertion to Confirm Execution**
	        Assert.assertTrue(true, "testPaymentPage method executed successfully.");
	        
		    } catch (Exception e) {
				logger.error("Test failed: " + e.getMessage());
				Assert.fail("Test failed: " + e.getMessage()); 
			}
			finally 
			{
			logger.info("***** Finished TC_004_PaymentPage *****");
			} 
		}
	  



	}
