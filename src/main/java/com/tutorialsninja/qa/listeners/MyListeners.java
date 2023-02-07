package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExterntReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExterntReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {

		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " Stated Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.PASS, result.getName() + " got Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destnationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(destnationScreenshotPath);
		System.out.println(result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " got Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
		File extetReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extetReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
