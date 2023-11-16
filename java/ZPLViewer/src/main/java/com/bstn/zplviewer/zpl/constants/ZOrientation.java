package com.bstn.zplviewer.zpl.constants;

public enum ZOrientation {
	NORMAL('N'), ROTATE_90('R'), INVERTED('I'), READ_FROM_BOTTOM('B');

	private final char name;
	
	ZOrientation(char representation) {
		this.name = representation;
	}

	public char getName() {
		return name;
	}
	
	public static ZOrientation getRotationByName(char name) {
	    for (ZOrientation rotation : ZOrientation.values()) {
	        if (rotation.getName() == name) {
	            return rotation;
	        }
	    }
	    
		return null;
	}
}
