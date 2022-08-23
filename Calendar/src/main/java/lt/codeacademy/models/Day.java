package lt.codeacademy.models;

import java.time.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "days")
public class Day {
	
	//gali prireikt DateTimeFormatter, tureti omenyje
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	LocalTime time;
	LocalDate date;
	
	@OneToMany(mappedBy="day")
	Set<Notification> notifications;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public Day(LocalTime time, LocalDate date, Set<Notification> notifications) {
		super();
		this.time = time;
		this.date = date;
		this.notifications = notifications;
	}

	public Day() {
		super();
	}

	@Override
	public String toString() {
		return "Day [id=" + id + ", time=" + time + ", date=" + date + ", notifications=" + notifications + "]";
	}
	
	
	
	
	
	
}
