package com.bstn.zplviewer.graphics;

public enum DPI {
	DPI72(72),
	DPI96(96),
	DPI150(150),
	DPI203(203),
	DPI300(300),
	DPI600(600),
	DPI1200(1200);

	private final int value;
	
	DPI(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

}

