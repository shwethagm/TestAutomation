package com.qa.util;

import java.io.FileReader;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonParserUtil {
	private Logger log = LoggerUtil.getLogger();
	private JsonParser parser = new JsonParser();
	private String userDir = System.getProperty("user.dir");

	public JsonArray getDataFromJson(String key) {
		JsonArray jsonArray = null;
		try {
			JsonElement jsonData = parser.parse(new FileReader(userDir + "\\resources\\users.json"));
			jsonArray = jsonData.getAsJsonObject().getAsJsonArray(key);
		} catch (Exception e) {
			log.error("Json parse failed " + e.getMessage());
			log.error("Json parse failed " + e.toString());
		}
		return jsonArray;
	}

//	public <T> T getDataFromJsonType(String key, T mytype) {
//		JsonElement jsonData = parser.parse(new FileReader(userDir + "\\resources\\users.json"));
//		JsonArray jsonArray = jsonData.getAsJsonObject().getAsJsonArray(key);
//
//		T[] user = new Gson().fromJson(jsonArray, T[].class);
//
//		return mytype;
//
//	}
}
