package org.dragberry.ozo.common.app;

import org.dragberry.ozo.common.CommonConstants;

public class AppVersionChecker {
	
	private static final String DOT = ".";
	public static final String APP_VERSION_VARIABLE = "appVersion";
	
	public static boolean check(String version) {
		if (version != null) {
			int lastPoint = version.lastIndexOf(DOT);
			if (lastPoint != -1) {
				return CommonConstants.APP_VERSION.substring(0, version.lastIndexOf(DOT))
						.equals(version.substring(0, lastPoint));
			}
		}
		return false;
	}
	
}
