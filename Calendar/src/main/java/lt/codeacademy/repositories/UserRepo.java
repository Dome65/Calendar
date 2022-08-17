package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
