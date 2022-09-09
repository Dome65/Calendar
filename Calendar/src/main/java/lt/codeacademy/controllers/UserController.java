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

import lt.codeacademy.models.User;
import lt.codeacademy.repositories.UserRepo;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	private UserRepo userRepo;

	public UserController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("/users")
	Collection<User> users() {
		return userRepo.findAll();
	}

	@GetMapping("/user/{id}")
	ResponseEntity<?> getUser(@PathVariable Long id) {
		Optional<User> notification = userRepo.findById(id);
		return notification.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

//	//cia pagal varda padaryt
//	@GetMapping("/userByName/{userName}")
//	ResponseEntity<?> getUserByName(@PathVariable String userName) {
//		Optional<User> notification = userRepo.findBy(null, null);
//		return notification.map(response -> ResponseEntity.ok().body(response))
//				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	}

	@PostMapping("/user")
	ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
		log.info("Request to create user: {}", user);
		User result = userRepo.save(user);
		return ResponseEntity.created(new URI("/api/user/" + result.getId())).body(result);
	}

	@PutMapping("/user/{id}")
	ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
		log.info("Request to update user: {}", user);
		User result = userRepo.save(user);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		log.info("Request to delete user: {}", id);
		userRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
