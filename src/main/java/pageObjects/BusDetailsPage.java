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
import org.openqa.selenium.support.ui.WebDriverWait;
  

public class BusDetailsPage extends BasePage {

	WebDriverWait wait;
	private JavascriptExecutor js;


	public BusDetailsPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.js = (JavascriptExecutor) driver;
	}

	//Locators

	// AC Filter Option
	@FindBy(xpath = "(//div[contains(@class,'FilterTabs_tabSection__DfwGX')])[1]")
	WebElement acFilter;

	// Sleeper Seat Type
	@FindBy(xpath = "(//li//div[@class='FilterTabs_tabSection__DfwGX '])[4]")
	WebElement sleeperSeat;

	// Sort Price Option
	@FindBy(xpath = "//div[@data-testid='sort-tab-price']")
	WebElement SortPrice;


	@FindBy(xpath = "//div[@class='BusCard_ListingCard__MOUj5']")
	WebElement TotalBusesFound;

	@FindBy(xpath = "//button[@class='BusCard_primaryBtn__iiHt1 ']")  
	WebElement firstBusSelectButton;

	@FindBy(xpath = "(//div[@class='makeFlex PickUpDropSelection_pickDropItem__YQFG2 '])[1]")  
	WebElement BoardingPoint;

	@FindBy(xpath = "//div[text()='Drop Points']/following::div[@class='makeFlex PickUpDropSelection_pickDropItem__YQFG2 '][1]")  
	WebElement DropPoint;
	
	@FindBy(xpath = "//button[text()='Continue']")  
	WebElement ContinueButton;
	
	//Action Methods

	// **Method to Click on acFilter**
	public  void clickAcFilter() {
		wait.until(ExpectedConditions.elementToBeClickable(acFilter));
		js.executeScript("arguments[0].click();", acFilter);
	}

	// **Method to Click on Sleeper Seat Type**
	public void selectSleeperSeat() {
		wait.until(ExpectedConditions.elementToBeClickable(sleeperSeat));
		js.executeScript("arguments[0].click();", sleeperSeat);
	}

	// **Method to Click on Cheapest Price Option**
	public void selectCheapestOption() {
		wait.until(ExpectedConditions.elementToBeClickable(SortPrice));
		js.executeScript("arguments[0].click();", SortPrice);
	}

	// **Method to Get Total Buses Found**
	public String getTotalBusesFound() {
		wait.until(ExpectedConditions.visibilityOf(TotalBusesFound));
		return TotalBusesFound.getText();
	}

	// **Method to Click on the First Bus Select Button**
	public void clickFirstBusSelectButton() {
		wait.until(ExpectedConditions.elementToBeClickable(firstBusSelectButton));
		js.executeScript("arguments[0].click();", firstBusSelectButton);
	}

//Locators
	//div[@class='SeatMapContainer_seat__FPUJ3 undefined']   - Total seats
	// SeatMapContainer_font10__sDtwv lightGreyText   - taken
	// SeatMapContainer_font10__sDtwv false            - available
	
	public void busSeatSelection () {

		// Locate all seat elements
		List<WebElement> allSeats = driver.findElements(By.xpath("//div[@class='SeatMapContainer_seat__FPUJ3 undefined']"));

		for (WebElement seat : allSeats) {
			try {    
				// Check if the seat contains 'seat_seater_available' inside a <span>
				WebElement seatStatus = seat.findElement(By.xpath("//div[@class='SeatMapContainer_font10__sDtwv false']"));

				if (seatStatus != null) {
					// Extract the seat price  
					WebElement priceElement = seat.findElement(By.xpath("//div[@class='SeatMapContainer_font10__sDtwv false']"));
					String seatPrice = priceElement.getText();

					System.out.println("Selecting seat with price: " + seatPrice);

					// Click on the seat to select it
					seat.click();
					break;  // Stop after selecting the first available seat
				}

			} catch (Exception e) {
				// Continue to the next seat if an exception occurs (seat not available)
				System.out.println("Seat not available or already booked.");
			}
		} 
 
	}

	public void BoardingPoints () {
		// Click Action of Continue Button
		wait.until(ExpectedConditions.elementToBeClickable(BoardingPoint));
		js.executeScript("arguments[0].click();", BoardingPoint);

	}
	
	public void DropPoints () {
		// Click Action of Continue Button
		wait.until(ExpectedConditions.elementToBeClickable(DropPoint));
		js.executeScript("arguments[0].click();", DropPoint);

	}
	
	
	public void clickContinueButton () {
		// Click Action of Continue Button
		wait.until(ExpectedConditions.elementToBeClickable(ContinueButton));
		js.executeScript("arguments[0].click();", ContinueButton);

	}


}
