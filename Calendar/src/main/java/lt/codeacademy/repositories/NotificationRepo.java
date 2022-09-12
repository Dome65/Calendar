package lt.codeacademy.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.models.Notification;
import lt.codeacademy.models.User;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
	Collection<Notification> findAllByUserId(String id);
}
