package lt.codeacademy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import lt.codeacademy.models.User;
import lt.codeacademy.repositories.UserRepo;



public class UserService {
	
	@Autowired
	private UserRepo userRepo;

	public void save(User user) {
		userRepo.delete(user);
	}

	public void delete(User user) {
		userRepo.delete(user);
	}

	public Iterable<User> findAll() {		
		return userRepo.findAll();
	}
	
	public Optional<User> findById(long id) {
		return userRepo.findById(id);
	}
}
