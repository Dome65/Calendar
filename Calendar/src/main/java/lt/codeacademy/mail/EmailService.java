package lt.codeacademy.mail;

public interface EmailService {
	
	String sendSimpleMail(EmailDetails details);

	String sendMailWithAttachment(EmailDetails details);
}
