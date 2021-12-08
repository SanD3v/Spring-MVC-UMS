package com.um.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.um.model.User;
import com.um.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(value = "/user")
	public ModelAndView getAllUsers() {
		List<User> users = userService.getAllUserr();
		Collections.sort(users, (a, b) -> (int) (a.getUserId() - b.getUserId()));
		ModelAndView mav = new ModelAndView("show_users");
		mav.addObject("users", users);
		return mav;
	}

	@GetMapping(value = "/addUserForm")
	public ModelAndView addUserForm() {
		User user = new User();
		ModelAndView mav = new ModelAndView("user_form");
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping(value = "/saveUser")
	public String saveUser(@ModelAttribute User user) {
		userService.addUser(user);
		return "redirect:/user";

	}

	@GetMapping(value = "/updateForm")
	public ModelAndView updateUser(@RequestParam("userId") Long userId) {
		User user = userService.findUserById(userId);
		ModelAndView mav = new ModelAndView("user_form");
		mav.addObject("user", user);
		return mav;
	}

	@GetMapping(value = "/deleteForm")
	public String deleteUser(@RequestParam("userId") Long userId) {
		userService.deleteUser(userId);

		return "redirect:/user";
	}

}
