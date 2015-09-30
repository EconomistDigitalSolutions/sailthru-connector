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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.sailthru.client.SailthruClient;
import com.sailthru.client.exceptions.ApiException;
import com.sailthru.client.handler.response.JsonResponse;
import com.sailthru.client.params.MultiSend;

/**
 * @author ronaldhatcher
 *
 */
public class MuleSailThruClient {
	
    private transient final Logger LOG = LoggerFactory.getLogger(this.getClass());
    
    private SailthruClient client;
    private String apiKey;
    private String apiSecret;
    private String apiURL;

	public MuleSailThruClient(String apiKey, String apiSecret, String apiURL) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.apiURL = apiURL;
        
    }

	public Map<String, Object> send(Collection<String> emailAddresses, String template,
			Map<String, Object> vars, Date scheduledTime,
			Map<String, Object> options) throws IOException{
		
        LOG.info("Sending email template {} to {}",template,emailAddresses);
        LOG.debug("emailAddress: {}, template:{}, vars: {}, scheduledTime: {}, options: {}",new Object[]{emailAddresses,template,vars,scheduledTime,options});
        MultiSend send = new MultiSend();
        send.setEmails(new ArrayList<String>(emailAddresses));
        send.setTemplate(template);
        if(null != vars && !(vars.isEmpty()))
        {
            send.setVars(vars);
        }
        
        if(null != scheduledTime)
        {
            send.setScheduleTime(scheduledTime);
        }
        
        if(null != options && !(options.isEmpty()))
        {
            send.setOptions(options);
        }
        JsonResponse response = handleErrorResponse(getClient().multiSend(send));
        LOG.debug("Response: {}",response.getResponse());
        
        return response.getResponse();

	}
	
    protected JsonResponse handleErrorResponse(JsonResponse resp) throws ApiException
    {
        if(!resp.isOK())
        {
           throw new ApiException(200,resp.getResponse().get("errormsg").toString(), resp.getResponse());
        }
        
        return resp;
    }
    

    public SailthruClient getClient()
    {
        if(null == this.client)
        {
            setClient(new SailthruClient(apiKey, apiSecret, apiURL));
        }
        
        return this.client;
    }
    
    public void setClient(SailthruClient client)
    {
        this.client = client;
    }
}
