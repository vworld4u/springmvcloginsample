package com.vworld4u.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vworld4u.models.User;
import com.vworld4u.models.UserType;
import com.vworld4u.services.RegistrationHandler;
import com.vworld4u.services.UserService;

@Controller
public class ApplicationController {
	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	private UserService userService;
	@Autowired RegistrationHandler registrationHandler;

	@RequestMapping(method=RequestMethod.GET, value="/register")
	public String register(Model model) {
		log.info("GET register");
		User user = new User();
		user.setName("Venki");
		user.setEmail("venki@gmail.com");
		user.setDateOfBirth(new Date());
		user.setPassword("pass");
		user.setUserType(UserType.Student);
		model.addAttribute("user", user);
		return "register";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String rootPath() {
		log.info("GET rootPath: ");
		return "hello";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	public String hello() {
		log.info("GET hello: ");
		return "hello";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String home(Model model) {
		log.info("GET home: ");
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
			log.info("Sending Email");
			registrationHandler.sendRegistrationEmail(registeredUser);
			model.addAttribute("registeredUser", registeredUser);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return "register";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good idea to show login screen again.
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/profile")
	public String getProfile(Model model) {
		log.info("GET profile: ");
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String email;
			if (auth.getPrincipal() instanceof UserDetails) {
				email = ((UserDetails) auth.getPrincipal()).getUsername();
			} else {
				email = auth.getPrincipal().toString();
			}
			log.info("getProfile: Email = " + email);
			User user = userService.getUserByEmail(email);
			log.info("getProfile: User fetched = " + user);
			model.addAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return "profile";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/profile")
	public String updateProfile(@ModelAttribute User user, Model model) {
		log.info("POST profile: " + user);
		try {
			User updatedUser = userService.editUser(user);
			log.info("updateProfile: Updated User = " + updatedUser);
			model.addAttribute("updatedUser", updatedUser);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			return "profile";
		}
		return "profile";
	}

}
