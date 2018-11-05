package com.wallethub.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wallethub.common.Initialization;

public class WalletPolicyReview extends Initialization {
	WebDriver driver;
	
	@Test
	public void testHealthReview() throws InterruptedException {
		driver = Initialization.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 50);
		driver.get("https://wallethub.com/profile/test_insurance_company/");
		//Sign In 
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[1]/input")).sendKeys(dataProps.getProperty("walletUser"));;
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[2]/input")).sendKeys(dataProps.getProperty("walletPass"));;
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[4]/button[2]")).click();
		Thread.sleep(3000);
		driver.get("https://wallethub.com/profile/test_insurance_company/");
		
		//Hovering
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//*[@id='wh-body-inner']/div[2]/div[3]/span")));
		WebElement hovering_over_stars = driver.findElement(By.xpath(".//*[@id='wh-body-inner']/div[2]/div[3]/span"));
		Actions builder = new Actions(driver);
		builder.moveToElement(hovering_over_stars).build().perform();
		
		//hovering over the 5th,4th and 3rd inner stars
		Thread.sleep(3000);
		builder.moveToElement(driver.findElement(By.xpath("//*[@id='wh-body-inner']/div[2]/div[3]/div[1]/div/a[5]"))).build().perform();
		Thread.sleep(3000);
		builder.moveToElement(driver.findElement(By.xpath("//*[@id='wh-body-inner']/div[2]/div[3]/div[1]/div/a[4]"))).build().perform();
		Thread.sleep(3000);
		builder.moveToElement(driver.findElement(By.xpath("//*[@id='wh-body-inner']/div[2]/div[3]/div[1]/div/a[3]"))).build().perform();
		Thread.sleep(3000);
		
		//Clicking on fourth star
		driver.findElement(By.xpath("//*[@id='wh-body-inner']/div[2]/div[3]/div[1]/div/a[4]")).click();
		
		//opting for Health option from the dropdown
		driver.findElement(By.cssSelector("div[class='dropdown-title']")).click();
		builder.moveToElement(driver.findElement(By.xpath("//*[@id='reviewform']/div/div/ul/li[2]"))).click();
		driver.findElement(By.xpath("//*[@id='reviewform']/div/div/ul/li[2]")).click();
				
		//entering the review text
		driver.findElement(By.xpath("//*[@id='review-content']")).sendKeys(dataProps.getProperty("reviewComment"));
		builder.moveToElement(driver.findElement(By.xpath("//*[@id='overallRating']/a[4]"))).build().perform();
		driver.findElement(By.xpath("//*[@id='overallRating']/a[4]")).click();
		driver.findElement(By.xpath("//*[@class='submit']/input")).click();
		
		//Verifying the success
		String awesomeText = driver.findElement(By.xpath("//*[@id='review']/div[1]/h1")).getText();
		System.out.println(awesomeText);
		Assert.assertEquals(awesomeText, "Awesome!\nYour Test Insurance Company review has been posted.");
		
		//verifying that 'has been posted.' is a hypelink
		driver.findElement(By.linkText("has been posted."));
		
		String reviewText = driver.findElement(By.xpath("//*[@id='reviewform']/div[2]/p")).getText();
		Assert.assertEquals(reviewText, dataProps.getProperty("reviewComment"));
		
		//Navigating directly to review section and verifying the review
		
		driver.get("https://wallethub.com/profile/punia_sarita01/reviews/");
		String reviewTextInReviewSection = driver.findElement(By.xpath("//*[@class='reviews']/div/p")).getText();
		Assert.assertEquals(reviewTextInReviewSection, dataProps.getProperty("reviewComment"));
		
	}

}
