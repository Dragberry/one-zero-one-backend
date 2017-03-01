package org.dragberry.ozo.common.level;

import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.dragberry.ozo.common.levelresult.LevelResultName;

public class Levels {

	public final static Map<String, LevelConfig> LIST;
	
	public final static String L001_LETS_START 			= "ozo.001.letsStart.lvl";
	public final static String L002_LITTLE_BIT_HARDER 	= "ozo.002.littleBitHarder.lvl";
	public final static String L003_NEED_MORE 			= "ozo.003.needMore.lvl";
	public final static String L004_DOUBLE_5 			= "ozo.004.double5.lvl";
	public final static String L005_MUSHROOM_RAIN		= "ozo.005.mushroomRain.lvl";
	public final static String L006_SAVE_US				= "ozo.006.saveUs.lvl";
	public final static String L007_ROULETTE 			= "ozo.007.roulette.lvl";
	public final static String L008_QUEUES				= "ozo.008.queues.lvl";
	public final static String L009_STRAIGHT_FLASH		= "ozo.009.straightFlush.lvl";
	public final static String L010_CHESSBOARD			= "ozo.010.chessboard.lvl";
	
	public final static String L011_MUSHROOM_SHOWER		= "ozo.011.mushroomShower.lvl";
	public final static String L012_WAVES				= "ozo.012.waves.lvl";
	public final static String L013_CASINO_ROYALE		= "ozo.013.casinoRoyale.lvl";
	public final static String L014_REGULARITY			= "ozo.014.regularity.lvl";
	public final static String L015_UNSAFE_PLACE		= "ozo.015.unsafePlace.lvl";
	public final static String L016_UNSAFE_REGULARITY	= "ozo.016.unsafeRegularity.lvl";
	public final static String L017_STORM				= "ozo.017.storm.lvl";
	public final static String L018_TSUNAMI				= "ozo.018.tsunami.lvl";
	
	static {
		Map<String, LevelConfig> map = new LinkedHashMap<>();
		map.put(L001_LETS_START, new LevelConfig(L001_LETS_START, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L002_LITTLE_BIT_HARDER, new LevelConfig(L002_LITTLE_BIT_HARDER, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L003_NEED_MORE, new LevelConfig(L003_NEED_MORE, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L004_DOUBLE_5, new LevelConfig(L004_DOUBLE_5, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L005_MUSHROOM_RAIN, new LevelConfig(L005_MUSHROOM_RAIN, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L006_SAVE_US, new LevelConfig(L006_SAVE_US, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L007_ROULETTE, new LevelConfig(L007_ROULETTE, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L008_QUEUES, new LevelConfig(L008_QUEUES, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L009_STRAIGHT_FLASH, new LevelConfig(L009_STRAIGHT_FLASH, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L010_CHESSBOARD, new LevelConfig(L010_CHESSBOARD, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		
		map.put(L011_MUSHROOM_SHOWER, new LevelConfig(L011_MUSHROOM_SHOWER, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L012_WAVES, new LevelConfig(L012_WAVES, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L013_CASINO_ROYALE, new LevelConfig(L013_CASINO_ROYALE, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L014_REGULARITY, new LevelConfig(L014_REGULARITY, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L015_UNSAFE_PLACE, new LevelConfig(L015_UNSAFE_PLACE, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L016_UNSAFE_REGULARITY, new LevelConfig(L016_UNSAFE_REGULARITY, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L017_STORM, new LevelConfig(L017_STORM, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		map.put(L018_TSUNAMI, new LevelConfig(L018_TSUNAMI, 
				EnumSet.of(LevelResultName.TIME, LevelResultName.STEPS, LevelResultName.LOST_UNITS)));
		
		LIST = Collections.unmodifiableMap(map);
	}
}
