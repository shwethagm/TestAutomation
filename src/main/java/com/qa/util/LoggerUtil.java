package com.qa.util;

import org.apache.log4j.Logger;

public class LoggerUtil {

	static Logger logger = Logger.getLogger("Logger");

	static public Logger getLogger() {
		return logger;
	}
}
