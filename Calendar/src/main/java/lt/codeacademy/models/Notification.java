package lt.codeacademy.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Transactional
@Table(name = "notifications")
public class Notification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@NonNull
	String content;
	
	@NonNull
	String name;

	@NonNull
	LocalDateTime dateTime;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "user_email", nullable = false)
	private String userEmail;
	
	@NotNull
	@Builder.Default
	private ZoneId timeZone = ZoneId.systemDefault();

	// It is only for foreign key.

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false),
			@JoinColumn(name = "user_email", referencedColumnName = "email", insertable = false, updatable = false) 
			})
	private User user;

}
