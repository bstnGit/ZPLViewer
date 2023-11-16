package com.bstn.zplviewer.util;

public class Validator {
	
	/**
     * Validates whether a given string can be parsed into an integer.
     *
     * @param input The string to be validated.
     * @return true if the input can be parsed as an integer, false otherwise.
     */
    public static boolean validateInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validates whether a given string can be parsed into a double.
     *
     * @param input The string to be validated.
     * @return true if the input can be parsed as a double, false otherwise.
     */
	public static boolean validateDouble(String input) {
		try {
	        Double.parseDouble(input);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	/**
	 * Validates whether a given string can be parsed into a float.
	 *
	 * @param input The string to be validated.
	 * @return true if the input can be parsed as a float, false otherwise.
	 */
	public static boolean validateFloat(String input) {
	    try {
	        Float.parseFloat(input);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
}
