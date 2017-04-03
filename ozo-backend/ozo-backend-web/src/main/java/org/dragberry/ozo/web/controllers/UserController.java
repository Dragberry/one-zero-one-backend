package org.dragberry.ozo.web.controllers;

import java.util.UUID;

import javax.persistence.PersistenceException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dragberry.ozo.common.user.NewUserRequest;
import org.dragberry.ozo.common.user.NewUserResponse;
import org.dragberry.ozo.dao.UserDao;
import org.dragberry.ozo.domain.User;
import org.dragberry.ozo.web.exceptions.UserAlreadyExistsError;
import org.dragberry.ozo.web.exceptions.UserCreationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	private static final Logger LOG = LogManager.getLogger(UserController.class);
	
	private final static String UNIQUE_ID_CONSTRAINT = "U_USER_ID";
	private final static String UNIQUE_NAME_CONSTRAINT = "U_USER_NAME";

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/app/{appVersion}/user/new", method = RequestMethod.POST)
	@ResponseBody
	public NewUserResponse createNewUser(@RequestBody NewUserRequest userRequest) {
		LOG.debug("New user request is recieved: " + userRequest);
		User user = new User();
		for (int i = 0; i < 5; i++) {
			user.setUserId(UUID.randomUUID().toString());
			user.setEmail("user@email.com");
			user.setUserName(userRequest.getUserName());
			LOG.debug("New user id genreated=" + user.getUserId());
			try {
				user = userDao.create(user);
				NewUserResponse response = new NewUserResponse();
				response.setUserId(user.getUserId());
				response.setUserName(user.getUserName());
				return response;
			} catch (JpaSystemException exc) {
				if (exc.getCause() instanceof PersistenceException) {
					PersistenceException pe = (PersistenceException) exc.getCause();
					if (pe.getCause() instanceof ConstraintViolationException) {
						if (UNIQUE_ID_CONSTRAINT.equalsIgnoreCase(((ConstraintViolationException) pe.getCause()).getConstraintName())) {
							LOG.warn("User with such id=" + user.getUserId() + " already exists", exc);
						} else if (UNIQUE_NAME_CONSTRAINT.equalsIgnoreCase(((ConstraintViolationException) pe.getCause()).getConstraintName())) {
							LOG.warn("User with such name=" + user.getUserName() + " already exists", exc);
							throw new UserAlreadyExistsError();
						}
					} else {
						LOG.error("An error has occured during user creation!", exc);
						throw new UserCreationError();
					}
				} else {
					LOG.error("An error has occured during user creation!", exc);
					throw new UserCreationError();
				}
			} catch (Exception exc) {
				LOG.error("An error has occured during user creation!", exc);
				throw new UserCreationError();
			}
		}
		throw new UserCreationError();
	}
}
