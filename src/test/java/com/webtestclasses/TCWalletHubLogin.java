package com.webtestclasses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.configurations.ExtentConfigurations;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.BaseClass;
import com.utilities.ConfigFilesUtility;
import com.utilities.Utilities;
import com.webpageclasses.WalletHubHomePage;
import com.webpageclasses.WalletHubLoginPage;
import com.webpageclasses.WalletHubProfileReviewPage;
import com.webpageclasses.WalletHubRatingPage;

public class TCWalletHubLogin extends BaseClass {
	ExtentReports reports;
	ExtentTest test;
	ITestResult result;
	private Logger logger;
	private ConfigFilesUtility configFileObj;
	public boolean isElementDispalyed = false;

	/* */
	public TCWalletHubLogin() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(TCWalletHubLogin.class);
		configFileObj = new ConfigFilesUtility();
		configFileObj.loadPropertyFile("wallethub.properties");
		reports = ExtentConfigurations.getExtentInstance(reportsPath, projectPath, "Demo Project");
		test = reports.startTest("TCWalletHubLogin");
	}

	/* */
	@BeforeClass
	public void setUP() throws Exception {
		driver = launchBrowser("chrome", configFileObj);
		String pageTitle = driver.getTitle();
		Assert.assertEquals(true, pageTitle.contains("Test Insurance Company Reviews:"));
		testLogHeader(test, "Verify Title");
		test.log(LogStatus.PASS, "Verified Page title : " + pageTitle);
		logger.info(pageTitle);
	}

	/* */
	@Test(priority = 1)
	public void loginTest() {
		try {
			WalletHubLoginPage objWalletHubLoginScreen = PageFactory.initElements(driver, WalletHubLoginPage.class);
			testLogHeader(test, "Verify WalletHubLoginPage page");
			objWalletHubLoginScreen.clkLogInLnk();
			test.log(LogStatus.INFO, "Clicked on Login Link");
			logger.info("Clicked on Login Link");
			objWalletHubLoginScreen.fillEmailAddressFld(configFileObj.getProperty("EmailAddress"));
			test.log(LogStatus.INFO, "Filled Email Address Field : " + configFileObj.getProperty("EmailAddress"));
			logger.info("Filled Email Address Field : " + configFileObj.getProperty("EmailAddress"));
			objWalletHubLoginScreen.fillInputPasswordFld(configFileObj.getProperty("Password"));
			test.log(LogStatus.INFO, "Filled Password Field : ******");
			logger.info("Filled Password Field : ******");
			objWalletHubLoginScreen.clkLogInBtn();
			test.log(LogStatus.INFO, "Clicked on Login Button");
			logger.info("Clicked on Login Button");
			boolean userLinkdisplayed = objWalletHubLoginScreen.userLinkDisplayed();
			if (userLinkdisplayed) {
				test.log(LogStatus.PASS, "Successfully Logged in");
				logger.info("Successfully Logged in");
				test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, "Logged in"));
			} else {
				test.log(LogStatus.FAIL, "Not Logged into application");
				logger.info("Not Logged into application");
				test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, "Not Logged into application"));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Element is not found" + e.getLocalizedMessage());
			logger.error("Element is not found" + e.getLocalizedMessage());
			test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, e.getLocalizedMessage()));
		}
	}

	/* */
	@Test(priority = 2)
	public void reviewTest() {
		try {
			WalletHubHomePage objWalletHubHomePage = PageFactory.initElements(driver, WalletHubHomePage.class);
			testLogHeader(test, "Verify WalletHubHomePage page");
			objWalletHubHomePage.clkhoverStarBtn();
			test.log(LogStatus.INFO, "Moved mouse to Rating bar");
			logger.info("Moved mouse to Rating bar");
			objWalletHubHomePage.clkRatingStarBtn();
			test.log(LogStatus.INFO, "Clicked on Rating Star icon");
			logger.info("Clicked on Rating Star icon");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Element is not found" + e.getLocalizedMessage());
			logger.error("Element is not found" + e.getLocalizedMessage());
			test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, e.getLocalizedMessage()));
		}
	}

	/* */
	@Test(priority = 3)
	public void reviewRatingTest() {
		try {
			WalletHubRatingPage objWalletHubRatingPage = PageFactory.initElements(driver, WalletHubRatingPage.class);
			testLogHeader(test, "Verify WalletHubRating Page");
			objWalletHubRatingPage.clkPolicyDropDownBtn();
			test.log(LogStatus.INFO, "Clicked on Policy drop down");
			logger.info("Clicked on Policy drop down");
			objWalletHubRatingPage.clkPolicyDropDownValue();
			test.log(LogStatus.INFO, "Clicked on Drop down value");
			logger.info("Clicked on Drop down value");
			objWalletHubRatingPage.clkReviewStarRating();
			test.log(LogStatus.INFO, "Clicked on review star rating");
			logger.info("Clicked on review star rating");
			objWalletHubRatingPage.fillReviewtextFld("Test Insurance Company Comments about Test Insurance Company Comments aboutTest Insurance Company Comments about Test Insurance Company Comments about Insurance Company Comments about Test Insurance Company Comments about");
			test.log(LogStatus.INFO, "Filled Review text field");
			logger.info("Filled Review text field");
			objWalletHubRatingPage.clkSubmitBtn();
			test.log(LogStatus.INFO, "Clicked on Submit button");
			logger.info("Clicked on Submit button");
			String reviewText = objWalletHubRatingPage.verifyReviewConfirmationAlert();
			test.log(LogStatus.PASS, "Verified Text is : " + reviewText);
			logger.info("Verified Text is : " + reviewText);
			test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, "Review Message"));
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Element is not found" + e.getLocalizedMessage());
			logger.error("Element is not found" + e.getLocalizedMessage());
			test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, e.getLocalizedMessage()));
		}
	}

	/* */
	@Test(priority = 4)
	public void verifyReviewTest() {
		try {
			WalletHubProfileReviewPage objWalletHubProfileReviewPage = PageFactory.initElements(driver, WalletHubProfileReviewPage.class);
			testLogHeader(test, "Verify WalletHubRating Page");
			objWalletHubProfileReviewPage.clkUserLnk();
			test.log(LogStatus.INFO, "Clicked on User link");
			logger.info("Clicked on User link");
			objWalletHubProfileReviewPage.clkProfileLnk();
			test.log(LogStatus.INFO, "Clicked on Profile link");
			logger.info("Clicked on Profile link");
			objWalletHubProfileReviewPage.clkReviewsLnk();
			test.log(LogStatus.INFO, "Clicked on Reviews link");
			logger.info("Clicked on Reviews link");
			String verifyReviewtext = objWalletHubProfileReviewPage.verifyReviewTextMsg();
			test.log(LogStatus.PASS, "Verified Reviewed Text : " + verifyReviewtext);
			logger.info("Verified Reviewed Text : " + verifyReviewtext);
			Thread.sleep(1000);
			test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, "Profile Review"));
			objWalletHubProfileReviewPage.clkUserLnk();
			test.log(LogStatus.INFO, "Clicked on User link");
			logger.info("Clicked on User link");
			objWalletHubProfileReviewPage.clkLogOutLnk();
			test.log(LogStatus.INFO, "Clicked on Logout link");
			logger.info("Clicked on Logout link");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Element is not found" + e.getLocalizedMessage());
			logger.error("Element is not found" + e.getLocalizedMessage());
			test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, e.getLocalizedMessage()));
		}
	}

	@AfterClass
	public void tearDown() throws Exception {
		reports.endTest(test);
		reports.flush();
		driver.quit();
		logger.info("Browser is closed");
	}
}
