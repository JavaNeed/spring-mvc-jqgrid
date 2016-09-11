/**
 * 
 */
package org.krams.tutorial.controller;

import java.util.List;

import javax.annotation.Resource;

import org.krams.tutorial.json.CustomGenericResponse;
import org.krams.tutorial.json.CustomUserResponse;
import org.krams.tutorial.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.DBObject;

/**
 * Handles CRUD requests for users
 * 
 */
@Controller
@RequestMapping("/crud")
public class UserController {

	protected static Logger logger = LoggerFactory.getLogger("controller");

	@Resource(name = "userService")
	private IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody CustomUserResponse getAll() {
		logger.debug("Received request to get all users");

		// Retrieve all users from the service
		List<DBObject> users = userService.getAll();

		// Initialize our custom user response wrapper
		CustomUserResponse response = new CustomUserResponse();

		// Assign the result from the service to this response
		response.setRows(users);

		// Assign the total number of records found. This is used for paging
		response.setRecords(String.valueOf(users.size()));
		response.setPage("1");
		response.setTotal("10");
		return response;
	}

	/**
	 * Edit the current user.
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody CustomGenericResponse edit(@RequestParam("id") String id,
													@RequestParam("firstName") String firstName, 
													@RequestParam("lastName") String lastName) {
		
		logger.debug("Received request to edit user");

		// Edit existing user by delegating to UserService
		Boolean success = userService.edit(id, firstName, lastName);

		// Check if successful
		if (success == true) {
			// Success. Return a custom response
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess(true);
			response.setMessage("Action successful!");
			return response;

		} else {
			// A failure. Return a custom response as well
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess(false);
			response.setMessage("Action failure!");
			return response;
		}

	}

	/**
	 * Add a new user
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CustomGenericResponse add(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		logger.debug("Received request to add a new user");

		// Add a new user by delegating to the UserService
		Boolean success = userService.add(firstName, lastName);

		// Check if successful
		if (success == true) {
			// Success. Return a custom response
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess(true);
			response.setMessage("Action successful!");
			return response;

		} else {
			// A failure. Return a custom response as well
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess(false);
			response.setMessage("Action failure!");
			return response;
		}

	}

	/**
	 * Delete an existing user
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody CustomGenericResponse delete(@RequestParam("id") String id) {

		logger.debug("Received request to delete an existing user");

		// Delete existing user by delegating to the UserService
		Boolean success = userService.delete(id);

		// Check if successful
		if (success == true) {
			// Success. Return a custom response
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess(true);
			response.setMessage("Action successful!");
			return response;

		} else {
			// A failure. Return a custom response as well
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess(false);
			response.setMessage("Action failure!");
			return response;
		}
	}

}