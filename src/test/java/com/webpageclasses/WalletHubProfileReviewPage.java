package com.webpageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.utilities.BaseClass;

public class WalletHubProfileReviewPage extends BaseClass {
	public WebDriver driver;

	public WalletHubProfileReviewPage(WebDriver driver) {
		this.driver = driver;
	}

	/* Used to click the user link*/
	@FindBy(how = How.XPATH, using = "//a[@class='user']")
	private WebElement userLink;

	public void clkUserLnk() {
		waitForExpectedElement(driver, userLink);
		userLink.click();
	}

	/* used to click the profile link*/
	@FindBy(how = How.LINK_TEXT, using = "Profile")
	private WebElement profileLink;

	public void clkProfileLnk() {
		waitForExpectedElement(driver, profileLink);
		profileLink.click();
	}

	/* Used to click the reviews link*/
	@FindBy(how = How.XPATH, using = "//div[@class='profilenav']/ul/li[3]/a")
	private WebElement reviewsLink;

	public void clkReviewsLnk() {
		waitForExpectedElement(driver, reviewsLink);
		reviewsLink.click();
	}

	/* Used to verify reviewd text by user*/
	@FindBy(how = How.XPATH, using = "//div[@class='reviews']/div[1]/p")
	private WebElement verifyReviewText;

	public String verifyReviewTextMsg() {
		waitForExpectedElement(driver, verifyReviewText);
		String reviewTextMessage = verifyReviewText.getText();
		return reviewTextMessage;
	}

	/* Used to click the log out link*/
	@FindBy(how = How.LINK_TEXT, using = "Logout")
	private WebElement logOutLnk;

	public void clkLogOutLnk() {
		waitForExpectedElement(driver, logOutLnk);
		logOutLnk.click();
	}
}
