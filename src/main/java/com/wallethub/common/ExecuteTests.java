package com.wallethub.common;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class ExecuteTests {
	public static void main(String args[]) {
		List<String> testFiles = new ArrayList<String>();
		testFiles.add("testng.xml");
		TestNG tngObj = new TestNG();
		tngObj.setTestSuites(testFiles);
		tngObj.run();
	}

}
