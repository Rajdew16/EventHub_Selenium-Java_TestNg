package EventHub.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import EventHub.abstractComponents.AbstractComponents;

public class MyBookingsPage extends AbstractComponents{

	private WebDriver driver;
	
	public MyBookingsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By bookingRef = By.cssSelector("span.booking-ref");
	By bookingDetails = By.xpath(".//button[.='View Details']");
	@FindBy(id = "booking-card") private List<WebElement> bookingCards;
	
	public List<WebElement> getBookingCardList(){
		waitForAllElementsToAppear(bookingCards);
		return bookingCards;
	}
	
	public WebElement getBookingCardByReference(String bookingReference) {
		return getBookingCardList().stream().filter( bookCard ->
		bookCard.findElement(bookingRef).getText().equals(bookingReference)).findFirst().orElse(null);
	}
	
	public BookingDetailsPage clickSpecificBookingViewDetails(String bookingReference) {
		getBookingCardByReference(bookingReference).findElement(bookingDetails).click();
		return new BookingDetailsPage(driver);
	}
}
