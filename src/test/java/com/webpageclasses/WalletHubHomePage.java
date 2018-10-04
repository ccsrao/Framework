package com.webpageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.utilities.BaseClass;

public class WalletHubHomePage extends BaseClass {
	public WebDriver driver;

	public WalletHubHomePage(WebDriver driver) {
		this.driver = driver;
	}

	/* Used to mouse hover upto rating stars*/
	@FindBy(how = How.XPATH, using = "//div[@id='wh-body-inner']/div[2]/div[3]/span")
	private WebElement hoverStar;

	public void clkhoverStarBtn() throws Exception {
		waitForExpectedElement(driver, hoverStar);
		Actions action = new Actions(driver);
		action.moveToElement(hoverStar).build().perform();
	}

	/* Used to click the rating star icon*/
	@FindBy(how = How.XPATH, using = "//div[@id='wh-body-inner']/div[2]/div[3]/div[1]/div[1]/a[4]")
	private WebElement ratingStarButton;

	public void clkRatingStarBtn() throws Exception {
		waitForExpectedElement(driver, ratingStarButton);
		ratingStarButton.click();
	}
}
