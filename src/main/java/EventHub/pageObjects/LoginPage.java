package EventHub.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import EventHub.abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {

	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(id = "email") private WebElement username;
	@FindBy(id = "password") private WebElement password;
	@FindBy(id = "login-btn") private WebElement signInButton;
	@FindBy(xpath = "//p[.='Invalid email or password']") private WebElement invalidErrorMessage;
	
	
	public DashboardPage loginUsingValidCredential(String userName , String userPassword) {
		username.sendKeys(userName);
		password.sendKeys(userPassword);
		signInButton.click();
		DashboardPage dashboardPage = new DashboardPage(driver);
		return dashboardPage;
	}
	
	public String getInavlidEmailPasswordMessage() {
		return invalidErrorMessage.getText();
	}
}
