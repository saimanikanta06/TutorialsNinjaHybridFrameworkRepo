package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id = "input-email")
	private WebElement emailField;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneNumberField;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(name = "agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement contiuneButton;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWaringMessage;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id= 'input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//input[@id= 'input-lastname']/following-sibling::div")
	private  WebElement lastNameWarning;
	
	@FindBy(xpath = "//input[@id= 'input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath = "//input[@id= 'input-telephone']/following-sibling::div")
	private  WebElement telephoneWarning;
	
	@FindBy(xpath = "//input[@id= 'input-password']/following-sibling::div")
	private  WebElement passwordWarning;
	
	public void enterFirstNameField(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public void enterLastNameField(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
	public void enterEmailField(String email) {
		emailField.sendKeys(email);
	}
	
	public void enterTelephoneFiled(String telephone) {
		telephoneNumberField.sendKeys(telephone);
	}
	
	public void enterPasswordField(String password) {
		passwordField.sendKeys(password);
	}
	
	public void enterConfirmPasswordField(String ConfirmPassword) {
		confirmPasswordField.sendKeys(ConfirmPassword);
	}
	
	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		contiuneButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	
	public String retriveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText=duplicateEmailAddressWaringMessage.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrivePrivacyPolicyWarning() {
		String privacyPolicyWarningText=privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retriveFirstNameWarning() {
		String firstNameWarningText=firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String retriveLastNameWarning() {
		String lastNameWarningText=lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	public String retriveEmailWarning() {
		String emailWarningText= emailWarning.getText();
		return emailWarningText;
	}
	
	public String retrivetelephoneWarning() {
		String telephoneWarningText= telephoneWarning.getText();
		return telephoneWarningText;
	}
	
	public String retrivePasswordWarning() {
		String passwordWarningText= passwordWarning.getText();
		return passwordWarningText;
	}
	
	public AccountSuccessPage registerWithMandatoryFeilds(String firstname, String lastName, String email, String telephone, String password) {
		firstNameField.sendKeys(firstname);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		telephoneNumberField.sendKeys(telephone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		privacyPolicyField.click();
		contiuneButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFeilds(String firstname, String lastName, String email, String telephone, String password) {
		firstNameField.sendKeys(firstname);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		telephoneNumberField.sendKeys(telephone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		yesNewsLetterOption.click();
		privacyPolicyField.click();
		contiuneButton.click();
		return new AccountSuccessPage(driver);
	}
	
}
