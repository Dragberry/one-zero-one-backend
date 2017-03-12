package org.dragberry.ozo.common.audit;

public class LevelAttemptAuditEventRequest extends AuditEventRequest {

	private static final long serialVersionUID = 6427743907954522214L;

	private String levelId;
	
	private LevelAttemptStatus status;
	
	private Integer time;
	
	private Integer steps;
	
	private Integer lostUnits;

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
