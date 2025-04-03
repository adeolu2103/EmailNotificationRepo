package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletter;
	
	@FindBy(name="agree")
	private WebElement privacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameErrorMessage;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameErrorMessage;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailErrorMessage;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneErrorMessage;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordErrorMessage;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyErrorMessage;
	
	public void enterFirstNameToRegister(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastNameToRegister(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailToRegister(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephoneToRegister(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPasswordToRegister(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void enterpasswordConfirmToRegister(String passwordText) {
		passwordConfirmField.sendKeys(passwordText);
	}
	
	public void selectYesNewletterOption() {
		yesNewsletter.click();
	}
	
	public void selectPrivacyPolicy () {
		privacyPolicy.click();
	}
	
	public WebDriver clickOnContinueButton() {
		continueButton.click();
		return driver;
	}
	
	public String retrieveFirstNameError() {
		return firstNameErrorMessage.getText();
	}
	
	public String retrieveLastNameError() {
		return lastNameErrorMessage.getText();
	}
	
	public String retrieveEmailError() {
		return emailErrorMessage.getText();
	}
	
	public String retrieveTelephoneError() {
		return telephoneErrorMessage.getText();
	}
	
	public String retrievePasswordError() {
		return passwordErrorMessage.getText();
	}
	
	public String retrievePrivacyPolicyError() {
		return privacyPolicyErrorMessage.getText();
	}

}
