package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.SearchPage;

public class Search {
	
	WebDriver driver = null;
	Properties prop = null;
	HomePage homePage = null;
	SearchPage searchPage  = null;
	
	@BeforeMethod
		public void setup() throws FileNotFoundException, IOException {
			
			prop = new Properties();
			prop.load(new FileInputStream(new File("src/test/java/properties/ProjectData.properties")));
			
			String browserName = prop.getProperty("browser");
			
			if(browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}else if(browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}else if(browserName.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			}else if(browserName.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get(prop.getProperty("url"));
		}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	@Test(priority=1)
	public void searchForAValidProduct() {
		homePage = new HomePage(driver);
		homePage.enterIntoSearchField(prop.getProperty("validproduct"));
		driver = homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		Assert.assertTrue(searchPage.displayStatusOfValidProduct());
	}
		
	@Test(priority=2)
	public void searchWithAnInvalidProduct() {
		homePage = new HomePage(driver);
		homePage.enterIntoSearchField(prop.getProperty("invalidproduct"));
		driver = homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		String expectedNoProductMessage = "There is no product that matches the search criteria.";
		String actualNoProductMessage = searchPage.retrieveNoProductMessage();
		Assert.assertEquals(actualNoProductMessage, expectedNoProductMessage);
	}
	
	@Test(priority=3)
	public void searchWithoutAnyProduct() {
		homePage = new HomePage(driver);
		driver = homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		String expectedNoProductMessage = "There is no product that matches the search criteria.";
		String actualNoProductMessage = searchPage.retrieveNoProductMessage();
		Assert.assertEquals(actualNoProductMessage, expectedNoProductMessage);
		}

}
