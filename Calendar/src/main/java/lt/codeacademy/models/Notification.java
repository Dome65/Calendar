package lt.codeacademy.models;

import java.time.LocalTime;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String content;
	String name;
	LocalTime time;
	
	@ManyToOne
    @JoinColumn(name="day_id", nullable=false)
	Day day;

	public Notification() {
		super();
	}

	public Notification(String content, String name, LocalTime time, Day day) {
		super();
		this.content = content;
		this.name = name;
		this.time = time;
		this.day = day;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", content=" + content + ", name=" + name + ", time=" + time + ", day=" + day
				+ "]";
	}

	

	

	



}
