package com.qa.util;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParserUtil {
	Logger log = LoggerUtil.getLogger();

	public String getJsonValue(String jsonStr, String key) {
		String val = "";
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);
			val = (String) jsonObject.get(key);
		} catch (ParseException e) {
			log.info("error json parser");
		}
		return val;
	}
}
