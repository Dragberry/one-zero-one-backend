package org.dragberry.ozo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LEVEL_RESULT_LOST_UNITS")
public class LostUnitsLevelResult extends LevelResult<Integer> {

	private static final long serialVersionUID = 7658620888686769960L;

}
