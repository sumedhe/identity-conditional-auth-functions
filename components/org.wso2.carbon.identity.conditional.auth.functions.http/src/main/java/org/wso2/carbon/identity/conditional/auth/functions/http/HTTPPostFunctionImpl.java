/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.conditional.auth.functions.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;


/**
 * Implementation of the {@link HTTPPostFunction}
 */
public class HTTPPostFunctionImpl extends AbstractHTTPFunction implements HTTPPostFunction {

    private static final Log LOG = LogFactory.getLog(HTTPPostFunctionImpl.class);

    public HTTPPostFunctionImpl() {

       super();
    }

    @Override
    public void httpPost(String epUrl, Map<String, Object> payloadData, Map<String, Object> eventHandlers) {

            HttpPost request = new HttpPost(epUrl);
            request.setHeader(ACCEPT, TYPE_APPLICATION_JSON);
            request.setHeader(CONTENT_TYPE, TYPE_APPLICATION_JSON);

            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, Object> dataElements : payloadData.entrySet()) {
                jsonObject.put(dataElements.getKey(), dataElements.getValue());
            }
            request.setEntity(new StringEntity(jsonObject.toJSONString(), StandardCharsets.UTF_8));
            executeHttpMethod(request, eventHandlers);
    }
}
