package EventHub.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import EventHub.pageObjects.LoginPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {
		
		Properties configReader = new Properties();
		FileInputStream fis = new FileInputStream
				(System.getProperty("user.dir") + "\\src\\main\\java\\EventHub\\resources\\GlobalData.properties");
		configReader.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: configReader.getProperty("browser");
		
		String environment = System.getProperty("environment") != null ? System.getProperty("environment") 
				: configReader.getProperty("environment");
		String baseUrl = configReader.getProperty(environment);
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--force-device-scale-factor=1");
			}
			driver = new ChromeDriver(options);
		}else if(browserName.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		if (!browserName.contains("headless")) {
		    driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(baseUrl);
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		
		String jsonContent = Files.readString(Paths.get(filepath));
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
		
	}
	
	public String getScreenShot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "\\test-report\\" + testcaseName + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir") + "\\test-report\\" + testcaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException{
		initializeDriver();
		loginPage = new LoginPage(driver);
		return loginPage;
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
