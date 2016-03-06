/**
 * 
 */
package org.krams.tutorial.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.krams.tutorial.domain.User;
import org.krams.tutorial.json.CustomGenericResponse;
import org.krams.tutorial.json.CustomUserResponse;
import org.krams.tutorial.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/crud")
public class UserController {
	protected static Logger LOGGER = Logger.getLogger("controller");

	@Resource(name="userService")
	private IUserService userService;


	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody  CustomUserResponse getAll() {
		LOGGER.debug("Received request to get all users");

		// Retrieve all users from the service
		List<User> users = userService.getAll();

		// Initialize our custom user response wrapper
		CustomUserResponse response = new CustomUserResponse();

		// Assign the result from the service to this response
		response.setRows(users);

		// Assign the total number of records found. This is used for paging
		response.setRecords( String.valueOf(users.size()) );

		response.setPage( "1" );
		response.setTotal( "10" );

		return response;
	}

	/**
	 * Edit the current user.
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody CustomGenericResponse edit(
			@RequestParam("id") String id,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		LOGGER.debug("Received request to edit user");

		// Construct our user object
		// Assign the values from the parameters
		User user = new User();
		user.setId( Long.valueOf(id) );
		user.setFirstName(firstName);
		user.setLastName(lastName);

		// Do custom validation here or in your service

		// Call service to edit
		Boolean success = userService.edit(user);

		// Check if successful
		if ( success == true ) {
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
	public @ResponseBody CustomGenericResponse add(
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName){
		LOGGER.debug("Received request to add a new user");

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);

		// Call service to add
		Boolean success = userService.add(user);

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


	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody CustomGenericResponse delete(@RequestParam("id") String id) {
		LOGGER.debug("Received request to delete an existing user");

		User user = new User();
		user.setId( Long.valueOf(id) );

		// Call service to add
		Boolean success = userService.delete(user);

		// Check if successful
		if ( success == true ) {
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