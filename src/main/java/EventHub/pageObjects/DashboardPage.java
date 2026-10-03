package EventHub.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import EventHub.abstractComponents.AbstractComponents;

public class DashboardPage extends AbstractComponents {

	private WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(xpath = "//h2[.='Featured Events']") private WebElement featureEvenetsLabel;
	@FindBy(css = "#event-card") private List<WebElement> allEventCards;
	
	public String getFeatureEventsText() {
		return featureEvenetsLabel.getText();
	}
	
	public List<WebElement> getEventCard() {
		waitForAllElementsToAppear(allEventCards);
		return allEventCards;
	}
	
	public BookTicketsPage bookNowSpecificEvent(String cardName) {
		
		getEventCard().stream().filter(card -> 
		card.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(cardName))
		.findFirst().ifPresentOrElse(card -> {
			
			WebElement bookNowButton =
		            card.findElement(By.cssSelector("#book-now-btn"));		
			
			clickUsingActions(bookNowButton);
		}, () -> {
			throw new RuntimeException("Event Card not found!");
		});
		
		BookTicketsPage bookTicketsPage = new BookTicketsPage(driver);
		return bookTicketsPage;
	}
}
