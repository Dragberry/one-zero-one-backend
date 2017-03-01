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
		map.put("ozo.level.000.lvl", new LevelConfig("ozo.level.000.lvl", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put("ozo.level.001.lvl", new LevelConfig("ozo.level.001.lvl", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put("ozo.level.002.lvl", new LevelConfig("ozo.level.001.lvl", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put("ozo.level.003.lvl", new LevelConfig("ozo.level.003.lvl", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		LIST = Collections.unmodifiableMap(map);
	}
}
