package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import lt.codeacademy.entities.Notification;


public interface NotificationRepo extends JpaRepository<Notification, Long> {

}
