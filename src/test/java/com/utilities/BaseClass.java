package com.utilities;

import java.io.File;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.configurations.ReportPaths;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	public WebDriver driver;
	public static String projectPath = System.getProperty("user.dir");
	public static String reportsPath = projectPath + File.separator + "WebReports" + File.separator + ReportPaths.reportPathName;
    public String chromeDriverPath = projectPath + File.separator + "Resources" + File.separator + "chromedriver.exe";
	public String geckoFireFoxDriverPath = projectPath + File.separator + "Resources" + File.separator + "geckodriver.exe";
	public String iEDriverPath = projectPath + File.separator + File.separator + "Resources" + File.separator + "IEDriverServer.exe";
	
	
	// Explicit wait method
	public static WebElement waitForExpectedElement(WebDriver driver, final By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static WebElement waitForExpectedElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Explicit wait
	public static WebElement waitForExpectedElement(WebDriver driver, final By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Explicit wait method
	public boolean objectExists(WebDriver driver, final By locator) {
		try {
			waitForPageToLoad();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Method for Retry and It executes the failed test case based on our count
	public class Retry implements IRetryAnalyzer {
		private int count = 0;
		private static final int maxTry = 3;

		public boolean retry(ITestResult iTestResult) {
			if (!iTestResult.isSuccess()) { // Check if test not succeed
				if (count < maxTry) { // Check if maxtry count is reached
					count++; // Increase the maxTry count by 1
					iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
					return true; // Tells TestNG to re-run the test
				} else {
					iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
				}
			} else {
				iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
			}
			return false;
		}
	}

	// Explicit wait method (While Script Execution we need to pass time limit)
	public boolean objectExists(WebDriver driver, final By locator, int timeout) {
		try {
			waitForPageToLoad();
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Explicit wait method (While Script Execution we need to pass time limit
	public void waitForPageToLoad() {
		(new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState")
						.equals("complete"));
			}
		});
	}

	@SuppressWarnings("deprecation")
	public WebDriver launchBrowser(String browserName, ConfigFilesUtility configFileObj) {
		if (!isWindows()) {
			if (isSolaris() || isUnix()) {
				chromeDriverPath = chromeDriverPath.replace(".exe", "");
				geckoFireFoxDriverPath = geckoFireFoxDriverPath.replace(".exe", "");
			} else if (isMac()) {
				chromeDriverPath = chromeDriverPath.replace("chromedriver.exe", "macChromeDriver");
				geckoFireFoxDriverPath = geckoFireFoxDriverPath.replace(".exe", "macGeckodriver");
			}
		}
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			JSONObject jsonCapabilities = new JSONObject();

	        jsonCapabilities.put("nativeEvents", false);
	        options.setCapability("se:ieOptions", jsonCapabilities);
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver(options);
			System.out.println("Chrome Browser is Launched");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", geckoFireFoxDriverPath);
			driver = new FirefoxDriver();
			System.out.println("FireFox Browser is Launched");
		} else if (browserName.equalsIgnoreCase("safari")) {	
			//Note Should AllowRemoteAutomation in safari browser DeveloperMenu
			//Directions -- > launchSafariBrowser --> Preferences --> Advanced Tab --> 
			//Show Developer Menu --> Click on DevloperMenu --> Enable AllowRemoteAutomation 
			driver = new SafariDriver();
			System.out.println("Safari Browser is Launched");
		} else if (browserName.equalsIgnoreCase("ie")) {
			if(!isWindows()) {
				System.out.println("IE Browser not supported for this OS.");
				return null;
			}
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", iEDriverPath);
			driver = new InternetExplorerDriver(capabilities);
			System.out.println("IE Browser is Launched");
		}

		driver.manage().deleteAllCookies();
		driver.get(configFileObj.getProperty("URL"));
		driver.manage().window().maximize();
		return driver;
	}
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}
	
	public static void testLogHeader(ExtentTest test ,String data) {
		test.log(LogStatus.INFO, "<b style = 'background-color: #ffffff; color : #ff8f00 ; font-size : 18px' >"+ data + "</b>");
	}
	
}
