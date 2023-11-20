package com.bstn.zplviewer.util;


public class Logger {
	
	private static void message(Level level, String message) {		
		switch (level) {
			case ERROR -> {
				System.err.println("Error: " + message);
				System.exit(0);
			}
			case WARNING -> System.out.println("Warning: " + message);
			case INFO -> System.out.println("Info: " + message);
			
			default ->
				throw new IllegalArgumentException("Unexpected value: " + level);
		}
	}
	
	
	/**
	 * Log an error message.
	 *
	 * @param object  The object associated with the log message.
	 * @param message The error message to log.
	 */
	public static void error(String message) {
		message(Level.ERROR, message);
	}

	/**
	 * Log a warning message.
	 *
	 * @param object  The object associated with the log message.
	 * @param message The warning message to log.
	 */
	public static void warning(String message) {
		message(Level.WARNING, message);
	}
	
	
	/**
	 * Log an information message.
	 *
	 * @param object  The object associated with the log message.
	 * @param message The information message to log.
	 */
	public static void info(String message) {
		message(Level.INFO, message);
	}
}
