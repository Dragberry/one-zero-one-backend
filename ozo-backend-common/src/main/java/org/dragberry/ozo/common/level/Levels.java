package org.dragberry.ozo.common.level;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.dragberry.ozo.common.levelresult.LevelResultName;

public class Levels {

	public final static List<LevelConfig> LIST = new ArrayList<>();
	
	static {
		LIST.add(new LevelConfig("ozo.level.0", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		LIST.add(new LevelConfig("ozo.level.1", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		LIST.add(new LevelConfig("ozo.level.2", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		LIST.add(new LevelConfig("ozo.level.3", 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
	}
}
