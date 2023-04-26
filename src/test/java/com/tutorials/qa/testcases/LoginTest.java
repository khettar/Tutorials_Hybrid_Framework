package com.tutorials.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.qa.pages.AccountPage;
import com.tutorials.qa.pages.HomePage;
import com.tutorials.qa.pages.LoginPage;
import com.tutorials.qa.testData.SupplyTestData;
import com.tutorials.qa.testbase.TestBase;
import com.tutorials.qa.utils.Utilities;

public class LoginTest extends TestBase {
	public LoginTest() throws Exception {
	super();
	}
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	@ BeforeMethod
	public void setUp () {
	    driver = initializeBrowserAndOpenAplication(prop.getProperty("browserName"));
	    HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
	}
	@Test(priority = 1, dataProvider = "validCredentialsSupplyTestData", dataProviderClass=SupplyTestData.class) 
	public void verifyLoggingIntoTheApplicationUsingValidCredentials(String email, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		
		AccountPage accountPage = new AccountPage(driver);
		
		softassert.assertTrue(accountPage.editMyAccountInformationOptionIsDisplyedOrNot());
		softassert.assertAll();
		
	
	}
@Test(priority = 2)
public void verifyLoggingIntoTheApplicationUsingInvalidCredentials() throws Exception {
	LoginPage loginPage = new LoginPage(driver);
	loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
	//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
	loginPage.enterPassword(dataprop.getProperty("invalidPassword"));
	//driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
	loginPage.clickOnLoginButton();
	//driver.findElement(By.xpath("//input[@value ='Login']")).click();
	String actualWarningMessage = loginPage.retrieveEmailPassowrdWarningNoMatchMassageText();
	//String actualWarningMessage = driver.findElement(By.xpath("//div[@class ='alert alert-danger alert-dismissible']")).getText();
	String expectedWrningMessage = dataprop.getProperty("emailPassowrdWarningNoMatchMessage");
	
	softassert.assertTrue(actualWarningMessage.contains(expectedWrningMessage),"Expected Warning Message is not displayed");
	softassert.assertAll();
	
	
}
@Test(priority = 3)
public void verifyLoggingIntoTheApplicationUsingInvalidEmailAddressAndValidPassword()throws Exception {
	LoginPage loginPage = new LoginPage(driver);
	loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
	//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
	loginPage.enterPassword(prop.getProperty("validPassword"));
	//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	loginPage.clickOnLoginButton();
	//driver.findElement(By.xpath("//input[@value ='Login']")).click();
	String actualWarningMessage=loginPage.retrieveEmailPassowrdWarningNoMatchMassageText();
	//String actualWarningMessage = driver.findElement(By.xpath("//div[@class ='alert alert-danger alert-dismissible']")).getText();
	String expectedWrningMessage = dataprop.getProperty("emailPassowrdWarningNoMatchMessage");
	
	softassert.assertTrue(actualWarningMessage.contains(expectedWrningMessage),"Expected Warning Message is not displayed");
	softassert.assertAll();
	
}
@Test(priority= 4)
public void verifyLoggingIntoTheApplicationUsingValidEmailAddressAndInvalidPassword()throws Exception {
	LoginPage loginPage = new LoginPage(driver);
	loginPage.enterEmailAddress(prop.getProperty("validEmail"));
	//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
	loginPage.enterPassword(dataprop.getProperty("invalidPassword"));
	//driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
	loginPage.clickOnLoginButton();
	String actualWarningMessage=loginPage.retrieveEmailPassowrdWarningNoMatchMassageText();
	//String actualWarningMessage = driver.findElement(By.xpath("//div[@class ='alert alert-danger alert-dismissible']")).getText();
	String expectedWrningMessage = dataprop.getProperty("emailPassowrdWarningNoMatchMessage");
	
	softassert.assertTrue(actualWarningMessage.contains(expectedWrningMessage),"Expected Warning Message is not displayed");
	softassert.assertAll();
	
}
@Test(priority = 5)
public void VerifyLoggingIntoTheApplicationWithoutProvidingAnyCredentials() throws Exception {
	LoginPage loginPage = new LoginPage(driver);
	loginPage.clickOnLoginButton();
	//driver.findElement(By.xpath("//input[@value ='Login']")).click();
	
	String actualWarningMessage=loginPage.retrieveEmailPassowrdWarningNoMatchMassageText();
	//String actualWarningMessage = driver.findElement(By.xpath("//div[@class ='alert alert-danger alert-dismissible']")).getText();
	String expectedWrningMessage = dataprop.getProperty("emailPassowrdWarningNoMatchMessage");
	
	softassert.assertTrue(actualWarningMessage.contains(expectedWrningMessage),"Expected Warning Message is not displayed");
	softassert.assertAll();
}
@Test (priority = 6)
public void VerifyForgottenPasswordlinkIsAvailableInTheLoginPageAndIsWorking() throws Exception {
	
	LoginPage loginPage = new LoginPage(driver);
	loginPage.clickForgottenPasswordLink();
	//driver.findElement(By.linkText("Forgotten Password")).click();
	String actualResultMessage=loginPage.retrieveForgetYourPasswordText();
	//String actualResultMessage = driver.findElement(By.xpath("//div[@id='content']/descendant::h1")).getText();
			
	String expectedResultMessage = dataprop.getProperty("forgetYourPasswordText");
	
	softassert.assertTrue(actualResultMessage.contains(expectedResultMessage),"Forgot Your Password? is not displayed");
	softassert.assertAll();
	
}

@AfterMethod
public void tearDown() {
	driver.quit();
}

}