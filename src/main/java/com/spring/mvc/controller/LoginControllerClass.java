package com.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.service.AuthService;

@Controller
@RequestMapping(value = "/user")
public class LoginControllerClass {

	@Autowired
	private AuthService authenticateService; // This will auto-inject the authentication service into the
	// controller.

	// Checks if the user credentials are valid or not.
	public ModelAndView find(@RequestParam("username") String name, @RequestParam("password") String password) {

		String msg = "";
		boolean isvlaid = authenticateService.findUser(name, password);

		System.out.println("Is valid user" + isvlaid);
		if (isvlaid) {

			msg = "Welcome " + name + "!";
		} else {
			msg = "Invalid Credentials";
		}

		return new ModelAndView("result", "output", msg);

	}
}
