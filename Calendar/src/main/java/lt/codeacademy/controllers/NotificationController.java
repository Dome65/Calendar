package lt.codeacademy.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lt.codeacademy.models.Notification;
import lt.codeacademy.models.User;
import lt.codeacademy.repositories.NotificationRepo;
import lt.codeacademy.repositories.UserRepo;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class NotificationController {

	private final Logger log = LoggerFactory.getLogger(NotificationController.class);
	private UserRepo userRepo;
	private NotificationRepo notificationRepo;

	public NotificationController(NotificationRepo notificationRepo, UserRepo userRepo) {
		this.notificationRepo = notificationRepo;
		this.userRepo = userRepo;
	}

	@GetMapping("/notifications")
	Collection<Notification> notifications(Principal principal) {
		return notificationRepo.findAllByUserId(principal.getName());
	}

	@GetMapping("/notification/{id}")
	ResponseEntity<?> getNotification(@PathVariable Long id) {
		Optional<Notification> notification = notificationRepo.findById(id);
		return notification.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/notification")
	ResponseEntity<Notification> createNotification(@Valid @RequestBody Notification notification,
			@AuthenticationPrincipal OAuth2User principal) throws URISyntaxException {
		log.info("Request to create notification: {}", notification);
		Map<String, Object> details = principal.getAttributes();
		String userId = details.get("sub").toString();

		// check to see if user already exists
		Optional<User> user = userRepo.findById(userId);
		notification.setUser(
				user.orElse(new User(userId, details.get("name").toString(), details.get("email").toString())));

		Notification result = notificationRepo.save(notification);
		return ResponseEntity.created(new URI("/api/notification/" + result.getId())).body(result);
	}

	@PutMapping("/notification/{id}")
	ResponseEntity<Notification> updateNotification(@Valid @RequestBody Notification notification) {
		log.info("Request to update notification: {}", notification);
		Notification result = notificationRepo.save(notification);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "/notification/{id}")
	public ResponseEntity<?> deleteNotification(@PathVariable("id") Long id) {
		log.info("Request to delete notification: {}", id);
		notificationRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
