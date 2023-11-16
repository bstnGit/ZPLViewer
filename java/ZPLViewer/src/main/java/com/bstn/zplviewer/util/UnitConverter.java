package com.bstn.zplviewer.util;

import com.bstn.zplviewer.graphics.DPI;

public class UnitConverter {
	
	private DPI dpi;
	
	public UnitConverter(DPI dpi) {
		this.dpi = dpi;
	}
	
	public float inchesToPDFPoints(float inches) {
		return inches * 72;
	}
	
	public float dotsToPDFPoints(float dots) {
		float points = dots / (dpi.getValue() / 72);
	    return  Math.round(points);
	}
}
