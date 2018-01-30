package com.bytesville.blog.microservices.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class VerifyRequestHeaderFilter  extends ZuulFilter{

    @Override
    public String filterType() {
        return "pre";
    }

    /***
     * Make Sure filter should not be more that 999. Default order will be take if it is more that 999
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            HttpServletRequest request = context.getRequest();
            if (request.getHeader("auth") != null) {
                context.set("Auth", "Filtered");
            } else {
                context.unset();
                context.setSendZuulResponse(false);
            }
        } catch (Exception e){
            // Catch Exception and do nothing. Typical dev mindset :D
            return null;
        }

        return null;
    }
}
