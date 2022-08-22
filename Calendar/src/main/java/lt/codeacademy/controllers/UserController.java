package lt.codeacademy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.codeacademy.repositories.UserRepo;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	UserRepo userRepo;

	@PostMapping("/register")
	public String register() {

		return "register";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/index")
	public String calendar() {
		return "index";
	}
}
