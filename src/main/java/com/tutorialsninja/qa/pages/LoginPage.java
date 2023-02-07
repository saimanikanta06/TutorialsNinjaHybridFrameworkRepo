package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-email")
	WebElement emailAddressField;
	
	@FindBy(id = "input-password")
	WebElement passwordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	WebElement emailPasswordNotMatchingWarning;
	
	public void enterEmailAddress(String email) {
		emailAddressField.sendKeys(email);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	public AccountPage login(String email, String password) {
		emailAddressField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
	}
	public String retriveEmailPasswordNotMatchingWarningText() {
		String warnignText=emailPasswordNotMatchingWarning.getText();
		return warnignText;
	}
}
