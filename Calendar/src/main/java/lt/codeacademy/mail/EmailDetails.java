package lt.codeacademy.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
	
	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;
}
