package com.webpageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.utilities.BaseClass;

public class FacebookLoginPage extends BaseClass {
	public WebDriver driver;

	public FacebookLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	/* Used to fill the email address field */
	@FindBy(how = How.ID, using = "email")
	private WebElement emailAddressInput;

	public void fillEmailAddressFld(String varInputValue) {
		waitForExpectedElement(driver, emailAddressInput);
		emailAddressInput.sendKeys(varInputValue);
	}

	/* Used to fill the password field */
	@FindBy(how = How.ID, using = "pass")
	private WebElement passwordInput;

	public void fillInputPasswordFld(String varInputValue) {
		waitForExpectedElement(driver, passwordInput);
		passwordInput.sendKeys(varInputValue);
	}

	/* Used to click the loggin button */
	@FindBy(how = How.ID, using = "loginbutton")
	private WebElement loginButton;

	public void clkLogInBtn() {
		waitForExpectedElement(driver, loginButton);
		loginButton.click();
	}

	/* Used to verify the home link is displayed or not in Home page */
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Home')]")
	private WebElement homeLink;

	public boolean verifyHomeLnk() {
		waitForExpectedElement(driver, homeLink);
		boolean verifyHomeLink = homeLink.isDisplayed();
		return verifyHomeLink;
	}

}
