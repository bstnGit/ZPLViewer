package com.bstn.zplviewer.interpreter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.bstn.zplviewer.graphics.ImageConverter;
import com.bstn.zplviewer.graphics.RenderQueue;
import com.bstn.zplviewer.util.UnitConverter;
import com.bstn.zplviewer.zpl.constants.ZBarcodeFormat;
import com.bstn.zplviewer.zpl.constants.ZCode128Mode;
import com.bstn.zplviewer.zpl.constants.ZColor;
import com.bstn.zplviewer.zpl.constants.ZFont;
import com.bstn.zplviewer.zpl.constants.ZJustification;
import com.bstn.zplviewer.zpl.constants.ZOrientation;

import renderables.Code128Barcode;
import renderables.Graphic;
import renderables.Rectangle;
import renderables.Renderable;
import renderables.Text;

public class InterpreterEnvironment {
	
	private RenderQueue renderQueue;
	private UnitConverter unitConverter;
	
	//Label
	private boolean reversePrintAllFields;
	
	// Field
	private int fieldX;
	private int fieldY;
	private ZJustification justification;
	private boolean fieldReversed;
	private ZOrientation orientation;
	
	// Font
	private ZFont font;
	private ZColor fontColor;
	private int fontWidth;
	private int fontHeight;

	// Bar code
	private ZBarcodeFormat barcodeFormat;
	private int barcodeModuleWidth;
	private float barcodeWidthRatio;
	private int tempBarcodeHeight;
	private int barcodeHeight;
	private boolean barcodeUccCheckDigitEnabled;
	private ZCode128Mode code128Mode;

	private ZOrientation tempOrientation;
	private boolean barcodePrintInterpretationLine;
	private boolean barcodePrintInterpretationLineAboveCode;

	public InterpreterEnvironment(UnitConverter unitConverter) {
		this.unitConverter = unitConverter;
		
		renderQueue = new RenderQueue();
		
		font = ZFont.ZERO;
		fontColor = ZColor.BLACK;
		fontWidth = 0;
		fontHeight = 9;
		barcodeModuleWidth = 2;
	}

	public void drawFieldData(String data) {
		if (barcodeFormat == null) {
			drawString(data);
		} else {
			drawBarcode(data);
		}
	}

	public void drawString(String data) {
		float x = unitConverter.dotsToPDFPoints(fieldX);
		float y = unitConverter.dotsToPDFPoints(fieldY);
		float width = unitConverter.dotsToPDFPoints(fontWidth);
		float height = unitConverter.dotsToPDFPoints(fontHeight);

		renderQueue.addElement(new Text(x, y, width, height, fontColor, orientation, justification, data, font));
	}

	public void drawRectangle(float width, float height, float borderThickness, ZColor lineColor, float degreeOfCornerRounding) {
		float x = unitConverter.dotsToPDFPoints(fieldX);
		float y = unitConverter.dotsToPDFPoints(fieldY);
		width = unitConverter.dotsToPDFPoints(width);
		height = unitConverter.dotsToPDFPoints(height);
		borderThickness = unitConverter.dotsToPDFPoints(borderThickness);
		
		renderQueue.addElement(new Rectangle(x, y, width, height, lineColor, orientation, justification, borderThickness, degreeOfCornerRounding));

	}
	
	public void drawGraphic(String data, int bytesPerRow, int scaleX, int scaleY) {
		BufferedImage bufferedImage = new ImageConverter().dataToBufferedImage(data, bytesPerRow);
		
		float x = unitConverter.dotsToPDFPoints(fieldX);
		float y = unitConverter.dotsToPDFPoints(fieldY);
		float width = unitConverter.dotsToPDFPoints(bufferedImage.getWidth());
		float height = unitConverter.dotsToPDFPoints(bufferedImage.getHeight());
		
		renderQueue.addElement(new Graphic(x, y, width, height, ZColor.BLACK, tempOrientation, justification, data, bytesPerRow, scaleX, scaleY, bufferedImage));
	}

	public void drawBarcode(String data) {
		float x = unitConverter.dotsToPDFPoints(fieldX);
		float y = unitConverter.dotsToPDFPoints(fieldY);
		float width = unitConverter.dotsToPDFPoints(data.length() * 11 * barcodeModuleWidth);
		float height = unitConverter.dotsToPDFPoints((tempBarcodeHeight == 0) ? barcodeHeight : tempBarcodeHeight);
		
		renderQueue.addElement(new Code128Barcode(x, y, width, height, ZColor.BLACK, orientation, justification, data, barcodeModuleWidth, barcodeWidthRatio, barcodePrintInterpretationLine, barcodePrintInterpretationLineAboveCode, false, code128Mode));
	}

