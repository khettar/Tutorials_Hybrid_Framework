package com.tutorials.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.qa.pages.AccountSuccessPage;
import com.tutorials.qa.pages.HomePage;
import com.tutorials.qa.pages.RegisterPage;
import com.tutorials.qa.testbase.TestBase;
import com.tutorials.qa.utils.Utilities;

public class RegisterTest extends TestBase {
	public  RegisterTest() throws Exception {
		super();
	}
	  WebDriver driver;
	public  static SoftAssert softassert = new SoftAssert();
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenAplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		//driver.findElement(By.xpath("//span[text()= 'My Account']")).click();
		homePage.selectRegisterOption();
		//driver.findElement(By.linkText("Register")).click();
	}
	@Test(priority = 1)
	public void verifyRegisteringAnAccountByProvidingOnlyTheMandatoryFields() {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		//driver.findElement(By.xpath("//input[@id = 'input-firstname']")).sendKeys(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		//driver.findElement(By.xpath("//input[@id = 'input-lastname']")).sendKeys(dataprop.getProperty("lastName"));
		registerPage.enterEmailAdress(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@id = 'input-password']")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@name ='confirm']")).sendKeys(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		//driver.findElement(By.name("agree")).click();
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@type ='submit']")).click();
		
         AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        
		String actualConfirmationMessage = accountSuccessPage.retrieveConfirmationSuccessAccountText();
			//	.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText();

		softassert.assertEquals(actualConfirmationMessage, dataprop.getProperty("accountSuccessfulyCreated"),
				"confirmation message is not displayed");
		softassert.assertAll();
		
	}

	@Test(priority = 2)
	public void verifyingRegisteringAccountWithExistingCredentials() {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		//driver.findElement(By.xpath("//input[@id = 'input-firstname']")).sendKeys(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		//driver.findElement(By.xpath("//input[@id = 'input-lastname']")).sendKeys(dataprop.getProperty("lastName"));
		registerPage.enterEmailAdress(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@id = 'input-password']")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@name ='confirm']")).sendKeys(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		//driver.findElement(By.name("agree")).click();
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@type ='submit']")).click();
		String actualWarningMessage = registerPage.retrieveExistingEmailWarningText();
		//String actualWarningMessage = driver
				//.findElement(By.xpath("//div[@class ='alert alert-danger alert-dismissible']")).getText();
		String expectedWarningMessage = dataprop.getProperty("existingEmailWarning");
		softassert.assertEquals(actualWarningMessage, expectedWarningMessage);
		softassert.assertAll();
		
	}

	@Test(priority = 3)
	public void VerifyRegisteringTheAccountWithoutSelectingThePrivacyPolicyCheckboxOption() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		//driver.findElement(By.xpath("//input[@id = 'input-firstname']")).sendKeys(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		//driver.findElement(By.xpath("//input[@id = 'input-lastname']")).sendKeys(dataprop.getProperty("lastName"));
		registerPage.enterEmailAdress(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@id = 'input-password']")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@name ='confirm']")).sendKeys(prop.getProperty("validPassword"));
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@type ='submit']")).click();
		String actualWarningMessage = registerPage.retrievePrivacyPolicyWarningText();
				/*String actualWarningMessage = driver.findElement(By.xpath("//div[@class ='alert alert-danger alert-dismissible']"))
				.getText();*/
		String expectedWarningMessage = dataprop.getProperty("privacyPolicyWarning");
		softassert.assertEquals(actualWarningMessage, expectedWarningMessage);
		softassert.assertAll();
		
	}

	@Test(priority = 4)
	public void verifyRegisteringAnAccountByProvidingAllTheFields() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		//driver.findElement(By.xpath("//input[@id = 'input-firstname']")).sendKeys(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		//driver.findElement(By.xpath("//input[@id = 'input-lastname']")).sendKeys(dataprop.getProperty("lastName"));
		registerPage.enterEmailAdress(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@id = 'input-password']")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@name ='confirm']")).sendKeys(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		//driver.findElement(By.xpath("//input[@name ='newsletter' and @value='1']")).click();
		registerPage.selectPrivacyPolicy();
		//driver.findElement(By.name("agree")).click();
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@type ='submit']")).click();
		 AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);    
			String actualConfirmationMessage = accountSuccessPage.retrieveConfirmationSuccessAccountText();
				//	.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText();

			softassert.assertEquals(actualConfirmationMessage, dataprop.getProperty("accountSuccessfulyCreated"),
					"confirmation message is not displayed");
			softassert.assertAll();
		
	}

	@Test(priority = 5)
	public void VerifyRegisteringAnAccountByEnteringDifferentPasswordsIntoPasswordAndPasswordConfirmfields() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		//driver.findElement(By.xpath("//input[@id = 'input-firstname']")).sendKeys(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		//driver.findElement(By.xpath("//input[@id = 'input-lastname']")).sendKeys(dataprop.getProperty("lastName"));
		registerPage.enterEmailAdress(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@id = 'input-password']")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterPassword(dataprop.getProperty("invalidPassword"));
		//driver.findElement(By.xpath("//input[@name ='confirm']")).sendKeys(dataprop.getProperty("invalidPassword"));
		registerPage.selectPrivacyPolicy();
		//driver.findElement(By.name("agree")).click();
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@type ='submit']")).click();
		String actualWarningMessage= registerPage.retrievePasswordConfirmationWarningText();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[@class='text-danger']")).getText();

		softassert.assertEquals(actualWarningMessage,dataprop.getProperty("passwordConfirmationWarning"),
				"Warning message is not displayed");
		softassert.assertAll();

		

	}
	@Test(priority = 6)
	public void verifyProperNotificationMessagesAreDisplayedForTheMandatoryFieldsWhenYouDoNotProvideAnyDetiles() {
	
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@type ='submit']")).click();
		String actualPrivacyPolicyWarningMessage= registerPage.retrievePrivacyPolicyWarningMessage(); 
	//String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
	String expectedPrivacyPolicyWarningMessage =dataprop.getProperty("privacyPolicyWarning");
	softassert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage), "privacy policy warning message doesn't mach");
	softassert.assertAll();
	
	String actualFirstNameWarning =registerPage.retrieveFirstNameWarningText();
	//String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
	String expectedFirstNameWarning =dataprop.getProperty("firstNameWarning ");		
	softassert.assertTrue(actualFirstNameWarning .contains(expectedFirstNameWarning), "First name warning message is not displayed");
	softassert.assertAll();
	
	String actualLastNameWarning =registerPage.retrieveLastNameWarningText();
	//String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div[@class='text-danger']")).getText();
	String expectedLastNameWarning =dataprop.getProperty("lastNameWarning");
	softassert.assertTrue(actualLastNameWarning .contains(expectedLastNameWarning), "last name warning message is not displayed");
	softassert.assertAll();
	
	String actualEmailWarning= registerPage.retrieveEmailWarningText();
	//String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div[@class='text-danger'])).getText();
	String expectedEmailWarning =dataprop.getProperty("emailWarning");
	softassert.assertTrue(actualEmailWarning .contains(expectedEmailWarning), "Email warning message is not displayed");
	softassert.assertAll();
	
	String actualTelephoneWarning = registerPage.retrieveTelephoneWarning();
	//String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div[@class='text-danger']")).getText();
	String expectedTelephoneWarning =dataprop.getProperty("telephoneWarning");
	softassert.assertTrue(actualTelephoneWarning .contains(expectedTelephoneWarning), "Telephone warning message is not displayed");
	softassert.assertAll();
	
	String actualPasswordWarning= registerPage.retrievePasswordWarningText();
	//String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div[@class='text-danger']")).getText();
	String expectedPasswordWarning =dataprop.getProperty("passwordWarning");
	softassert.assertTrue(actualPasswordWarning .contains(expectedPasswordWarning), "password warning message is not displayed");
	softassert.assertAll();
	
	
	}
@AfterMethod
public void tearDown() {
	driver.quit();
}
}
