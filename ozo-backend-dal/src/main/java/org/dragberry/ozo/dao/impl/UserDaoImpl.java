package org.dragberry.ozo.dao.impl;

import org.dragberry.ozo.dao.UserDao;
import org.dragberry.ozo.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}
}
