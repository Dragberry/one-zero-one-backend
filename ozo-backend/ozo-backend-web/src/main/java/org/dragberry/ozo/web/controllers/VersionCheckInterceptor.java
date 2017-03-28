package org.dragberry.ozo.web.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dragberry.ozo.common.app.AppVersionChecker;
import org.dragberry.ozo.web.exceptions.WrongAppVersionException;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class VersionCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		@SuppressWarnings("rawtypes")
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String appVersion = (String) pathVariables.get(AppVersionChecker.APP_VERSION_VARIABLE);
		if (AppVersionChecker.check(appVersion)) {
			return true;
		}
		throw new WrongAppVersionException();
	}
}
