package com.spring.development.controller;

import com.spring.development.dto.ApplicationRequest;
import com.spring.development.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

	public static final String MESSAGE_SERVER_IS_STARTED = "Hello, Server is started!";

	private final ApplicationService versionService;

	@GetMapping("/status")
	public ResponseEntity<?> status() {
		return new ResponseEntity<>(MESSAGE_SERVER_IS_STARTED, HttpStatus.OK);
	}

	@GetMapping("/version")
	public ResponseEntity<?> version(
		@RequestHeader MultiValueMap<String, String> httpHeaders,
		@RequestBody ApplicationRequest request
	) {
		var response = versionService.version(httpHeaders, request);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
