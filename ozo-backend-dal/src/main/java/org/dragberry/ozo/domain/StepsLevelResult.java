package org.dragberry.ozo.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LEVEL_RESULT_STEPS")
@NamedQueries({
	@NamedQuery(
			name = StepsLevelResult.GET_RESULTS_FOR_LEVEL, 
			query = "SELECT MIN(r.resultValue) FROM StepsLevelResult r GROUP BY r.level")
	})
public class StepsLevelResult extends LevelResult<Integer> {

	private static final long serialVersionUID = 7658620888686769960L;
	
	public static final String GET_RESULTS_FOR_LEVEL = "StepsLevelResult.getForAllLevels";

}
