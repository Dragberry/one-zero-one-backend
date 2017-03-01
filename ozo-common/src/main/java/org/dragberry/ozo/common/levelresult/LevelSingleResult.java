package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;

public class LevelSingleResult<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = -1014358995437087396L;
	
	public LevelSingleResult() {}

	public LevelSingleResult(T personal, T worlds, String owner) {
		this.personal = personal;
		this.worlds = worlds;
		this.owner = owner;
	}

	private T personal;
	
	private T worlds;
	
	private String owner;

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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	

}
