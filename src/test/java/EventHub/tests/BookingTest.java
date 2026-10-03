package EventHub.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EventHub.pageObjects.BookTicketsPage;
import EventHub.pageObjects.BookingDetailsPage;
import EventHub.pageObjects.DashboardPage;
import EventHub.pageObjects.MyBookingsPage;
import EventHub.testComponents.BaseTest;

public class BookingTest extends BaseTest{

	@Test(dataProvider = "BookingData")
	public void bookEventFromDashboard(HashMap<String, String> data) {
		
		DashboardPage dashboardPage = loginPage.loginUsingValidCredential(data.get("email"), data.get("password"));
		Assert.assertTrue(dashboardPage.getFeatureEventsText().equals("Featured Events"));
		BookTicketsPage bookTicketsPage = dashboardPage.bookNowSpecificEvent(data.get("cardName"));
		bookTicketsPage.bookOneTicket(data.get("fullname"),data.get("email"),data.get("phone"));
		String bookingRef = bookTicketsPage.getBookingReference();
		Assert.assertEquals(bookingRef.charAt(0),data.get("cardName").charAt(0));
		MyBookingsPage myBookingsPage = bookTicketsPage.clickOnViewMyBookings();
		BookingDetailsPage bookingDetailsPage = myBookingsPage.clickSpecificBookingViewDetails(bookingRef);
		Assert.assertEquals(bookingDetailsPage.getEventNameText().charAt(0),bookingRef.charAt(0));
		
	}
	
	@DataProvider(name = "BookingData")
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty
				("user.dir")+ "\\src\\test\\java\\EventHub\\testData\\ValidData.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
