package org.dragberry.ozo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LEVEL_RESULT_STEPS")
public class StepsLevelResult extends LevelResult<Integer> {

	private static final long serialVersionUID = 7658620888686769960L;

}
