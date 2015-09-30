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
package com.economist.modules.sailthru.automation;

import org.junit.Before;
import com.economist.modules.sailthru.SailthruConnector;
import org.mule.tools.devkit.ctf.mockup.ConnectorDispatcher;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

public abstract class AbstractTestCase {
	
	private SailthruConnector connector;
	private ConnectorDispatcher<SailthruConnector> dispatcher;
	
	
	protected SailthruConnector getConnector() {
		return connector;
	}


	protected ConnectorDispatcher<SailthruConnector> getDispatcher() {
		return dispatcher;
	}

	@Before
	public void init() throws Exception {
		
		//Initialization for single-test run
        ConnectorTestContext.initialize(SailthruConnector.class, false);
		
		//Context instance
		ConnectorTestContext<SailthruConnector> context = ConnectorTestContext.getInstance(SailthruConnector.class);
		
		//Connector dispatcher
		dispatcher = context.getConnectorDispatcher();
		
		connector = dispatcher.createMockup();
		
	}

}
