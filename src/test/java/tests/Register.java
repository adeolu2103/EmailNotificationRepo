package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
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

import pageobjects.AccountSuccessPage;
import pageobjects.HomePage;
import pageobjects.RegisterPage;

public class Register {
	
	WebDriver driver = null;
	Properties prop = null;
	RegisterPage registerPage = null;
	AccountSuccessPage accountSuccessPage = null;
	
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
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		driver = homePage.selectRegisterOption();
		
		}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	@Test(priority=1)
	public void registerWithAllFields() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstNameToRegister(prop.getProperty("firstname"));
		registerPage.enterLastNameToRegister(prop.getProperty("lastname"));
		registerPage.enterEmailToRegister(generateNewEmail());
		registerPage.enterTelephoneToRegister(prop.getProperty("telephone"));
		registerPage.enterPasswordToRegister(prop.getProperty("validpassword"));
		registerPage.enterpasswordConfirmToRegister(prop.getProperty("validpassword"));
		registerPage.selectYesNewletterOption();
		registerPage.selectPrivacyPolicy();
		driver = registerPage.clickOnContinueButton();
		accountSuccessPage = new AccountSuccessPage(driver);
		String expectedPageHeading = "Your Account Has Been Created!";
		String actualPageHeading = accountSuccessPage.retrieveSuccessPageHeading();
		Assert.assertEquals(actualPageHeading, expectedPageHeading);
		
	}
	
	@Test(priority=2)
	public void registerWithMandatoryFields() {
		registerPage = new RegisterPage(driver);
		registerPage.enterFirstNameToRegister(prop.getProperty("firstname"));
		registerPage.enterLastNameToRegister(prop.getProperty("lastname"));
		registerPage.enterEmailToRegister(generateNewEmail());
		registerPage.enterTelephoneToRegister(prop.getProperty("telephone"));
		registerPage.enterPasswordToRegister(prop.getProperty("validpassword"));
		registerPage.enterpasswordConfirmToRegister(prop.getProperty("validpassword"));
		registerPage.selectPrivacyPolicy();
		driver = registerPage.clickOnContinueButton();
		accountSuccessPage = new AccountSuccessPage(driver);
		String expectedPageHeading = "Your Account Has Been Created!";
		String actualPageHeading = accountSuccessPage.retrieveSuccessPageHeading();
		Assert.assertEquals(actualPageHeading, expectedPageHeading);
	}
	
	@Test(priority=3)
	public void registerWithoutEnteringAnyDetails() {
		registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueButton();
		String expectedFirstNameError = "First Name must be between 1 and 32 characters!";
		String actualFirstNameError = registerPage.retrieveFirstNameError();
		Assert.assertEquals(actualFirstNameError, expectedFirstNameError);
		String expectedLastNameError = "Last Name must be between 1 and 32 characters!";
		String actualLastNameError = registerPage.retrieveLastNameError();	
		Assert.assertEquals(actualLastNameError, expectedLastNameError);
		String expectedEmailError = "E-mail Address does not appear to be valid!";
		String actualEmailError = registerPage.retrieveEmailError();
		Assert.assertEquals(actualEmailError, expectedEmailError);
		String expectedTelephoneError = "Telephone must be between 3 and 32 characters!";
		String actualTelephoneError = registerPage.retrieveTelephoneError();
		Assert.assertEquals(actualTelephoneError, expectedTelephoneError);
		String expectedPasswordError = "Password must be between 4 and 20 characters!";
		String actualPasswordError = registerPage.retrievePasswordError();
		Assert.assertEquals(actualPasswordError, expectedPasswordError);
		String expectedPrivacyPolicyError = "Warning: You must agree to the Privacy Policy!";
		String actualPrivacyPolicyError = registerPage.retrievePrivacyPolicyError();
		Assert.assertEquals(actualPrivacyPolicyError, expectedPrivacyPolicyError);
	}
	
	public String generateNewEmail() {
		Date date = new Date();
		return "arun"+date.toString().replace(" ", "_").replace(":", "_")+"@gmail.com";

	}
	
	

}
