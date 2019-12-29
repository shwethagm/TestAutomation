package com.qa.util;

import org.apache.log4j.Logger;

public class LoggerUtil {

	static Logger logger = null;

	private LoggerUtil() {

	}

	public static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger("Logger");

		return logger;
	}
}
