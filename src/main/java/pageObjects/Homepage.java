package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import utilities.DataUtils;

public class Homepage extends BasePage{
	
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public Homepage(WebDriver driver)
	{
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.js = (JavascriptExecutor) driver;
	}  


	// Locators
	@FindBy(xpath = "//span[@class='commonModal__close']")
	WebElement commandModelClose;

	@FindBy(xpath = "//span[@class='headerIconTextAlignment chNavText darkGreyText'][normalize-space()='Buses']")
	WebElement clickBus;

	@FindBy(xpath = "//input[@id='fromCity']")
	WebElement fromField;

	@FindBy(xpath = "//input[@id='toCity']")
	WebElement toField;

	@FindBy(id = "travelDate")
	WebElement datePicker;

	@FindBy(xpath = "//button[@id='search_button']")
	WebElement searchButton;


	// Method to Close Popup
	public void closePopup() {
		try {  
			js.executeScript("arguments[0].click();", commandModelClose);
		} catch (Exception e) {
			System.out.println("No popup found.");
		}
	}    
  
	// **Method to Click on Bus Tab**
	public void clickBusTab() {
		wait.until(ExpectedConditions.elementToBeClickable(clickBus));
		js.executeScript("arguments[0].click();", clickBus);

	}  

	// **Method to Enter Source City**
	public void enterSource(String source) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fromField));
		js.executeScript("arguments[0].click();", fromField);

		WebElement fromBox = driver.findElement(By.xpath("(//input[@placeholder='From'])[1]"));
		fromBox.sendKeys(source);
		Thread.sleep(2000); 	

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform(); 
  
	}
 
	// **Method to Enter Destination City**
	public void enterDestination(String destination) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(toField));
		js.executeScript("arguments[0].click();", toField);

		WebElement toBox = driver.findElement(By.xpath("(//input[@placeholder='To'])[1]"));
		toBox.sendKeys(destination);
		Thread.sleep(2000);

		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();


	}     

	// **Method to Select Date from congig.propeties data**
	public void selectDateFromCalendar() {  
		
		// Open the date picker
		wait.until(ExpectedConditions.elementToBeClickable(datePicker));
		js.executeScript("arguments[0].click();", datePicker);

		// ✅ Find the date using aria-label (Matches "Sat Mar 22 2025")
		String formattedDate = DataUtils.getFormattedFutureDateFromConfig();
	    System.out.println("Selecting Date: " + formattedDate);
	    
	    // XPath to locate the Next Month arrow
	    By nextArrow = By.xpath("//span[@aria-label='Next Month']");

	    while(true) {
	    	try {
	    		WebElement dateElement = driver.findElement(By.xpath("//div[@aria-label='" + formattedDate + "']"));
	    		
	    		wait.until(ExpectedConditions.elementToBeClickable(dateElement));
	    		js.executeScript("arguments[0].click();", dateElement);
	    		break;
	    	}catch (Exception e) {
	    		// Date not visible yet, click next arrow
	    		WebElement arrowElement = driver.findElement(nextArrow);
//	    		wait.until(ExpectedConditions.elementToBeClickable(arrowElement));
//	            js.executeScript("arguments[0].click();", arrowElement);
	    		arrowElement.click();
	    
	    	}
	    }  
  
	} 
	
	// **Method to Select Date from Excel DDT data**
		public void selectDatefromExcel(int currentRowIndex) {  
			
			// Open the date picker
			wait.until(ExpectedConditions.elementToBeClickable(datePicker));
			js.executeScript("arguments[0].click();", datePicker);

			// ✅ Find the date using aria-label (Matches "Sat Mar 22 2025")
			String formattedDate = DataUtils.getFormattedDateFromExcel(currentRowIndex);
		    System.out.println("Selecting Date: " + formattedDate);
		    
		    // XPath to locate the Next Month arrow
		    By nextArrow = By.xpath("//span[@aria-label='Next Month']");

		    while(true) {
		    	try {
		    		WebElement dateElement = driver.findElement(By.xpath("//div[@aria-label='" + formattedDate + "']"));
		    		
		    		wait.until(ExpectedConditions.elementToBeClickable(dateElement));
		    		js.executeScript("arguments[0].click();", dateElement);
		    		break;
		    	}catch (Exception e) {
		    		// Date not visible yet, click next arrow
		    		WebElement arrowElement = driver.findElement(nextArrow);
		    		wait.until(ExpectedConditions.elementToBeClickable(arrowElement));
		            js.executeScript("arguments[0].click();", arrowElement);
		    		
		    
		    	}
		     }  
	  
		} 
		
 
	// **Method to Click on Search Button**
	public void clickSearchBuses() {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		js.executeScript("arguments[0].click();", searchButton);

	}

	
}



