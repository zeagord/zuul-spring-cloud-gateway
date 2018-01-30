package com.bytesville.blog.microservices.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorProcessingFilter extends ZuulFilter{
    Logger LOG = LoggerFactory.getLogger(ModifyResponseBodyFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
       /* RequestContext context = RequestContext.getCurrentContext();
        LOG.info("Zuul Status Custom--> " + context.getResponse().getStatus());
        return  (context.getResponse().getStatus() != 200);*/
       return false;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        context.setResponseBody("{\"status\": \"error\", \"code\": 2004}");
        return null;
    }
}
