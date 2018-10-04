package com.webpageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.utilities.BaseClass;

public class WalletHubRatingPage extends BaseClass {
	public WebDriver driver;

	public WalletHubRatingPage(WebDriver driver) {
		this.driver = driver;
	}

	/* Used to click the drop down icon*/
	@FindBy(how = How.XPATH, using = "//span[@class='drop-arrow']")
	private WebElement policyDropDown;

	public void clkPolicyDropDownBtn() {
		waitForExpectedElement(driver, policyDropDown);
		policyDropDown.click();
	}

	/* Used to click the drop down value by dispalying text*/
	@FindBy(how = How.LINK_TEXT, using = "Health")
	private WebElement policyDropDownValue;

	public void clkPolicyDropDownValue() {
		waitForExpectedElement(driver, policyDropDownValue);
		policyDropDownValue.click();
	}

	/* Used to click the rating star icon*/
	@FindBy(how = How.XPATH, using = "//a[@class='bf-icon-star-empty star bstar bf-icon-star'][5]")
	private WebElement reviewStarRating;

	public void clkReviewStarRating() {
		waitForExpectedElement(driver, reviewStarRating);
		reviewStarRating.click();
	}
	
	/* Used to fil the review field*/
	@FindBy(how = How.ID, using = "review-content")
	private WebElement reviewTextField;

	public void fillReviewtextFld(String varInput) throws Exception {
		waitForExpectedElement(driver, reviewTextField);
		Thread.sleep(2000);
		reviewTextField.clear();
		reviewTextField.sendKeys(varInput);
	}
	
	/* Used to click on submit button*/
	@FindBy(how = How.XPATH, using = "//input[@value='Submit']")
	private WebElement submitButton;

	public void clkSubmitBtn() {
		waitForExpectedElement(driver, submitButton);
		submitButton.click();
	}

	/* Used to verify success message after sumbittin the review*/
	@FindBy(how = How.XPATH, using = "//div[@class='big-title small']/h1")
	private WebElement verifyReview;

	public String verifyReviewConfirmationAlert() {
		waitForExpectedElement(driver, verifyReview);
		String reviewConfirmationText = verifyReview.getText();
		return reviewConfirmationText;
	}
}
