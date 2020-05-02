package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;
import com.musicinfofinder.musicdetectorsrv.services.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	private final static Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping("/authorize")
	public void authorize() {
		try {
			loginService.authorize();
		} catch (AuthorizeException exception) {
			logger.error("Cannot authorize", exception);
		}
	}

	@RequestMapping("/postAuthorize")
	public void postAuthorize(@RequestParam("code") String code, @RequestParam(name = "state", required = false) String state) {
		AuthorizeResponse response = new AuthorizeResponse();
		response.setCode(code);
		response.setState(state);
		loginService.postAuthorize(response);
	}
}
