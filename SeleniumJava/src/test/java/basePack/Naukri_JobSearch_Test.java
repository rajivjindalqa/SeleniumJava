package basePack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pageRepository.Naukri_HomePage;

public class Naukri_JobSearch_Test extends BaseClass {
	 
	
	
	@BeforeSuite
	public void before() {
		
		extentReport_Initiate();
	}
	
	Logger logger = LogManager.getLogger(Naukri_JobSearch_Test.class);
	
	@Test
	public void Seach_Job() throws InterruptedException   {
	
	
		//extentReport_Initiate();
		LaunchBrowser("chrome","https://www.naukri.com/");
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	
		Naukri_HomePage NauHomeObj = new Naukri_HomePage(driver);
		NauHomeObj.Search_Skill("Testing");
		test = extent.createTest("Search Job Test").assignAuthor("Rajiv JIndal");
		extentReport_TakeSnapshot();

		NauHomeObj.Search_Location("Gurgaon");
		extentReport_TakeSnapshot();

		NauHomeObj.Seach_Jobs();
		extentReport_TakeSnapshot();
		
		test.pass("Job Searched successfully");
		
		logger.info("This is informational");
		logger.trace("This is trace message");
		logger.error("This is error message");
		logger.warn("This is warning message");
		logger.fatal("This is fatal message");
		
		driver.close();
		

	}
	@AfterSuite
	public void after() {
		extent_flush();
	}
	

}
