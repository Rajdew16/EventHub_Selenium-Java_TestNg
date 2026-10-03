package EventHub.abstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AbstractComponents {

	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
		actions  = new Actions(driver);
	}
	
	public void waitForElementToAppear(WebElement element) {	
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToClick(WebElement element) {	
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForAllElementsToAppear(List<WebElement> element) {	
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void clickUsingActions(WebElement element) {
		actions.moveToElement(element).click().build().perform();
	}
	
	public void clickUsingActionsWithWait(WebElement element, int millis) {
		actions.pause(Duration.ofMillis(millis)).moveToElement(element).click().build().perform();
	}
	
	public void scrollUsingActions(WebElement element) {
		actions.scrollToElement(element).build().perform();
	}
	
	public void waitUsingStaticForUI(int time) throws InterruptedException {	
		Thread.sleep(time);
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",element);
	}
}
