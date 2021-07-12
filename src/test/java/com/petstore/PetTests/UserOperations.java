package com.petstore.PetTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserOperations {

	String projectDir=System.getProperty("user.dir");
	public String baseUrl = "https://petstore.swagger.io/";
	public WebDriver driver;

	@BeforeTest
	public void launchBrowser() {
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", projectDir+"\\utilities\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test
	public void verifyHomepageTitle() {
		String expectedTitle = "Swagger UI";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test
	public void verifyExpandButton() {
		WebElement expandButton=driver.findElement(By.xpath("//span[text()='User']//ancestor::button"));
		String buttonExpand=expandButton.getAttribute("aria-expanded");
		if(buttonExpand.equalsIgnoreCase("false")) {
			System.out.println("Button exists and in collapsed");
		}
	}
	
	@Test
	public void clickExpand() throws InterruptedException {
		WebElement expandButton=driver.findElement(By.xpath("//span[text()='User']//ancestor::button"));		
		expandButton.click();
		Thread.sleep(30000);
		WebElement expandTablecontent = driver.findElement(By.xpath("//span[text()='User']//ancestor::button/parent::span/span[2]/table"));
		if(expandTablecontent != null) {
			System.out.println("Expand button is Clicked");
		}
	}
	@Test
	public void verifyCollapseButton() {
		WebElement expandButton=driver.findElement(By.xpath("//span[text()='User']//ancestor::button"));
		String buttonExpand=expandButton.getAttribute("aria-expanded");
		if(buttonExpand.equalsIgnoreCase("true")) {
			System.out.println("Button exists and in Expanded");
		}
	}
	@Test
	public void clickCollapseButton() throws InterruptedException {
		WebElement collapseButton=driver.findElement(By.xpath("//span[text()='User']//ancestor::button"));		
		collapseButton.click();
	}

	@AfterTest
	public void terminateBrowser() {
		driver.close();
		driver.quit();
	}
}
