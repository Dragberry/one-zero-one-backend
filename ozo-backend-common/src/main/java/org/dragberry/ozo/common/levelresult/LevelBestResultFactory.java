package org.dragberry.ozo.common.levelresult;

import java.text.MessageFormat;

public class LevelBestResultFactory {
	
	private static final String UNKNOWN_RESULT_TYPE_MSG = "Unknown result type: {0}!";

	private LevelBestResultFactory() {}

	public static LevelBestResult create(BestResultType type) {
		switch(type) {
			case LOST_UNITS:
				
				break;
			case STEPS:
				
				break;
			case TIME:
				
				break;
			default:
				break;
		}
		throw new IllegalArgumentException(MessageFormat.format(UNKNOWN_RESULT_TYPE_MSG, type));
	}
}
