package com.webpageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.utilities.BaseClass;

public class FacebookPostPage extends BaseClass {
	public WebDriver driver;

	public FacebookPostPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/* Used to click the create post link*/
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Compose Post')]")
	private WebElement createpostLink;

	public void clkCreatePostLnk() {
		waitForExpectedElement(driver, createpostLink);
		createpostLink.click();
	}

	/* Used to fill the post field*/
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Write something here')]")
	private WebElement postFld;

	public void fillPostFld(String varInputValue) throws InterruptedException {
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		actions.moveToElement(postFld);
		actions.click();
		actions.sendKeys(varInputValue);
		actions.build().perform();
	}

	/* Used to click the post button*/
	@FindBy(how = How.XPATH, using = "//span[text()='Post']")
	private WebElement postButton;

	public void clkPostBtn() {
		waitForExpectedElement(driver, postButton);
		postButton.click();
	}

	/* Used to verify the posted message*/
	public String verifyPostedMessage(String postMessage) throws Exception {
		WebElement postedMessage = driver.findElement(By.xpath("//p[contains(text(),'" + postMessage + "')]"));
		waitForExpectedElement(driver, postedMessage);
		String postedtextMessage = postedMessage.getText();
		return postedtextMessage;
	}

	/* Used to click the account setting button*/
	@FindBy(how = How.ID, using = "pageLoginAnchor")
	private WebElement accountSettingsButton;

	public void clkAccountSettingsBtn() {
		waitForExpectedElement(driver, accountSettingsButton);
		accountSettingsButton.click();
	}

	/* Used to click the logout button*/
	@FindBy(how = How.XPATH, using = "//span[text()='Log Out']")
	private WebElement logOutButton;

	public void clkLogOutBtn() {
		waitForExpectedElement(driver, logOutButton);
		logOutButton.click();
	}

}
