package org.dragberry.ozo.common.app;

import org.dragberry.ozo.common.CommonConstants;

public class AppVersionChecker {
	
	public static final String APP_VERSION_VARIABLE = "appVersion";
	
	public static boolean check(String version) {
		return CommonConstants.APP_VERSION.equals(version);
	}

}
