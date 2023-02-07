package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	public WebDriver driver;
	LoginPage loginpage;
	
	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() 
	{
		driver=intializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage= new HomePage(driver);
		homePage.clickOnMyAccount();
		loginpage=homePage.selectLoginOption();
	}
	
	@DataProvider (name="ValidCredentialSupplier")
	public Object[][] getTestData()
	{
		Object[][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "ValidCredentialSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		AccountPage accoutnPage=loginpage.login(email,password);
		Assert.assertTrue(accoutnPage.getDisplayStatusOfEditYourAccountInformationOption(),"Expected link Text is not displayed");
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		loginpage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		String ActualWarning = loginpage.retriveEmailPasswordNotMatchingWarningText();
		String ExpectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarning.contains(ExpectedWarningMsg), "Expected Warning msg is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		loginpage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		String ActualWarning = loginpage.retriveEmailPasswordNotMatchingWarningText();
		String ExpectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarning.contains(ExpectedWarningMsg), "Expected Warning msg is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		loginpage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		String ActualWarning = loginpage.retriveEmailPasswordNotMatchingWarningText();
		String ExpectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarning.contains(ExpectedWarningMsg), "Expected Warning msg is not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {
		loginpage.clickOnLoginButton();
		String ActualWarning = loginpage.retriveEmailPasswordNotMatchingWarningText();
		String ExpectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarning.contains(ExpectedWarningMsg), "Expected Warning msg is not displayed");
	}
}
