package pageObjects;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PaymentPage extends BasePage {

	WebDriverWait wait;
	JavascriptExecutor js;

	// Constructor
	public PaymentPage(WebDriver driver) {
		super (driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.js = (JavascriptExecutor) driver;
	}
	
     	//Locators

	 @FindBy(xpath = "(//span[@class='styles__PriceText-sc-169rw1l-2 cUooPs'])")
	    WebElement TotalPrice;

	
		
		
		//Action Methods  

	    // ✅ Method to validate if a payment method is available
	    public boolean isPaymentMethodAvailable(String methodName) {
	        String dynamicXPath = "//span[normalize-space()='" + methodName + "']";
	        List<WebElement> elements = driver.findElements(By.xpath(dynamicXPath));

	        if (!elements.isEmpty()) {
	            System.out.println("✅ Payment method '" + methodName + "' is available.");
	            return true;
	        } else {
	            System.out.println("❌ Payment method '" + methodName + "' is NOT available.");
	            return false;
	        }
	        
	    }
	    
	    public String getTotalPrice() {
	        wait.until(ExpectedConditions.visibilityOf(TotalPrice));
	        String priceText = TotalPrice.getText().trim();
	        System.out.println("Total Price: " + priceText);
	        return priceText;
	    }
	    
	  
		  
		
		 
}
