package org.dragberry.ozo.common.audit;

public class LevelAttemptAuditEventRequest extends AuditEventRequest {

	private static final long serialVersionUID = 6427743907954522214L;
	
	public static final String URL_LEVEL_ATTEMPT = "levelattempt"; 

	private String levelId;
	
	private LevelAttemptStatus status;
	
	private Integer time;
	
	private Integer steps;
	
	private Integer lostUnits;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("[levelId=")
			.append(levelId)
			.append("][status=")
			.append(status)
			.append("][time=")
			.append(time)
			.append("][steps=")
			.append(steps)
			.append("][lostUnits=")
			.append(lostUnits)
			.append("]");
		return sb.toString();
	}
	
	@Override
	public String getUrl() {
		return URL_LEVEL_ATTEMPT;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public LevelAttemptStatus getStatus() {
		return status;
	}

	public void setStatus(LevelAttemptStatus status) {
		this.status = status;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}

	public Integer getLostUnits() {
		return lostUnits;
	}

	public void setLostUnits(Integer lostUnits) {
		this.lostUnits = lostUnits;
	}
	
	
}
