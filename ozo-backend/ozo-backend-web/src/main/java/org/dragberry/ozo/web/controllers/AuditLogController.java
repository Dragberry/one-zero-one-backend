package org.dragberry.ozo.web.controllers;

import java.time.LocalDateTime;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dragberry.ozo.common.audit.AuditEventRequest;
import org.dragberry.ozo.common.audit.LevelAttemptAuditEventRequest;
import org.dragberry.ozo.dao.AuditEventDao;
import org.dragberry.ozo.dao.LevelAttemptDao;
import org.dragberry.ozo.domain.AuditEvent;
import org.dragberry.ozo.domain.LevelAttempt;
import org.dragberry.ozo.domain.User;
import org.dragberry.ozo.service.levels.LevelService;
import org.dragberry.ozo.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuditLogController {

	private static final Logger LOG = LogManager.getLogger(AuditLogController.class);

	@Autowired
	private AuditEventDao auditEventDao;
	
	@Autowired
	private LevelAttemptDao levelAttemptDao;
	
	@Autowired
	private UserService userSerice;
	
	@Autowired
	private LevelService levelService;

	@RequestMapping(value = "/audit/events/new/simple", method = RequestMethod.POST)
	@ResponseBody
	public void createAuditEvent(@RequestBody AuditEventRequest request) {
		new AuditRequestProcessor<>().createEvent(request);
	}
	
	@RequestMapping(value = "/audit/events/new/levelattempt", method = RequestMethod.POST)
	@ResponseBody
	public void createAuditEvent(@RequestBody LevelAttemptAuditEventRequest request) {
		new AuditRequestProcessor<LevelAttemptAuditEventRequest>() {
			
			@Override
			protected void createAdditionalEvent(LevelAttemptAuditEventRequest request, AuditEvent auditLog) {
				LevelAttempt attempt = new LevelAttempt();
				attempt.setAuditEvent(auditLog);
				attempt.setLevel(levelService.getLevel(request.getLevelId()));
				attempt.setLostUnits(request.getLostUnits());
				attempt.setSteps(request.getSteps());
				attempt.setTime(request.getTime());
				attempt.setStatus(request.getStatus());
				levelAttemptDao.create(attempt);
			}
		}.createEvent(request);
	}
	
	private class AuditRequestProcessor<T extends AuditEventRequest> {
		
		void createEvent(T request) {
			LOG.debug("Audit event request has recived: " + request);
			User user = userSerice.findUserById(request.getUserId());

			AuditEvent auditLog = new AuditEvent();
			auditLog.setUser(user);
			auditLog.setType(request.getType());
			auditLog.setDate(LocalDateTime.now());
			
			auditEventDao.create(auditLog);
			
			createAdditionalEvent(request, auditLog);
		}

		protected void createAdditionalEvent(T request, AuditEvent auditLog) {
			// nothing by default
		}
		
		
	}
}
