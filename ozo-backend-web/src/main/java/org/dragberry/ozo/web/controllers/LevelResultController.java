package org.dragberry.ozo.web.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dragberry.ozo.dao.UserDao;
import org.dragberry.ozo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LevelResultController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/result/level/all", method = RequestMethod.GET)
	@ResponseBody
	public String getResultsForAllLevels(HttpServletRequest request) {
		String userEmail = request.getParameter("userEmail");
		
		
		User user = userDao.findOne(1000L);
		
		return "User email: " + user.getEmail();
	}
}

class AllLevelResults {
	
	private String userEmail;
	
	private Map<String, SingleLevelResults> levelResults = new HashMap<>(); 
	
}

class SingleLevelResults {
	
	private String levelName;
	
	private Map<ResultType, LevelResult> results = new HashMap<>(); 

	
}

class LevelResult<T extends Serializable> {
	
	private T personal;
	
	private T world;
	
	private String worldUser;
}

enum ResultType {
	TIME, STEPS, LOST_UNITS
}

/*

{ 
	"results": {
		"userEmail": "max@gmail.com",
		"levelResults": [
			{
				"level": {
					"levelName": "ozo.level.0",
					"results": [
						{ "TIME": { "personal": "66.23", "world": "55.55", "worldUser": "record@gmail.com" }},
						{ "STEPS": { "personal": "54", "world": "36", "worldUser": "record@gmail.com" }},
						{ "LOST_UNITS": { "personal": "14", "world": "9", "worldUser": "record@gmail.com" }}
					]
				}
			},
			{
				"level": {
					"levelName": "ozo.level.1",
					"results": [
						{ "TIME": { "personal": "112.23", "world": "85.55", "worldUser": "record@gmail.com" }},
						{ "STEPS": { "personal": "110", "world": "88", "worldUser": "record@gmail.com" }},
						{ "LOST_UNITS": { "personal": "64", "world": "15", "worldUser": "record@gmail.com" }}
					]
				}
			}
		]
	}
}









*/

