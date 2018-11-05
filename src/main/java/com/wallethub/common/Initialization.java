package com.wallethub.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Initialization {
	protected static Properties dataProps = new Properties();
	protected static Properties objProps = new Properties();

	static {
		
		try {

			FileInputStream dataFile = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/data.properties");
			FileInputStream objFile = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/objRepo.properties");
			dataProps.load(dataFile);
			objProps.load(objFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "/home/sarita/chromedriver");
		ChromeOptions coptions = new ChromeOptions();
		coptions.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(coptions);
		driver.manage().window().maximize();
		return driver;
	}

}
