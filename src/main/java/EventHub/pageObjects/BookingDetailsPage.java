package EventHub.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import EventHub.abstractComponents.AbstractComponents;

public class BookingDetailsPage extends AbstractComponents {

	public BookingDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[.='Event']/following-sibling::span") private WebElement eventName;
	
	public String getEventNameText() {
		return eventName.getText();
	}
}
