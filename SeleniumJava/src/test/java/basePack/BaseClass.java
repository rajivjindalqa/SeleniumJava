package basePack;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcelUtility;

public class BaseClass {
	//Extent report variables
	ExtentTest test=null;
	ExtentReports extent=null;
	ExtentSparkReporter spark=null;

	//Web driver variables
	WebDriver driver=null;
	String SystemPath=System.getProperty("user.dir");

	//Property file variables
	Properties prop=null;
	InputStream io=null;
	String value;



	//Initializing extent report 
	public void extentReport_Initiate() {
		spark = new ExtentSparkReporter("index.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("MyReport");
		spark.config().setReportName("First Report");
	}

	public void extentReport_TakeSnapshot() {
		test.pass("snapshot", MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64)).build());
	}

	public void extent_flush() {
		extent.flush();
	}

	public void getProperty(String property)   {
		try {
			prop = new Properties();
			io = new FileInputStream(SystemPath+ "/src/test/java/config/config.properties");
			prop.load(io);
			value = prop.getProperty(property);
			System.out.println(value);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//Browser launch
	public void LaunchBrowser(String Browser, String URL) throws InterruptedException {
			if(Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(options);


		} else if(Browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();	
			driver.manage().window().maximize();
		}

		driver.get(URL);
		Set<String> win = driver.getWindowHandles();
		Iterator itr = win.iterator();
		String primary = (String) itr.next();
		String ppo1 = (String) itr.next();
		String ppo2 = (String) itr.next();
		String ppo3 = (String) itr.next();
		driver.switchTo().window(ppo1);
		driver.close();
		//Thread.sleep(3000);
		driver.switchTo().window(primary);


		driver.switchTo().window(ppo2);
		driver.close();

		driver.switchTo().window(primary);

		driver.switchTo().window(ppo3);
		driver.close();

		driver.switchTo().window(primary);

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[4]/div/button")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"allow\"]")));
		driver.findElement(By.xpath("//*[@id=\"allow\"]")).click();

	}

	public void CloseBrowser() {
		driver.close();
		driver.quit();
	}


	//Excel functions 

	public void getRowCount(String FilePath) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(FilePath);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println(rowCount);
	}


	public void getCellStringValue(String FilePath, int rowNum, int ColNum) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(FilePath);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		String cellValue = sheet.getRow(rowNum).getCell(ColNum).getStringCellValue();
		System.out.println("Cell Value is: "+cellValue);
	}


	public void getCellNumericValue(String FilePath, int rowNum, int ColNum) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(FilePath);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		double numericCellValue = sheet.getRow(rowNum).getCell(ColNum).getNumericCellValue();
		System.out.println("Cell Value is :"+numericCellValue);

	}


	//------------DATA PROVIDER ----------------------------

	public Object[][] testData(String sheetpath, String sheetTab ) throws IOException {

		ExcelUtility exc = new ExcelUtility(sheetpath, sheetTab);
		int rowCount = exc.Excel_getRowCount();
		int colCount = exc.Excel_getColumnCount();

		Object data[][] = new Object[rowCount-1][colCount];

		for (int i=1;i<rowCount;i++) {
			for(int j=0;j<colCount;j++) {

				String cellData = exc.Excel_getCellStringValue(i, j);
				//System.out.print(cellData+"  |  ");
				data[i-1][j]=cellData;
			}
			//System.out.println();

		}
		return data;
	}

	//-------------------------------------------------------------------
}
