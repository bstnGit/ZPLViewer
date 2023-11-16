package com.bstn.zplviewer.zpl.constants;

public enum ZCode128Mode {
	NO_SELECTED_MODE('N'), UCC_CASE_MODE('U'), AUTOMATIC_MODE('A'), UCC_EAN_MODE('D');
	
	private final char name;
	
	ZCode128Mode(char name) {
		this.name = name;
	}

	public char getName() {
		return name;
	}

	public static ZCode128Mode getRotationByName(char name) {
	    for (ZCode128Mode rotation : ZCode128Mode.values()) {
	        if (rotation.getName() == name) {
	            return rotation;
	        }
	    }
	    
		return null;
	}
}
