package com.bstn.zplviewer.util;

/**
 * @author Jerome Bastien
 */
public enum Level {
	ERROR(1), WARNING(2), INFO(3);
	
	private int value;
	

    Level(int value) {
        this.value = value;
    }

    /**
     * Get the numeric value associated with the log level.
     *
     * @return The numeric value of the log level.
     */
    public int getValue() {
        return value;
    }

    /**
     * Get the maximum numeric value among all log levels.
     *
     * @return The maximum numeric value of log levels.
     */
    public static int getMax() {
        int max = 0;

        Level[] levels = Level.values();
        for (int i = 0; i < levels.length; i++) {
            if (max < levels[i].getValue()) max = levels[i].getValue();
        }

        return max;
    }
}
