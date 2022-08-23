package lt.codeacademy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.models.Day;
import lt.codeacademy.repositories.DayRepo;

@Service
public class DayService {

	@Autowired
	private DayRepo dayRepo;
	
	public void save(Day day) {
		dayRepo.save(day);
	}
	
	public void delete(Day day) {		
		dayRepo.delete(day);
	}
}
