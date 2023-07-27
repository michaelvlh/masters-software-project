package com.team12.foodforall.controller.user;

import com.team12.foodforall.domain.LoginForm;
import com.team12.foodforall.domain.RegisterForm;
import com.team12.foodforall.domain.User;
import com.team12.foodforall.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author: Heng Gao
 * @date: 08/03/2022 :17:06
 **/
@Controller
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("/login")
	public String showLoginForm(LoginForm loginForm, Model model, String loginError, String logout) {
		if (loginError != null) {
			model.addAttribute("errorMsg", "Your username and password are invalid.");
		}
		if (logout != null) {
			model.addAttribute("msg", "You have been logged out successfully.");
		}

		return "login";
	}

	// Retrieve user info supported by spring security
	public String getCurrentLoginUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // this is a spring security object
		if (!authentication.isAuthenticated()) {
			return null;
		}

		Object principal = authentication.getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		return username;
	}

	@GetMapping("/register")
	public String showRegisterForm(RegisterForm registerForm) {
		return "register";
	}


	/**
	 * Returns the target page in string form based on the registration result.
	 * <p>
	 * This method will validate and register the given user.
	 * Password will be encrypted.
	 *
	 * @param registerForm user input form, consist of all information.
	 * @param result       result can be used to store errors, which will be further
	 *                     used by thymeleaf to control DOM visibility
	 * @param model        the model is basically the page, you can add attributes(simply key/value pairs)
	 *                     which can be accessed by thymeleaf templates.
	 * @return a string of page name which can be found insequence
	 * under /resources, /resources/template, /resources/static /resources/public.
	 */
	@PostMapping("/register")
	public String addUser(@Valid RegisterForm registerForm, BindingResult result, Model model) {

		// handle error actively here
		if (result.hasErrors()) {
			//TODO: replace by throw exception if needed
			return "register";
		}

		User user = userService.registerUser(registerForm);

		if (user == null) { //failed
			throw new RuntimeException();
		}

		// if success
		return "redirect:/login?registerSuccess";
	}

}
