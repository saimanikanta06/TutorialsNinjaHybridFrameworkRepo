package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExterntReporter{
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport= new ExtentReports();
		
		File extentReportFile= new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Results Report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp= new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");;
		FileInputStream fisConfigProp;
		try {
			fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("BrowserName", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("OperatingSystem", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}

}
