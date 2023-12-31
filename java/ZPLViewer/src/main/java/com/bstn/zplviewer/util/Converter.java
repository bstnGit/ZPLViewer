package com.bstn.zplviewer.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bstn.zplviewer.zpl.command.Command;
import com.bstn.zplviewer.zpl.constants.ZColor;

/**
 * This class provides utility methods for converting and handling parameters.
 */
public class Converter {
	
	public static final String parameterDefault = "default";
	
	 /**
     * Converts a parameter string to a list of strings.
     *
     * @param parameter     	The parameter string.
     * @param expectedSize  	The expected size of the resulting list.
     * @param delimeterRegex	The pattern used to spit the parameter string.
     * @return A list of strings generated from the parameter.
     */
	public static List<String> parametersStringToList(String parameter, int expectedSize, String delimeterRegex) {
		parameter = parameter.replaceAll("\\s","");
		List<String> parameters = new ArrayList<>(Arrays.asList(parameter.split(delimeterRegex)));

		for (int i = 0; i < expectedSize; i++) {

			if(i >= parameters.size()) {
				parameters.add(i, parameterDefault);
			}else if(parameters.get(i).isEmpty()) {
				parameters.set(i, parameterDefault);
			}
		}
		
		return parameters;
	}
	
	/**
	 * Converts a string parameter to a boolean value based on certain conditions.
	 *
	 * @param command The command for which this conversion is performed.
	 * @param parameterName The name of the parameter being checked.
	 * @param parameterValue The value of the parameter to convert.
	 * @param defaultValue The default boolean value to return if conditions are not met.
	 * @return The boolean value corresponding to the parameterValue or the defaultValue if conditions are not met.
	 */
	public static boolean parametersToBoolean(Command command, String parameterName, String parameterValue, boolean defaultValue) {
		if(parameterValue == parameterDefault) {
			return defaultValue;
		}
		
		parameterValue = parameterValue.toLowerCase();
		
		if (parameterValue.length() != 1) {
	        Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be a single character.");
	        return defaultValue;
	    }
		
		if(parameterValue.equals("y")) {
			return true;
		}
		
		if(parameterValue.equals("n")) {
			return false;
		}
		
	    Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be an accepted character 'Y/N'.");
		
		return defaultValue;
	}
	
	/**
	 * Converts a parameter value to a character based on accepted values or a default value.
	 *
	 * @param command The command associated with the parameter.
	 * @param parameterName The name of the parameter.
	 * @param acceptedValues An array of accepted characters.
	 * @param parameterValue The value to be converted to a character.
	 * @param defaultValue The default character to use if the conversion is not successful.
	 * @return The converted character or the default character if the conversion is not successful.
	 */
	public static char parametersToCharacter(Command command, String parameterName, char[] acceptedValues, String parameterValue, char defaultValue) {
		if(parameterValue == parameterDefault) {
			return defaultValue;
		}
		
		parameterValue = parameterValue.toUpperCase();
		
		for (int i = 0; i < acceptedValues.length; i++) {
		    acceptedValues[i] = Character.toUpperCase(acceptedValues[i]);
		}
		
		if (parameterValue.length() != 1) {
	        Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be a single character; used " + defaultValue + " instead.");
	        return defaultValue;
	    }
		
		
		char parameter = parameterValue.charAt(0);

		for (int i = 0; i < acceptedValues.length; i++) {
			if(acceptedValues[i] == parameter) {
				return parameter;
			}
		}
		
	    Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be an accepted character: " + Arrays.asList(acceptedValues).toString() + ".");
	    return defaultValue;
	}
	
	/**
	 * Validates if a given value is in the accepted range (A to Z and 0 to 9).
	 *
	 * @param command          The command associated with the value.
	 * @param parameterName    The name of the parameter.
	 * @param value            The value to validate.
	 * @return True if the value is in the accepted range, false otherwise.
	 */
	public static char parametersToFontName(Command command, String parameterName, String parameterValue, char defaultValue) {
		if(parameterValue == parameterDefault) {
			return defaultValue;
		}
		
	    if (parameterValue == null || parameterValue.length() != 1) {
	        Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be a single character; used " + defaultValue + " instead.");
			return defaultValue;
	    }

	    char character = parameterValue.charAt(0);
	    if (Character.isLetter(character) && Character.isUpperCase(character) || Character.isDigit(character)) {
	    	return character;
	    } else {
	        Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be in the accepted range (A to Z and 0 to 9).");
			return defaultValue;
	    }
	}
	
	/**
	 * Converts a string parameter to an integer value based on certain conditions.
	 *
	 * @param command The command for which this conversion is performed.
	 * @param parameterName The name of the parameter being checked.
	 * @param parameterValue The value of the parameter to convert.
	 * @param defaultValue The default integer value to return if conditions are not met.
	 * @return The integer value corresponding to the parameterValue or the defaultValue if conditions are not met.
	 */
	public static int parameterToInt(Command command, String parameterName, String parameterValue, int defaultValue) {
		if(parameterValue == parameterDefault) {
			return defaultValue;
		}
		
		if(Validator.validateInteger(parameterValue)) {
			return Integer.parseInt(parameterValue);
		}else {
			Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be numeric.");
		}
		
		return defaultValue;
	}
	
