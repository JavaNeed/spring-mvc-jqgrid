package org.krams.tutorial.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/main")
public class MediatorController {

	protected static Logger logger = LoggerFactory.getLogger("controller");

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsersPage() {
		logger.debug("Received request to show users page");
		return "users";
	}
}
