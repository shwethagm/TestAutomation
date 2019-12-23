package com.qa.util;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class JsonParserUtil {
	Logger log = LoggerUtil.getLogger();

	public void ConvertJsonToType() {
		Gson gson = new Gson();
		UserDetails[] users = null;
		try {
			users = gson.fromJson(new FileReader("\\resources\\users.json"), UserDetails[].class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(users);
	}
}

class Address {
	String line1;
	int pin;
}

class UserDetails {
	int id;
	String user;
	String login;
	Address a;
}
