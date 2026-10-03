package EventHub.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


import EventHub.pageObjects.DashboardPage;
import EventHub.pageObjects.LoginPage;
import EventHub.testComponents.BaseTest;
import EventHub.testComponents.Retry;

public class LoginTest extends BaseTest {

	@Test
	public void validLogin() throws IOException {
		
		DashboardPage dashboardPage = loginPage.loginUsingValidCredential("raj.maxy98@gmail.com","Raj@1234");
		Assert.assertEquals(dashboardPage.getFeatureEventsText(),"Featured Events");
		
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void invalidLogin() throws IOException {
		
		loginPage.loginUsingValidCredential("invalid@gmail.com","invalid");
		Assert.assertTrue(loginPage.getInavlidEmailPasswordMessage().equals("Invalid email or password"));
		
	}
}
