package com.bstn.zplviewer.renderables;

import com.bstn.zplviewer.graphics.PDFRenderer;
import com.bstn.zplviewer.zpl.constants.ZCode128Mode;
import com.bstn.zplviewer.zpl.constants.ZColor;
import com.bstn.zplviewer.zpl.constants.ZJustification;
import com.bstn.zplviewer.zpl.constants.ZOrientation;

public class Code128Barcode extends Barcode {
	private boolean uccCheckDigitEnabled;
	private ZCode128Mode mode;
	
	public Code128Barcode(float x, float y, float width, float height, ZColor color, ZOrientation orientation,
			ZJustification justification, String data, float moduleWidth, float widthRatio,
			boolean interpretationLineEnabled, boolean interpretationLineAbove, boolean uccCheckDigitEnabled,
			ZCode128Mode mode) {
		super(x, y, width, height, color, orientation, justification, data, moduleWidth, widthRatio,
				interpretationLineEnabled, interpretationLineAbove);
		this.uccCheckDigitEnabled = uccCheckDigitEnabled;
		this.mode = mode;
	}

	@Override
	public void render(PDFRenderer renderer) {
		renderer.drawCode128Barcode(this);
	}

	@Override
	public String toString() {
		return "Code128Barcode [" + getBarcodeStringRepresentation() + "uccCheckDigitEnabled=" + uccCheckDigitEnabled + ", mode=" + mode + "]";
	}

	public boolean isUccCheckDigitEnabled() {
		return uccCheckDigitEnabled;
	}

	public void setUccCheckDigitEnabled(boolean uccCheckDigitEnabled) {
		this.uccCheckDigitEnabled = uccCheckDigitEnabled;
	}

	public ZCode128Mode getMode() {
		return mode;
	}

	public void setMode(ZCode128Mode mode) {
		this.mode = mode;
	}
}
