package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public RegisterTest() {
		super();
	}

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	@BeforeMethod
	public void setUp() {
		driver = intializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		registerPage = homepage.navigateToRegisterPage();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verfiyRegisteringAnAccountWithMandatoryFields() {

		accountSuccessPage = registerPage.registerWithMandatoryFeilds(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		String actualHeading = accountSuccessPage.retriveAccountSuccessPageHeading();
		Assert.assertEquals(actualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Success page is not displayed");
	}

	@Test(priority = 2)
	public void verifyRegestringAnAccountByProvidingAllFields() {
		accountSuccessPage = registerPage.registerWithAllFeilds(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		String actualHeading = accountSuccessPage.retriveAccountSuccessPageHeading();
		Assert.assertEquals(actualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Success page is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExsistingEmailAddress() {
		accountSuccessPage = registerPage.registerWithAllFeilds(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), prop.getProperty("validEmail"),
				dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		String actualWarning = registerPage.retriveDuplicateEmailAddressWarning();
		Assert.assertEquals(actualWarning, dataProp.getProperty("duplicateEmailWarning"),
				"Warning message is not displayed");

	}

	@Test(priority = 4)
	public void veifyRegisteringAccountWithoutFillingAnyDetails() {

		registerPage.clickOnContinueButton();
		
		Assert.assertEquals(registerPage.retrivePrivacyPolicyWarning(), dataProp.getProperty("privacyPolicyWarning"),
				"Privacy Policy Warning message is not displayed");
		Assert.assertEquals(registerPage.retriveFirstNameWarning(), dataProp.getProperty("firstNameWarning"),
				"First Name Warning Message is not Displayed");
		Assert.assertEquals(registerPage.retriveLastNameWarning(), dataProp.getProperty("LastNameWarning"),
				"Last Name Warning Message is not Displayed");
		Assert.assertEquals(registerPage.retriveEmailWarning(), dataProp.getProperty("emailWarning"),
				"Email Warning Message is not Displayed");
		Assert.assertEquals(registerPage.retrivetelephoneWarning(), dataProp.getProperty("telephoneNumberWarning"),
				"Telephone Warning Message is not Displayed");
		Assert.assertEquals(registerPage.retrivePasswordWarning(), dataProp.getProperty("passwordWarning"),
				"Password Warning Message is not Displayed");

	}
}
