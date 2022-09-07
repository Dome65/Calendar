package lt.codeacademy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.codeacademy.models.Notification;
import lt.codeacademy.repositories.UserRepo;
import lt.codeacademy.services.NotificationService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/index")
	public String showUserList(Model model) {
		model.addAttribute("notifications", notificationService.findAll());
		return "home";
	}

	@GetMapping("/signup")
	public String showSignUpForm(Notification notification) {
		return "add-notification";
	}

	@GetMapping("/addnotification")
	public String addNotification(@Validated Notification notification, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-notification";
		}

		notificationService.save(notification);
		return "redirect:/index";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Notification notification = notificationService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("notification", notification);

		return "update-user";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Validated Notification notification, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			notification.setId(id);
			return "update-user";
		}

		notificationService.save(notification);

		return "redirect:/index";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Notification notification = notificationService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		notificationService.delete(notification);

		return "redirect:/index";

	}

}
