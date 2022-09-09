package lt.codeacademy.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lt.codeacademy.models.Notification;
import lt.codeacademy.repositories.NotificationRepo;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class NotificationController {

	private final Logger log = LoggerFactory.getLogger(NotificationController.class);
	private NotificationRepo notificationRepo;

	public NotificationController(NotificationRepo notificationRepo) {
		this.notificationRepo = notificationRepo;
	}

	@GetMapping("/notifications")
	Collection<Notification> notifications() {
		return notificationRepo.findAll();
	}

	@GetMapping("/notification/{id}")
	ResponseEntity<?> getNotification(@PathVariable Long id) {
		Optional<Notification> notification = notificationRepo.findById(id);
		return notification.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/notification")
	ResponseEntity<Notification> createNotification(@Valid @RequestBody Notification notification)
			throws URISyntaxException {
		log.info("Request to create notification: {}", notification);
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
