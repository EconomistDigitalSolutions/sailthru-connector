/**
 * SailThru AnyPoint Connector
 *
 * Copyright (c) The Economist Group  All rights reserved.  https://github.com/EconomistDigitalSolutions
 *
 * The software in this package is published under the terms of the
 * MIT License (MIT), a copy of which has been included with this distribution
 * in the LICENSE.txt file.
 */

package com.economist.modules.sailthru;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.param.Optional;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import com.economist.modules.sailthru.config.ConnectorConfig;

@Connector(name="sailthru", friendlyName="Sailthru")
@MetaDataScope( DataSenseResolver.class )
public class SailthruConnector {


    @Config
    ConnectorConfig config;

    /**
     * Sends an email.s
     * 
     * {@sample.xml ../../../doc/mule-module-sailthru.xml.sample sailthru:send}
     * 
     * @param emailAddress The email address to send to
     * @param template The template to use
     * @param vars The variables to use
     * @param scheduledTime the time to send at
     * @param options Options to use
     * @return The result of the send
     * @throws Exception
     */
    @Processor
    public Map<String,Object> send(String emailAddress, String template, 
            @Optional Map<String,Object> vars,
            @Optional Date scheduledTime,
            @Optional Map<String,Object> options) throws IOException
    {        
        return config.getClient().send(Collections.singleton(emailAddress), template, vars, scheduledTime, options);
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}