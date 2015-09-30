/**
 * SailThru AnyPoint Connector
 *
 * Copyright (c) The Economist Group  All rights reserved.  https://github.com/EconomistDigitalSolutions
 *
 * The software in this package is published under the terms of the
 * MIT License (MIT), a copy of which has been included with this distribution
 * in the LICENSE.txt file.
 */
/**
 *
 */
package com.economist.modules.sailthru.automation.testrunners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import com.economist.modules.sailthru.automation.testcases.SendTestCases;
import com.economist.modules.sailthru.SailthruConnector;
import org.mule.tools.devkit.ctf.junit.RegressionTests;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

@RunWith(Categories.class)
@IncludeCategory(RegressionTests.class)

@SuiteClasses({
	SendTestCases.class
	
})

public class RegressionTestSuite {
	
	@BeforeClass
	public static void initialiseSuite(){
		
		ConnectorTestContext.initialize(SailthruConnector.class);

	}
	
	@AfterClass
    public static void shutdownSuite() {

        ConnectorTestContext.shutDown();

    }
	
}