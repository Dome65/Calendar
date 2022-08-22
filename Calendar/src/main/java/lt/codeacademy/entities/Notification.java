package lt.codeacademy.entities;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String content;

	public enum Type {
		WORK, STUDIES, EVENT, BIRTHDAY, HOLIDAY, CUSTOM
	}
	
	Type theType;

	public Notification() {
		super();
	}

	public Notification(String content, Type theType) {
		super();
		this.content = content;
		this.theType = theType;
	}

	public Type getTheType() {
		return theType;
	}

	public void setTheType(Type theType) {
		this.theType = theType;
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

	@Override
	public String toString() {
		return "Notification [id=" + id + ", content=" + content + ", theType=" + theType + "]";
	}



}
