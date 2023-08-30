package com.cokkiri.secondhand.global.auth.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cokkiri.secondhand.global.auth.dto.request.GeneralLogInRequest;
import com.cokkiri.secondhand.global.auth.dto.request.GeneralSignUpRequest;
import com.cokkiri.secondhand.global.auth.dto.response.JwtTokenResponse;
import com.cokkiri.secondhand.global.auth.infrastructure.JwtAuthHttpResponseManager;
import com.cokkiri.secondhand.global.auth.service.GeneralAuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class GeneralAuthController {

	private final JwtAuthHttpResponseManager jwtAuthHttpResponseManager;
	private final GeneralAuthService generalAuthService;

	@PostMapping("/api/login")
	public ResponseEntity<JwtTokenResponse> login(
		@RequestBody @Valid GeneralLogInRequest signInRequest,
		HttpServletResponse response) {

		JwtTokenResponse jwtTokenResponse = generalAuthService.logIn(signInRequest);

		jwtAuthHttpResponseManager.setAuthHttpResponse(response, jwtTokenResponse);

		return ResponseEntity.ok(jwtTokenResponse);
	}

	@PostMapping("/api/users")
	public ResponseEntity<Void> signUp(
		@RequestBody @Valid GeneralSignUpRequest signUpRequest) {

		generalAuthService.signUp(signUpRequest);

		return ResponseEntity.ok().build();
	}
}