	public void reset() {
		fieldX = 0;
		fieldY = 0;
		fieldReversed = false;
		barcodeFormat = null;
		tempBarcodeHeight = 0;
		justification = null;
		tempOrientation = null;
	}

	public RenderQueue getRenderQueue() {
		return renderQueue;
	}

	public void setRenderQueue(RenderQueue renderQueue) {
		this.renderQueue = renderQueue;
	}

	public int getFieldY() {
		return fieldY;
	}

	public void setFieldY(int fieldY) {
		this.fieldY = fieldY;
	}

	public int getFieldX() {
		return fieldX;
	}

	public void setFieldX(int fieldX) {
		this.fieldX = fieldX;
	}

	public ZJustification getJustification() {
		return justification;
	}

	public void setJustification(ZJustification justification) {
		this.justification = justification;
	}

	public ZFont getFont() {
		return font;
	}

	public void setFont(ZFont font) {
		this.font = font;
	}

	public void setFontHeight(int fontHeight) {
		this.fontHeight = fontHeight;
	}

	public boolean isFieldReversed() {
		return fieldReversed;
	}

	public void setFieldReversed(boolean fieldReversed) {
		this.fieldReversed = fieldReversed;
	}

	public int getFontWidth() {
		return fontWidth;
	}

	public void setFontWidth(int fontWidth) {
		this.fontWidth = fontWidth;
	}

	public int getFontHeight() {
		return fontHeight;
	}

	public void specifiedDefaultFont(int fontHeight) {
		this.fontHeight = fontHeight;
	}

	public ZBarcodeFormat getBarCodeFormat() {
		return barcodeFormat;
	}

	public ZBarcodeFormat getBarcodeFormat() {
		return barcodeFormat;
	}

	public void setBarcodeFormat(ZBarcodeFormat barcodeFormat) {
		this.barcodeFormat = barcodeFormat;
	}

	public int getBarcodeModuleWidth() {
		return barcodeModuleWidth;
	}

	public void setBarcodeModuleWidth(int barcodeModuleWidth) {
		this.barcodeModuleWidth = barcodeModuleWidth;
	}

	public double getBarcodeWidthRatio() {
		return barcodeWidthRatio;
	}

	public void setBarcodeWidthRatio(float barcodeWidthRatio) {
		this.barcodeWidthRatio = barcodeWidthRatio;
	}

	public int getBarcodeHeight() {
		return barcodeHeight;
	}

	public int getTempBarcodeHeight() {
		return tempBarcodeHeight;
	}

	public void setTempBarcodeHeight(int tempBarcodeHeight) {
		this.tempBarcodeHeight = tempBarcodeHeight;
	}

	public void setBarcodeHeight(int barcodeHeight) {
		this.barcodeHeight = barcodeHeight;
	}

	public ZOrientation getTempOrientation() {
		return tempOrientation;
	}

	public void setTempOrientation(ZOrientation tempOrientation) {
		this.tempOrientation = tempOrientation;
	}

	public boolean isBarcodePrintInterpretationLine() {
		return barcodePrintInterpretationLine;
	}

	public void setBarcodePrintInterpretationLine(boolean barcodePrintInterpretationLine) {
		this.barcodePrintInterpretationLine = barcodePrintInterpretationLine;
	}

	public boolean isBarcodePrintInterpretationLineAboveCode() {
		return barcodePrintInterpretationLineAboveCode;
	}

	public void setBarcodePrintInterpretationLineAboveCode(boolean barcodePrintInterpretationLineAboveCode) {
		this.barcodePrintInterpretationLineAboveCode = barcodePrintInterpretationLineAboveCode;
	}

	public boolean isBarcodeUccCheckDigitEnabled() {
		return barcodeUccCheckDigitEnabled;
	}

	public void setBarcodeUccCheckDigitEnabled(boolean barcodeUccCheckDigitEnabled) {
		this.barcodeUccCheckDigitEnabled = barcodeUccCheckDigitEnabled;
	}

	public ZCode128Mode getCode128Mode() {
		return code128Mode;
	}

	public void setCode128Mode(ZCode128Mode code128Mode) {
		this.code128Mode = code128Mode;
	}

	public ZOrientation getOrientation() {
		return orientation;
	}

	public void setOrientation(ZOrientation orientation) {
		this.orientation = orientation;
	}

	public boolean isReversePrintAllFieldsEnabled() {
		return reversePrintAllFields;
	}

	public void setReversePrintAllFields(boolean reversePrintAllFields) {
		this.reversePrintAllFields = reversePrintAllFields;
		 		
		if(reversePrintAllFields) {
			fontColor = ZColor.WHITE;
		}else {
			fontColor = ZColor.BLACK;
		}
		
	}
	
}
