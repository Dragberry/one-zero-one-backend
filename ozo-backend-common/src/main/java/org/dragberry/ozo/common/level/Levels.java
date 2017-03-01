package org.dragberry.ozo.common.level;

import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.dragberry.ozo.common.levelresult.LevelResultName;

public class Levels {

	public final static Map<String, LevelConfig> LIST;
	
	static {
		Map<String, LevelConfig> map = new LinkedHashMap<>();
		map.put("ozo.level.0", new LevelConfig("ozo.level.0", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put("ozo.level.1", new LevelConfig("ozo.level.1", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put("ozo.level.2", new LevelConfig("ozo.level.2", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put("ozo.level.3", new LevelConfig("ozo.level.3", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		LIST = Collections.unmodifiableMap(map);
	}
}
