package listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListners implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("---------------TEST STARTED"+result.getName());

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("---------------TEST PASSED"+result.getName());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		System.out.println("---------------TEST FAILED"+result.getName());

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

		System.out.println("---------------TEST SKIPPED"+result.getName());
	}

	public void onStart(ITestContext context) {
		System.out.println("***********---------------EXECUTION STARTED"+context.getName());

	}

	public void onFinish(ITestContext context) {

		System.out.println("***********---------------EXECUTION FINISHED"+context.getName());
	}

}
