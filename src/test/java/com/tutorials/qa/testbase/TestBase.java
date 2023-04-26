package com.tutorials.qa.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorials.qa.utils.Utilities;

public class TestBase {

	public WebDriver driver;
	public Properties prop;
	public FileInputStream ip;  
    public Properties dataprop;
                                   
	                                      

	public  TestBase () throws Exception {
	    prop = new Properties();
	   try {
		ip =new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorials\\qa\\config\\config.properties");
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	
			prop.load(ip);
		
		    dataprop = new Properties();

			File datapropFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorials\\qa\\testData\\testData.properties");
		
			FileInputStream datafis =  new FileInputStream(datapropFile);
		    dataprop.load(datafis);

		    
	}
	public WebDriver initializeBrowserAndOpenAplication(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicitlyWaitTime));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageloadTime));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utilities.scriptTime));
		driver.get(("https://tutorialsninja.com/demo/"));
		return driver;
	}
}