	/**
     * Converts a parameter to an integer within a specified range.
     *
     * @param command          The command associated with the parameter.
     * @param parameterName    The name of the parameter.
     * @param parameterValue   The value of the parameter.
     * @param min              The minimum allowed value.
     * @param max              The maximum allowed value.
     * @param defaultValue     The default value to use if the parameter is invalid or equal to parameterDefault.
     * @return An integer within the specified range.
     */
	public static int parameterToIntWithRange(Command command, String parameterName, String parameterValue, int min, int max, int defaultValue) {
		if(parameterValue == parameterDefault) {
			return defaultValue;
		}
		
		if(Validator.validateInteger(parameterValue)) {
			int result = Integer.parseInt(parameterValue);
			
			if(result < min) {
				Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be above or equal to " + min + "; used " + min + " instead");
				return min;
			}
			
			if (result > max) {
				Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be below or equal to " + max + "; used " + max + " instead");
				return max;
			}
			
			return result;
		}else {
			Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be numeric.");
		}
		
		return defaultValue;
	}
	
	/**
     * Converts a parameter to an double within a specified range.
     *
     * @param command          The command associated with the parameter.
     * @param parameterName    The name of the parameter.
     * @param parameterValue   The value of the parameter.
     * @param min              The minimum allowed value.
     * @param max              The maximum allowed value.
     * @param defaultValue     The default value to use if the parameter is invalid or equal to parameterDefault.
     * @return An integer within the specified range.
     */
	public static float parameterToFloatWithRange(Command command, String parameterName, String parameterValue, float min, float max, int decimals, float defaultValue) {
		if(parameterValue == parameterDefault) {
			return defaultValue;
		}

		if(Validator.validateDouble(parameterValue)) {
			float result = Float.parseFloat(parameterValue);

			if(result < min) {
				Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be above or equal to " + min + "; used " + min + " instead");
				return min;
			}
			
			
			if (result > max) {
				Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be below or equal to " + max + "; used " + max + " instead");
				return max;
			}
			
			if(BigDecimal.valueOf(result).scale() > decimals +1) {
				float roundedResult = (float) (Math.round(result * 10.0) / 10.0);
				
				Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be below or equal to " + decimals + " decimals; used " + roundedResult + " instead");
				return roundedResult;
			}
			
			return result;
		}else {
			Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be numeric.");
		}
		
		return 0;
	}

	 /**
     * Converts a parameter to a string with a specified byte limit.
     *
     * @param command         The ZPL command associated with the parameter.
     * @param parameterName   The name of the parameter.
     * @param byteLimit       The maximum byte limit for the resulting string,
     * 							when passing -1 there is no byte limit.
     * @param parameterValue  The value of the parameter to be converted.
     * @return The converted string with a byte limit.
     */
	public static String parameterToString(Command command, String parameterName, int byteLimit, String parameterValue, String defaultValue) {
		if(defaultValue != null) {	
			if(parameterValue == parameterDefault) {
				return defaultValue;
			}
		}
		
		String result = new String(parameterValue.replaceAll("^\\s+|\\s+$", ""));
		
        byte[] stringBytes = result.getBytes();
		
        if (stringBytes.length <= byteLimit || byteLimit == -1) {
        	return result;
        } else {
        	Logger.warning(getPrefix(command, parameterName, result.length() > 20 ? result.substring(0, 20) + " [...]" : result) + " is too long; was trimmed to " + byteLimit + " bytes."); 
        	return new String(Arrays.copyOfRange(stringBytes, 0, byteLimit));
        }
  	}
	
	 /**
     * Converts a parameter to a color constant.
     *
     * @param command        The ZPL command associated with the parameter.
     * @param parameterName  The name of the parameter.
     * @param parameterValue The value of the parameter.
     * @param defaultColor   The default color to use if the parameter is equal to parameterDefault.
     * @return A Color constant.
     */
	public static ZColor parameterToColor(Command command, String parameterName, String parameterValue, ZColor defaultColor) {
		if(parameterValue == parameterDefault) {
			return defaultColor;
		}

		if(parameterValue.charAt(0) == 'B') {
			return ZColor.BLACK;
		}else if(parameterValue.charAt(0) == 'W') {
			return ZColor.WHITE;
		}else {
			Logger.warning(getPrefix(command, parameterName, parameterValue) + " should be either B for black or W for white.");
		}
		
		
		return null;
	}
	
	private static String getPrefix(Command command, String parameterName, String parameterValue) {
		return command.getLongName() + " '" + command.getShortName() + "' in line " + command.getLineNumber() + " at index " + command.getStartIndex() + " - " + parameterName + " with value '" + parameterValue + "'";
	}
}