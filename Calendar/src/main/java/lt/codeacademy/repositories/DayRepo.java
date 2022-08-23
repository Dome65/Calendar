package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.models.Day;


public interface DayRepo extends JpaRepository<Day, Long> {

}
