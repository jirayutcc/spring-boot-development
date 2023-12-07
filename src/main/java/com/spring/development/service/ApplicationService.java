package com.spring.development.service;

import com.spring.development.dto.ApplicationRequest;
import com.spring.development.dto.ApplicationResponse;
import com.spring.development.dto.HeaderResponse;
import com.spring.development.properties.ApplicationProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

	private final ApplicationProperty applicationProperty;

	public ApplicationResponse version(
		MultiValueMap<String, String> httpHeaders,
		ApplicationRequest request
	) {
		return ApplicationResponse.builder()
			.headerResp(HeaderResponse.mapResponse(request.getHeaderReq()))
			.body(
				ApplicationResponse.Body.builder()
					.version(applicationProperty.getVersion())
					.build()
			)
			.build();
	}
}
