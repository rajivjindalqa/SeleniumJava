package testCases;

import org.testng.annotations.Test;

import basePack.BaseClass;

public class Scenario1_Test extends BaseClass {

	
	@Test (dependsOnMethods = {"test2","test3"})
	public void test1() {
		System.out.println("Test 1");
		
	}

	@Test (groups= {"smoke","regression"}, dependsOnGroups = {"functional"})
	public void test2() {

		System.out.println("Test 2");

	}

	
	@Test (groups = {"functional"})
	public void test3() {

		System.out.println("Test 3");

	}
	
	

	
	


}
