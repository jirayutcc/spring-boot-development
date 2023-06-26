package com.spring.development.filter;

import com.spring.development.config.TokenManager;
import com.spring.development.utils.JWTHelper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class WebFilter implements Filter {
	final static String ENCODING_UTF_8 = "UTF-8";

	@Autowired
	@Qualifier("getTokenManager")
	private TokenManager tokenManager;

	@Autowired
	private JWTHelper jwtHelper;

	@Override
	public void doFilter(
		ServletRequest request, ServletResponse response, FilterChain chain
	) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String servletPath = httpRequest.getServletPath();
		if (!httpRequest.getMethod().equals(HttpMethod.OPTIONS.name())
			&& !servletPath.contains("/actuator")
			&& !servletPath.contains("/internal")
			&& !servletPath.contains("/swagger")
			&& !servletPath.contains("/api-docs")
		) {
			ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpRequest);
			ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);

			try {
				String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
				if (null != token) {
					tokenManager.setJwtPayload(jwtHelper.decodeToken(token));
				}
				chain.doFilter(requestWrapper, responseWrapper);
			} finally {
				performRequestAudit(requestWrapper);
				performResponseAudit(responseWrapper);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * Log request ofter filter processed.
	 *
	 * @param requestWrapper a servlet request with content caching.
	 */
	private void performRequestAudit(ContentCachingRequestWrapper requestWrapper) {
		if (null != requestWrapper) {
			if (requestWrapper.getContentAsByteArray().length > 0) {
				String threadName = Thread.currentThread().getName();
				log.info(threadName + "-Headers:: {}",
					new ServletServerHttpRequest((HttpServletRequest) requestWrapper.getRequest()).getHeaders());
				log.info(threadName + "-Request Body:: {}",
					getPayLoadFromByteArray(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding()));
			}
		}
	}

	/**
	 * Log response ofter filter processed.
	 *
	 * @param responseWrapper a servlet request with content caching.
	 */
	private void performResponseAudit(ContentCachingResponseWrapper responseWrapper) {
		try {
			if (responseWrapper.getContentAsByteArray().length > 0) {
				log.info(Thread.currentThread().getName() + "-Response Body:: {}",
					getPayLoadFromByteArray(responseWrapper.getContentAsByteArray(), ENCODING_UTF_8));
			} else {
				performErrorResponseAudit(responseWrapper);
			}
			responseWrapper.copyBodyToResponse();
		} catch (Exception e) {
			log.error("Cannot performResponseAudit: ", e);
		}
	}

	/**
	 * Log error response ofter filter processed.
	 *
	 * @param responseWrapper a servlet request with content caching.
	 */
	private void performErrorResponseAudit(ContentCachingResponseWrapper responseWrapper) {
		log.warn("HTTP Error status Code:: {}", responseWrapper.getStatus());
	}

	/**
	 * Get request payload from request content.
	 *
	 * @param requestBuffer on array of byte content.
	 * @param charEncoding  on encoding format.
	 * @return a string of request detail.
	 */
	private String getPayLoadFromByteArray(byte[] requestBuffer, String charEncoding) {
		String payLoad;
		try {
			payLoad = new String(requestBuffer, charEncoding);
		} catch (Exception e) {
			payLoad = "Unsupported-Encoding";
		}

		return payLoad;
	}
}

