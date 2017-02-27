package org.dragberry.ozo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LEVEL_RESULT_TIME")
public class DoubleLevelResult extends LevelResult<Double> {

	private static final long serialVersionUID = 7658620888686769960L;
	
}
