package testCases;

import org.testng.annotations.Test;

import basePack.BaseClass;

public class Scenario1Test extends BaseClass {

	
	@Test ()
	public void test11() {
		System.out.println("Test 1 is teh final one ");
		
	}

	@Test ()
	public void test22() {

		System.out.println("Test 2 is teh final one");

	}

	
	@Test (groups = {"functional"})
	public void test33() {

		System.out.println("Test 3");

	}
	
	
}
