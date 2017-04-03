package org.dragberry.ozo.common.user;

import org.dragberry.ozo.common.AbstractResponse;

public class NewUserResponse extends AbstractResponse {

	private static final long serialVersionUID = -3917118500480314237L;
	
	private String userId;
	
	private String userName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
