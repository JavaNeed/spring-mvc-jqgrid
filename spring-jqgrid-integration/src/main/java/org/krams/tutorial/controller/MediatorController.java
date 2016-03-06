package org.krams.tutorial.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/main")
public class MediatorController {
	private static Logger logger = Logger.getLogger("controller");

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsersPage() {
		logger.debug("Received request to show users page");

		// This will resolve to /WEB-INF/jsp/users.jsp page
		return "users";
	}
}
