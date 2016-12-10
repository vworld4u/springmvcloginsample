package com.vworld4u;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class RecaptchaFilter implements Filter {
	@Value("${recaptcha.url}")
	private String recaptchaUrl;
	@Value("${recaptcha.secret}")
	private String recaptchaSecret;

//	public static final String recaptchaUrl = "https://www.google.com/recaptcha/api/siteverify";
//	public static final String recaptchaSecret = "6Lf_Xg4UAAAAAMxkDp6HaGHuMOHnN_fuGkJzOqBa";
	
	private static class RecaptchaResponse {
        @JsonProperty("success")
        private boolean success;
        @JsonProperty("challenge_ts")
        private String hostname;
        @JsonProperty("hostname")
        private String challengeTs;
        @JsonProperty("error-codes")
        private Collection<String> errorCodes;
    }
	private static final Logger log = LoggerFactory.getLogger(RecaptchaFilter.class);
	
	@Autowired RestTemplate restTemplate;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			Enumeration<String> en = req.getParameterNames();
			log.info(" Request Path " + req.getPathInfo());
			log.info(" Request URL  " + req.getRequestURL());
			while (en.hasMoreElements()) {
				String param = en.nextElement();
				String value = req.getParameter(param);
				log.info("Param : " + param + " Value = " + value);
			}
			if (req.getParameter("g-recaptcha-response") != null) {
				log.info("Verifying the Google Re-Captcha : ");
				RecaptchaResponse resp = (RecaptchaResponse) restTemplate.postForEntity(recaptchaUrl, createBody(recaptchaSecret, getRemoteIp(req), req.getParameter("g-recaptcha-response")), 
						RecaptchaResponse.class).getBody();
				log.info(" Response : " + resp);
				if (!resp.success) {
					log.error(" Captcha Failed !!! ");
					throw new IOException("Captcha Failed");
				}
			}
		}
		chain.doFilter(request, response);
	}

	private String getRemoteIp(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}
	
	private MultiValueMap<String, String> createBody(String secret, String remoteIp, String response) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("secret", secret);
        form.add("remoteip", remoteIp);
        form.add("response", response);
        return form;
    }
	
	@Override
	public void destroy() {
	}

}
