package org.dragberry.ozo.common.audit;

import java.io.Serializable;

public class AuditEventRequest implements Serializable {

	private static final long serialVersionUID = -8571910255196651479L;
	
	private String userId;
	
	private AuditEventType type;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AuditEventRequest: [userId=")
			.append(userId)
			.append("][type=")
			.append(type)
			.append("]");
		return sb.toString();
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AuditEventType getType() {
		return type;
	}

	public void setType(AuditEventType type) {
		this.type = type;
	}
	
}
