package com.vworld4u.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vworld4u.models.User;
import com.vworld4u.models.UserType;
import com.vworld4u.services.UserService;

@Controller
public class ApplicationController {
	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method=RequestMethod.GET, value="/register")
	public String register(Model model) {
		log.info("GET register");
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	public String hello() {
		log.info("GET hello: ");
		return "hello";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String home(Model model) {
		log.info("GET home: ");
		User user = new User();
		model.addAttribute("user", user);
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(Model model) {
		log.info("GET login: ");
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String loginUser(@ModelAttribute User user, Model model) {
		log.info("POST login: " + user);
		try {
			User exUser = userService.getUserByEmail(user.getEmail());
			log.info("POST login: ExUser = " + exUser);
			if (exUser == null || !exUser.getPassword().equals(user.getPassword())) {
				throw new RuntimeException("Invalid Credentials");
			}
			log.info("registeredUser: " + exUser);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			return "login";
		}
		return "home";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public String register(@ModelAttribute User user, Model model) {
		log.info("POST register: " + user);
		try {
			User registeredUser = userService.register(user);
			log.info("registeredUser: " + registeredUser);
			model.addAttribute("registeredUser", registeredUser);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return "register";
	}
}
