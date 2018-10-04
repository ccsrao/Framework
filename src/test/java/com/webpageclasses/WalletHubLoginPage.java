package com.webpageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.utilities.BaseClass;

public class WalletHubLoginPage extends BaseClass {
	public WebDriver driver;

	public WalletHubLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	/* Used to click the login link*/
	@FindBy(how = How.XPATH, using = "//div[@id='viewport']/header/div/nav[3]/a[1]")
	private WebElement loginLink;

	public void clkLogInLnk() {
		waitForExpectedElement(driver, loginLink);
		loginLink.click();
	}

	/* Used to fill the email address field*/
	@FindBy(how = How.XPATH, using = "//input[@name='em']")
	private WebElement emailAddressInput;

	public void fillEmailAddressFld(String varInputValue) {
		waitForExpectedElement(driver, emailAddressInput);
		emailAddressInput.sendKeys(varInputValue);
	}

	/* Used to fill the password field*/
	@FindBy(how = How.XPATH, using = "//input[@name='pw']")
	private WebElement passwordInput;

	public void fillInputPasswordFld(String varInputValue) {
		waitForExpectedElement(driver, passwordInput);
		passwordInput.sendKeys(varInputValue);
	}

	/* Used to click the login button*/
	@FindBy(how = How.XPATH, using = "//span[text()='Login']")
	private WebElement loginButton;

	public void clkLogInBtn() {
		waitForExpectedElement(driver, loginButton);
		loginButton.click();
	}

	/* Used to verify the user is displayed or not in home page*/
	@FindBy(how = How.XPATH, using = "//a[@class='user']")
	private WebElement userLink;

	public boolean userLinkDisplayed() {
		waitForExpectedElement(driver, userLink);
		boolean userLinkDisplayed = userLink.isDisplayed();
		return userLinkDisplayed;
	}

}
