package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

public class Scenario2 {

	@Test (retryAnalyzer = listners.FailedCasesRetryAnalyser.class)
	public void test4() {
	
		System.out.println("Test 5");
			//Assert.assertTrue(false);
		}

	@Test
	public void test5() {
		System.out.println("Test 6");
	}


}
