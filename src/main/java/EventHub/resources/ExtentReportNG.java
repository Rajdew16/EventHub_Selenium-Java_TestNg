package EventHub.resources;

import java.nio.file.Files;
import java.nio.file.Path;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	
	public static ExtentReports getReportObject() {
		
		String reportPath = Path.of(System.getProperty("user.dir"),"test-report","index.html").toString();
        try {
            Files.createDirectories(
                    Path.of(System.getProperty("user.dir"), "test-report")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("EventHub");
		reporter.config().setDocumentTitle("EventHub Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","QA Tester");
		return extent;
	}
}
