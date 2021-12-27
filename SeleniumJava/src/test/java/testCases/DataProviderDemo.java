package testCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePack.BaseClass;

public class DataProviderDemo extends BaseClass {

	@Test(dataProvider = "TestData")
	public void test(String Name, String Password) {

		System.out.println(Name+"-----"+Password);

	}


	@DataProvider(name="TestData")

	public Object[][] getData() throws IOException {
		String systemPath = System.getProperty("user.dir");
		String sheetPath= systemPath+"\\excel\\data.xlsx";

		Object data[][] = testData(sheetPath, "Sheet1");
		return data;
	}




}
