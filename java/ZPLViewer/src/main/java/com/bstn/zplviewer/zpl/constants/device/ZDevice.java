package com.bstn.zplviewer.zpl.constants.device;

public class ZDevice {
	public static final Device R = new Device("R");
	public static final Device E = new Device("E"); 
	public static final Device B = new Device("B");
	public static final Device A = new Device("A");

	public static Device getDeviceByName(char name) {
		switch(name) {
			case 'R' -> {
				return R;
			}
			
			case 'E' -> {
				return E;
			}
			
			case 'B' -> {
				return B;
			}
			
			case 'A' -> {
				return A;
			}
			
			default -> {
				return null;
			}
		}
	}
}