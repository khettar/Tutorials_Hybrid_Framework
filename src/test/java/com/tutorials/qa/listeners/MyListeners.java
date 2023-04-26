package com.tutorials.qa.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorials.qa.utils.MyExtentReporter;

public class MyListeners implements ITestListener {
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		try {
			extentReport = MyExtentReporter.generateExtentReport();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//System.out.println("Execution of Tutorials Project Started");
		//ITestListener.super.onStart(context);
	}
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + "started executing");
		
		//System.out.println(testName +"start executing ");
		//ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.PASS, testName + "successfully executed");
		//System.out.println(testName +"successfully executed ");
		//ITestListener.super.onTestSuccess(result);
	}

	@Override
	// How to retrieve the driver?
		//one or more test cases will fail.Those test cases will have a certain method.That failed method will invoke this Listener.
		//that failed method will pass the driver.
		//that driver will be stored in the result.We can use the result to get the driver.
		//The driver will come in an Object format.We have to typeCase
		//Very imp -make sure that the Test Cases,the instance variable of WebDriver driver is public. Otherwise it will throw nullPointException
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		// screenshot of the failure
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") +"\\test-output\\Screenshots\\" + testName +".png";
		try {
			FileHandler.copy(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(destination);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + "failed");
		//System.out.println(testName +"failed ");
		//System.out.println(result.getThrowable());
	
		//ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName +"skipped");
		//System.out.println(testName +"got skipped ");
		//System.out.println(result.getThrowable());
		//ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onFinish(ITestContext context) {	
		System.out.println("Execution of Tutorials Project finished");
		extentReport.flush();
		//ITestListener.super.onFinish(context);
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName +"With this much percentage");
		System.out.println(result.getThrowable());
	}
}