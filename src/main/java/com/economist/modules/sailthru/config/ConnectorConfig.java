/**
 * SailThru AnyPoint Connector
 *
 * Copyright (c) The Economist Group  All rights reserved.  https://github.com/EconomistDigitalSolutions
 *
 * The software in this package is published under the terms of the
 * MIT License (MIT), a copy of which has been included with this distribution
 * in the LICENSE.txt file.
 */

package com.economist.modules.sailthru.config;

import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.param.Default;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.economist.modules.sailthru.MuleSailThruClient;

@ConnectionManagement(friendlyName = "Configuration")
public class ConnectorConfig {
    
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	private MuleSailThruClient client;
	private boolean connected = false;
	
    /**
     * Base URI to use for API calls
     */
    @Configurable
    @Default(value="http://api.sailthru.com")
    private String apiBaseURI;

    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String apiKey, String sharedSecret)
        throws ConnectionException {
        LOG.debug("Checking if client exists");
        if (null == client) {
            try {
                LOG.debug("Creating client");
                this.client = new MuleSailThruClient(apiKey, sharedSecret, this.apiBaseURI.toString());
                this.connected = true;
                LOG.debug("Got a new client");
            } catch (Exception e) {
                LOG.error("Something bad happened", e);
            }
        }
    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
        /*
         * CODE FOR CLOSING A CONNECTION GOES IN HERE
         */
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        return this.connected;
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return "001";
    }

	public String getApiBaseURI() {
		return apiBaseURI;
	}

	public void setApiBaseURI(String apiBaseURI) {
		this.apiBaseURI = apiBaseURI;
	}

	public MuleSailThruClient getClient() {
		return this.client;
	}
}