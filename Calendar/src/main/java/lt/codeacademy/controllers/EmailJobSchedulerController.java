package lt.codeacademy.controllers;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lt.codeacademy.email.EmailJob;
import lt.codeacademy.email.ScheduleEmailResponse;
import lt.codeacademy.models.Notification;
import lt.codeacademy.models.User;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
public class EmailJobSchedulerController {
	private static final Logger logger = LoggerFactory.getLogger(EmailJobSchedulerController.class);

	@Autowired
	private Scheduler scheduler;

	@PostMapping("/scheduleEmail")
	public ResponseEntity<ScheduleEmailResponse> scheduleEmail(@Valid @RequestBody Notification notification) {
		try {
			ZonedDateTime dateTime = ZonedDateTime.of(notification.getDateTime(), notification.getTimeZone());
			if (dateTime.isBefore(ZonedDateTime.now())) {
				ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
						"dateTime must be after current time");
				return ResponseEntity.badRequest().body(scheduleEmailResponse);
			}

			JobDetail jobDetail = buildJobDetail(notification);
			Trigger trigger = buildJobTrigger(jobDetail, dateTime);
			scheduler.scheduleJob(jobDetail, trigger);

			ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(true, jobDetail.getKey().getName(),
					jobDetail.getKey().getGroup(), "Email Scheduled Successfully!");
			return ResponseEntity.ok(scheduleEmailResponse);
		} catch (SchedulerException ex) {
			logger.error("Error scheduling email", ex);

			ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
					"Error scheduling email. Please try later!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleEmailResponse);
		}
	}

	private JobDetail buildJobDetail(@Valid Notification notification) {
		JobDataMap jobDataMap = new JobDataMap();

		jobDataMap.put("email", notification.getUserEmail());
		jobDataMap.put("subject", notification.getName());
		jobDataMap.put("body", notification.getContent());

		return JobBuilder.newJob(EmailJob.class).withIdentity(UUID.randomUUID().toString(), "email-jobs")
				.withDescription("Send Email Job").usingJobData(jobDataMap).storeDurably().build();
	}

	private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
		return TriggerBuilder.newTrigger().forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "email-triggers").withDescription("Send Email Trigger")
				.startAt(Date.from(startAt.toInstant()))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow()).build();
	}
}
