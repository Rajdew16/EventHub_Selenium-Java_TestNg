package EventHub.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import EventHub.abstractComponents.AbstractComponents;

public class BookTicketsPage extends AbstractComponents {

	private WebDriver driver;
	
	public BookTicketsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	@FindBy(css = "#customerName") private WebElement fullNameInputField;
	@FindBy(css = "#customer-email") private WebElement emailInputField;
	@FindBy(css = "#phone") private WebElement phoneInputField;
	@FindBy(css = "#confirm-booking") private WebElement confirmBookingButton;
	@FindBy(css = ".booking-ref") private WebElement bookingRefValue;
	@FindBy(xpath = "//button[normalize-space()='View My Bookings']") private WebElement viewMyBookingsButton;
	
	public void bookOneTicket(String fullname,String email,String phone) {
		fullNameInputField.sendKeys(fullname);
		emailInputField.sendKeys(email);
		phoneInputField.sendKeys(phone);
		confirmBookingButton.click();
	}
	
	public String getBookingReference() {
		waitForElementToAppear(bookingRefValue);
		return bookingRefValue.getText();
	}
	
	public MyBookingsPage clickOnViewMyBookings() {
		waitForElementToAppear(viewMyBookingsButton);
		viewMyBookingsButton.click();
		MyBookingsPage myBookingsPage = new MyBookingsPage(driver);
		return myBookingsPage;
	}
	
	
}
