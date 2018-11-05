package com.wallethub.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wallethub.common.Initialization;

public class FacebookLogin extends Initialization {
	WebDriver driver;

	@Test
	public void loginFacebook() throws InterruptedException {
		driver = Initialization.getDriver();
		driver.get("https://www.facebook.com");
		driver.findElement(By.id(objProps.getProperty("emailTextBox"))).clear();
		driver.findElement(By.id(objProps.getProperty("emailTextBox"))).sendKeys(dataProps.getProperty("facebookUser"));
		driver.findElement(By.id(objProps.getProperty("passTextBox"))).clear();
		driver.findElement(By.id(objProps.getProperty("passTextBox"))).sendKeys(dataProps.getProperty("facebookPass"));
		// driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath(objProps.getProperty("loginButton"))).click();
		driver.findElement(By.name(objProps.getProperty("statusTextBox"))).sendKeys(dataProps.getProperty("fbStatus"));
		Thread.sleep(10000);
		driver.findElement(By.cssSelector(objProps.getProperty("statusPostButton"))).click();
		Thread.sleep(3000);

		// news feed xpath
		// driver.findElement(By.xpath("//*[@class='uiContextualLayerParent']/div/ol/li/div/ol/li/div/div[1]/div")).click();

		// share button xpath
		// driver.findElement(By.xpath("//*[@id='feedx_sprouts_container']/div/div/div[2]/div/div[2]/div[3]/div[2]/div/div/span/button")).click();

		// Asserting the post

		driver.findElement(By.linkText("Mrudun")).click();
		Thread.sleep(3000);
		String text = driver.findElement(By.xpath(objProps.getProperty("latestStatusOnProfile"))).getText();
		Assert.assertEquals(text, dataProps.getProperty("fbStatus"));
		driver.close();
	}

}
