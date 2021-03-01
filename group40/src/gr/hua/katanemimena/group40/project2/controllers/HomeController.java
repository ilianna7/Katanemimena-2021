package gr.hua.katanemimena.group40.project2.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			model.addAttribute("username", getCurrentUsername());
			return "emp_view_apps";
		}
		// if it is not authenticated, then go to the log in page
		return "login";
	}
		
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";
	}
	/*
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homep() {

		return "emp_view_apps";
	}*/
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/logout/";
	}
		
		private String getCurrentUsername() {
			
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();

			return username;
		}
}
