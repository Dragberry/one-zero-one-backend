package org.dragberry.ozo.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LEVEL_RESULT_TIME")
@NamedQueries({
	@NamedQuery(
			name = TimeLevelResult.GET_RESULTS_FOR_ALL_LEVELS, 
			query = "SELECT MIN(r.resultValue) FROM TimeLevelResult r GROUP BY r.level")
	})
public class TimeLevelResult extends LevelResult<Float> {

	private static final long serialVersionUID = 7658620888686769960L;
	
	public static final String GET_RESULTS_FOR_ALL_LEVELS = "TimeLevelResult.getForAllLevels";
	
}
