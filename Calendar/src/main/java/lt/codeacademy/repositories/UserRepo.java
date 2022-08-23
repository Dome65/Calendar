package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.models.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
