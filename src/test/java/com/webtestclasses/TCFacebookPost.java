package com.webtestclasses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.webpageclasses.FacebookLoginPage;
import com.webpageclasses.FacebookPostPage;

public class TCFacebookPost extends BaseClass {
	ExtentReports reports;
	ExtentTest test;
	ITestResult result;
	private Logger logger;
	private ConfigFilesUtility configFileObj;
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
	Date dt = new Date();
	String postMessage = "";

	public TCFacebookPost() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(TCFacebookPost.class);
		configFileObj = new ConfigFilesUtility();
		configFileObj.loadPropertyFile("facebook.properties");
		reports = ExtentConfigurations.getExtentInstance(reportsPath, projectPath, "Demo Project");
		test = reports.startTest("TCFacebookPost");
	}

	@BeforeClass
	public void setUP() throws Exception {
		driver = launchBrowser("chrome", configFileObj);
		String pageTitle = driver.getTitle();
		Assert.assertEquals(true, pageTitle.contains("Facebook – log in or sign up"));
		testLogHeader(test, "Verify Title");
		test.log(LogStatus.PASS, "Verified Page title : " + pageTitle);
		logger.info(pageTitle);
	}

	@Test(priority = 1)
	public void loginTest() {
		try {
			FacebookLoginPage objFacebookLoginScreen = PageFactory.initElements(driver, FacebookLoginPage.class);
			testLogHeader(test, "Verify Facebook Login page");
			objFacebookLoginScreen.fillEmailAddressFld(configFileObj.getProperty("FacebookEmail"));
			test.log(LogStatus.INFO, "Filled Email Address Field : " + configFileObj.getProperty("FacebookEmail"));
			logger.info("Filled Email Address Field : " + configFileObj.getProperty("FacebookEmail"));
			objFacebookLoginScreen.fillInputPasswordFld(configFileObj.getProperty("FacebookPassword"));
			test.log(LogStatus.INFO, "Filled Email Password Field : ******");
			logger.info("Filled Email Password Field : ******");
			objFacebookLoginScreen.clkLogInBtn();
			test.log(LogStatus.INFO, "Clicked on Login Button");
			logger.info("Clicked on Login Button");
			boolean verifyHomeLink = objFacebookLoginScreen.verifyHomeLnk();
			if (verifyHomeLink) {
				test.log(LogStatus.PASS, "Succesfully Logged in");
				logger.info("Succesfully Logged in");
				test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, "Fb Logged in"));
			} else {
				test.log(LogStatus.FAIL, "Not Logged into application");
				test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, "Fb Not Logged in"));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Element is not found" + e.getLocalizedMessage());
			logger.error("Element is not found" + e.getLocalizedMessage());
			test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, e.getLocalizedMessage()));
		}
	}

	@Test(priority = 2)
	public void postTest() {
		try {
			FacebookPostPage objFacebookPostScreen = PageFactory.initElements(driver, FacebookPostPage.class);
			testLogHeader(test, "Verify FacebookPost Page");
			objFacebookPostScreen.clkCreatePostLnk();
			test.log(LogStatus.INFO, "Clicked on create post link");
			logger.info("Clicked on create post link");
			postMessage = "Hello World " + dateFormat.format(dt);
			objFacebookPostScreen.fillPostFld(postMessage);
			test.log(LogStatus.INFO, "Filled Post Field : " + postMessage);
			logger.info("Filled Post Field : " + postMessage);
			objFacebookPostScreen.clkPostBtn();
			test.log(LogStatus.INFO, "Clicked on Post Button");
			logger.info("Clicked on Post Button");
			Thread.sleep(2000);
			String verifyPostedTextMessage = objFacebookPostScreen.verifyPostedMessage(postMessage);
			if (verifyPostedTextMessage.equalsIgnoreCase(postMessage)) {
				test.log(LogStatus.PASS, "Displayed message is : " + verifyPostedTextMessage);
				logger.info("Displayed message is : " + verifyPostedTextMessage);
				test.log(LogStatus.PASS, "Succesfully posted the message");
				logger.info("Succesfully posted the message");
				test.log(LogStatus.INFO, "Screenshot Taken : " + Utilities.captureScreenshot(driver, "Post Message"));
			} else {
				test.log(LogStatus.FAIL, "Not posted the message");
				logger.info("Not posted the message");
			}
			objFacebookPostScreen.clkAccountSettingsBtn();
			test.log(LogStatus.INFO, "Clicked on Account Settings Button");
			logger.info("Clicked on Account Settings Button");
			objFacebookPostScreen.clkLogOutBtn();
			test.log(LogStatus.INFO, "Clicked on Logout Button");
			logger.info("Clicked on Logout Button");
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
