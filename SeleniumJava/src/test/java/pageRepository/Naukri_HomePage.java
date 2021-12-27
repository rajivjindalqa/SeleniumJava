package pageRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Naukri_HomePage {

	public WebDriver driver = null;

	
	public Naukri_HomePage (WebDriver driver) {
		this.driver = driver;
	}
	
		
	//Home Page Objects
	By skill_Search = By.xpath("//*[@id=\"qsb-keyword-sugg\"]");
	By Location_Search = By.xpath("//*[@id=\"qsb-location-sugg\"]");
	By Search_Button = By.xpath("//*[@id=\"root\"]/div[3]/div[2]/section/div/form/div[3]/button");
	
	//Home Page Actions
	
	public void Search_Skill(String Enter_Skill) {
		driver.findElement(skill_Search).sendKeys(Enter_Skill);
	}


	public void Search_Location(String Enter_Location) {
		driver.findElement(Location_Search).sendKeys(Enter_Location);
	}

	
	public void Seach_Jobs() {
		driver.findElement(Search_Button).click();
	}
	
}
