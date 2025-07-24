package testCases;

import org.testng.Assert; 

import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;
import pageObjects.BusDetailsPage;
import testBase.BaseClass;
import testCases.TC_001_Homepage;

public class TC_002_BusDetailsPage extends BaseClass {

	@Test
	public void verifyBusBookingPage() throws InterruptedException {
		
		// MANUALLY call TC_001 test case
        TC_001_Homepage homepageTest = new TC_001_Homepage();
        
        homepageTest.driver = this.driver;       // Reuse current driver
        homepageTest.p = this.p;                 // Reuse properties if needed
        homepageTest.logger = this.logger;       // Optional: reuse logger

        homepageTest.verify_homepage();          // ✅ Call homepage test first

        logger.info("Start of TC_002_BusDetailsPage");

        
        logger.info("Start of TC_002_BusDetailsPage");
		
		BusDetailsPage BusPage = new BusDetailsPage(driver);
		
		 try {
		Thread.sleep(2000);

		//  Test Scenario 7: Apply AC filter to refine bus search
		BusPage.clickAcFilter();
   
		//  Test Scenario 8: Apply additional filters for better selection
		BusPage.selectSleeperSeat();       // Select Sleeper Seat
		BusPage.selectCheapestOption();    // Sort by Cheapest

		//  Test Scenario 9: Fetch and display the total count of available buses
		String totalBuses = BusPage.getTotalBusesFound();
		System.out.println("Total Buses Found: " + totalBuses);

		// Test Scenario 10: Select the first available bus from the filtered results
		BusPage.clickFirstBusSelectButton();

		//  Test Scenario 11: Choose an available bus seat and proceed to the next step
		BusPage.busSeatSelection();
		
		BusPage.BoardingPoints();
		
		BusPage.DropPoints();
		
		BusPage.clickContinueButton(); 
		
		  //Hard Assertion to Confirm Execution**
        Assert.assertTrue(true, "BusDetailsPage Test executed successfully.");
        
			}
			catch (Exception e) {
				// In case of failure, test will fail with this message
				Assert.fail("Test failed: " + e.getMessage());
			}
		 finally{
				logger.info("**** Finished TC002_BusDetailsPage*****");
			}

	    }
	}


