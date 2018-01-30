package com.bytesville.blog.microservices.gateway;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;


/***
 * Credits : https://github.com/spring-cloud-samples/sample-zuul-filters/tree/master/src/main/java/org/springframework/cloud/samplezuulfilters
 */
public class ModifyResponseBodyFilter extends ZuulFilter {

    Logger LOG = LoggerFactory.getLogger(ModifyResponseBodyFilter.class);

    public String filterType() {
        return "post";
    }

    public int filterOrder() {
        return 998;
    }

    public boolean shouldFilter() {
       // RequestContext context = getCurrentContext();
        return false; //(context.getRequest().getParameter("service") == null &&
                // context.getResponse().getStatus() == 200);
    }

    public Object run() {
        try {
            RequestContext context = getCurrentContext();
            InputStream stream = context.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            ObjectMapper objectMapper = new ObjectMapper();
            // JsonNode is immutable and needs to get the data in Object to modfiy
            JsonNode node  = objectMapper.readTree(body);
            if (null != node.get("args") ) {
                ((ObjectNode)node).put("args", "My Custom test");
            }
            context.setResponseBody("Modified via setResponseBody(): "+node);
        }
        catch (IOException e) {
            rethrowRuntimeException(e);
        }
        return null;
    }
}