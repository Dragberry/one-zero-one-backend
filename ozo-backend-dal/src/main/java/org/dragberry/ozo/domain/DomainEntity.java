package org.dragberry.ozo.domain;

import java.io.Serializable;

public interface DomainEntity extends Serializable {
	
	Serializable getEntityKey();

}
