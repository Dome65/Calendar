package lt.codeacademy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.entities.Notification;
import lt.codeacademy.repositories.NotificationRepo;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepo notificationRepo;

	public void save(Notification notification) {
		notificationRepo.delete(notification);
	}

	public void delete(Notification notification) {
		notificationRepo.delete(notification);
	}

	public Iterable<Notification> findAll() {		
		return notificationRepo.findAll();
	}
	
	public Optional<Notification> findById(long id) {
		return notificationRepo.findById(id);
	}
}
