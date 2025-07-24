package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassengerDetailsPage extends BasePage {

    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Constructor
    public PassengerDetailsPage(WebDriver driver) {
        super (driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }



	//Locators

	@FindBy(xpath = "//input[@id='fname']") 
	WebElement passengerName;

	@FindBy(xpath = "//input[@id='age']") 
	WebElement passengerAge;

	@FindBy(xpath = "//div[@class='maleTab ']") 
	WebElement passengerGender;

	@FindBy(xpath = "//input[@id='contactEmail']") 
	WebElement contactEmail;

	@FindBy(xpath = "//input[@id='mobileNumber']") 
	WebElement mobileNumber;

	@FindBy(xpath = "//span[contains(@class,'checkboxWpr')]//b") 
	WebElement CheckBox;

	@FindBy(xpath = "//span[normalize-space()='Continue']") 
	WebElement FinalContinue;



	//Action Methods

	public void enterPassengerName(String Cusname) {
		passengerName.sendKeys(Cusname);
		wait.until(ExpectedConditions.elementToBeClickable(passengerName));
		js.executeScript("arguments[0].click();", passengerName);
	}

	public void enterPassengerAge(String age) {
		passengerAge.sendKeys(age);
		wait.until(ExpectedConditions.elementToBeClickable(passengerAge));
		js.executeScript("arguments[0].click();", passengerAge);
	}

	public void selectPassengerGender() {
		wait.until(ExpectedConditions.elementToBeClickable(passengerGender));
		js.executeScript("arguments[0].click();", passengerGender);
	}

	public void enterContactEmail(String email) {
		contactEmail.sendKeys(email);
		wait.until(ExpectedConditions.elementToBeClickable(contactEmail));
		js.executeScript("arguments[0].click();", contactEmail);
	}

	public void enterMobileNumber(String number) {
		mobileNumber.sendKeys(number);
		wait.until(ExpectedConditions.elementToBeClickable(mobileNumber));
		js.executeScript("arguments[0].click();", mobileNumber);

	}

	  
 
	public void clickCheckBox() {

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");  

		js.executeScript("arguments[0].click();", CheckBox);


	}  

	public void clickContinueButton() {
		wait.until(ExpectedConditions.elementToBeClickable(FinalContinue));
		js.executeScript("arguments[0].click();", FinalContinue);	    

	}


}
