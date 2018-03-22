package com.fyj.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter{


	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		String fallbackUrl = (String)getSubject(request,response).getSession().getAttribute("authc.fallbackUrl");
	    if(StringUtils.isEmpty(fallbackUrl)){
	    	fallbackUrl = getSuccessUrl();
	    }
	    WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
	}

	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		request.setAttribute(getFailureKeyAttribute(), ae);
	}

	
}
