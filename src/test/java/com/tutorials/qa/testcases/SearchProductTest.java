package com.tutorials.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.qa.pages.HomePage;
import com.tutorials.qa.pages.LoginPage;
import com.tutorials.qa.pages.SearchPage;
import com.tutorials.qa.testbase.TestBase;

public class SearchProductTest extends TestBase {
	public SearchProductTest() throws Exception {
		super();
	}

	WebDriver driver;
	public static SoftAssert softassert = new SoftAssert();

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenAplication(prop.getProperty("browserName"));
	}

	@Test(priority = 1)
	public void VerifySearchingWithAnExistingProductName() {

		 HomePage homePage = new HomePage(driver);
		 homePage.enterProductNameInSearchBoxField(dataprop.getProperty("existingProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataprop.getProperty("existingProduct"));
		 homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//button[@class ='btn btn-default btn-lg']")).click();
		 SearchPage searchPage = new SearchPage(driver);
		 Assert.assertTrue(searchPage.displayTheStatusOfIMacProduct(),"valid Product is not displayed");
		//Assert.assertTrue(driver.findElement(By.linkText("iMac")).isDisplayed());

	}

	@Test(priority = 2)
	public void VerifySearchingWithANonExistingProductName() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterProductNameInSearchBoxField(dataprop.getProperty("nonExistingProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataprop.getProperty("nonExistingProduct"));
		homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//button[@class ='btn btn-default btn-lg']")).click();
		SearchPage searchPage = new SearchPage(driver);
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		/*String actualWarningMessage = driver.findElement(By.xpath("//div[@id ='content']/h2/following-sibling::p"))
				.getText();*/
		String expectedSearchMessage = dataprop.getProperty("noProductmatchesInSearchResult");
		softassert.assertEquals(actualSearchMessage, (expectedSearchMessage),
				"no product message in the search result is displayed");
		softassert.assertAll();
	}

	@Test(priority = 3)
	public void verifySearchingWithoutProvidingAnyProductName() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchButton();	
		//driver.findElement(By.xpath("//button[@class ='btn btn-default btn-lg']")).click();
		SearchPage searchPage = new SearchPage(driver);
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		/*String actualWarningMessage = driver.findElement(By.xpath("//div[@id ='content']/h2/following-sibling::p"))
				.getText();*/
		String expectedSearchMessage = dataprop.getProperty("noProductmatchesInSearchResult");
		softassert.assertEquals(actualSearchMessage, (expectedSearchMessage),
				"no product message in the search result is displayed");
		softassert.assertAll();
	}

	@Test(priority = 4)
	public void veriftySearchingForProductAfterLoginToTheAccount() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		//driver.findElement(By.xpath("//span[text()= 'My Account']")).click();
		homePage.selectLoginOption();
		//driver.findElement(By.linkText("Login")).click();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value ='Login']")).click();
		 homePage.enterProductNameInSearchBoxField(dataprop.getProperty("existingProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataprop.getProperty("existingProduct"));
		 homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//button[@class ='btn btn-default btn-lg']")).click();
		 SearchPage searchPage = new SearchPage(driver);
		 Assert.assertTrue(searchPage.displayTheStatusOfIMacProduct(),"valid Product is not displayed");
		//Assert.assertTrue(driver.findElement(By.linkText("iMac")).isDisplayed());

		
	}

	@Test(priority = 5)
	public void verifySearchingUsingSearchCriteriafield() {
		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//button[@class ='btn btn-default btn-lg']")).click();
		
		homePage.enterProductNameInSearchBoxField(dataprop.getProperty("existingProduct"));
		//driver.findElement(By.xpath("//input[@id='input-search']")).sendKeys(dataprop.getProperty("existingProduct"));
		homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//input[@id='button-search']")).click();
		SearchPage searchPage = new SearchPage(driver);
         
		//String actualSearchtMessage = driver.findElement(By.xpath("//div[@id='content']//h2")).getText();
		String actualSearchMessage = searchPage.retriveProductsMeetingTheSearchCriteriaText();
		String expectedSearchMessage = dataprop.getProperty("validProductSearchTextResult");
		softassert.assertEquals(actualSearchMessage, (expectedSearchMessage),
				"no product message in the search result is displayed");
		softassert.assertAll();
	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
