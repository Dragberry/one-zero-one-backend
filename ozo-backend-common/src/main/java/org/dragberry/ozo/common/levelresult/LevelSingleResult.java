package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;

public class LevelSingleResult<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = -1014358995437087396L;
	
	public LevelSingleResult() {}

	public LevelSingleResult(T personal, T worlds, String ownerUserEmail) {
		this.personal = personal;
		this.worlds = worlds;
		this.ownerUserEmail = ownerUserEmail;
	}

	private T personal;
	
	private T worlds;
	
	private String ownerUserEmail;

	public T getPersonal() {
		return personal;
	}

	public void setPersonal(T personal) {
		this.personal = personal;
	}

	public T getWorlds() {
		return worlds;
	}

	public void setWorlds(T worlds) {
		this.worlds = worlds;
	}

	public String getOwnerUserEmail() {
		return ownerUserEmail;
	}

	public void setOwnerUserEmail(String ownerUserEmail) {
		this.ownerUserEmail = ownerUserEmail;
	}

}
