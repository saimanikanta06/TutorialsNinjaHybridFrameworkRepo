package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

// updated comments - added more details 
public class SearchTest extends Base {
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	public SearchTest() {
		super();
	}
	@BeforeMethod
	public void setup() {
		driver=intializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		homePage= new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchPage=homePage.searchForAproduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHpValidProduct(), "valid product is not displayed");
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		searchPage=homePage.searchForAproduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retriveNoProductMessageText(), dataProp.getProperty("noProductTextInSearchResults"),
				"No product in search results is not displayed");
	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {
		searchPage =homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retriveNoProductMessageText(), dataProp.getProperty("noProductTextInSearchResults"),
				"No product in search results is not displayed");

	}

}
