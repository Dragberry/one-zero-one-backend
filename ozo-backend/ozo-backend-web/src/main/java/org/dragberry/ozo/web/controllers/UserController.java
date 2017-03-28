package org.dragberry.ozo.web.controllers;

import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dragberry.ozo.dao.UserDao;
import org.dragberry.ozo.domain.User;
import org.dragberry.ozo.web.exceptions.UserCreationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	private static final Logger LOG = LogManager.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/app/{appVersion}/user/new", method = RequestMethod.POST)
	@ResponseBody
	public String createNewUser(@RequestBody String defultUserId) {
		LOG.debug("New user request is recieved. Id=" + defultUserId);
		User user = new User();
		for (int i = 0; i < 5; i++) {
			user.setUserId(UUID.randomUUID().toString());
			user.setEmail("user@email.com");
			user.setUserName("user");
			LOG.debug("New user id genreated=" + user.getUserId());
			try {
				user = userDao.create(user);
				return user.getUserId();
			} catch (ConstraintViolationException exc) {
				LOG.warn("User with such id=" + user.getUserId() + " already exists", exc);
			} catch (Exception exc) {
				LOG.error("An error has occured during user creation!", exc);
				throw new UserCreationError();
			}
		}
		throw new UserCreationError();
	}
}
