package org.dragberry.ozo.service.users;

import java.text.MessageFormat;

import javax.persistence.NoResultException;

import org.dragberry.ozo.dao.UserDao;
import org.dragberry.ozo.domain.User;
import org.dragberry.ozo.web.exceptions.UserNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBean implements UserService {
	
	private static final String USER_404_MSG = "User with id {0} does not exist!";
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User findUserById(String userId) {
		User user = null;
		try {
			user = userDao.findById(userId);
		} catch (NoResultException nre) {
			// TODO: Log
		}
		if (user == null) {
			throw new UserNotFountException(MessageFormat.format(USER_404_MSG, userId));
		}
		return user;
	}
	

}
