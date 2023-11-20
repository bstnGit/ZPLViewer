package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.zpl.ZPL;
import com.bstn.zplviewer.zpl.constants.ZBarcodeFormat;
import com.bstn.zplviewer.zpl.constants.ZCode128Mode;
import com.bstn.zplviewer.zpl.constants.ZOrientation;

public class Code128Barcode extends Command {
	
	/*
	 * Accepted Values:
	 * N = normal
	 * R = rotated 90 degrees (clockwise)
	 * I = inverted 180 degrees
	 * B = read from bottom up, 270 degrees
	 * Default Value: current ^FW value
	 */
	private ZOrientation orientation;
	
	/*
	 * Accepted Values: 1 to 32000
     * Default Value: value set by ^BY
	 */
	private int barcodeHeight;
	
	/*
	 * Accepted Values: Y (yes) or N (no)
	 * Default Value: Y
	 * The interpretation line can be printed in any font by placing
	 * the font command before the bar code command.
	 */
	private boolean printInterpretationLine;
	
	/*
	 * Accepted Values: Y (yes) or N (no)
     * Default Value: N
	 */
	private boolean printInterpretationLineAboveCode;
	
	/*
	 * Accepted Values: Y (turns on) or N (turns off)
	 * Mod 103 check digit is always there. It cannot be turned on or off. Mod 10
	 * and 103 appear together with e turned on.
	 * Default Value: N
	 */
	private boolean uccCheckDigit;
	
	/*
	 * Accepted Values:
	 * 		N = no selected mode
	 * 
	 * 		U = UCC Case Mode
	 * 			• More than 19 digits in ^FD or ^SN are eliminated.
	 * 			• Fewer than 19 digits in ^FD or ^SN add zeros to the right to bring the
	 * 			  count to 19. This produces an invalid interpretation line.
	 * 
	 * 		A = Automatic Mode
	 * 			This analyzes the data sent and automatically determines the best
	 * 			packing method. The full ASCII character set can be used in the ^FD
	 * 			statement — the printer determines when to shift subsets. A string of
	 * 			four or more numeric digits causes an automatic shift to Subset C.
	 * 
	 * 		D = UCC/EAN Mode
	 * 			This allows dealing with UCC/EAN with and without chained
	 * 			application identifiers. The code starts in the appropriate subset
	 * 			followed by FNC1 to indicate a UCC/EAN 128 bar code. The printer
	 * 			automatically strips out parentheses and spaces for encoding, but
	 * 			prints them in the human-readable section. The printer automatically
	 * 			determines if a check digit is required, calculate it, and print it.
	 * 			Automatically sizes the human readable.
	 * 
	 * Default Value: N
	 */
	private ZCode128Mode mode;

	public Code128Barcode() { /* --- */}

	@Override
	public void execute(InterpreterEnvironment env) {
		env.setBarcodeFormat(ZBarcodeFormat.BARCODE128);
		env.setTempOrientation(orientation);
		env.setBarcodeHeight(barcodeHeight);
		env.setBarcodePrintInterpretationLine(printInterpretationLine);
		env.setBarcodePrintInterpretationLineAboveCode(printInterpretationLineAboveCode);
		env.setCode128Mode(mode);
		
	}

	@Override
	public void parse(String parameters) {
		List<String> parameterList = Converter.parametersStringToList(parameters, 6, String.valueOf(ZPL.delimiter));
		
		this.orientation = ZOrientation.getRotationByName(Converter.parametersToCharacter(this, "orientation", new char[] {'N', 'R', 'I', 'B'}, parameterList.get(0), 'N'));
		this.barcodeHeight = Converter.parameterToIntWithRange(this, "bar code height", parameterList.get(1), 0, 32000, 5);
		this.printInterpretationLine = Converter.parametersToBoolean(this, "print interpretation line", parameterList.get(2), true);
		this.printInterpretationLineAboveCode = Converter.parametersToBoolean(this, "print interpretation line above code", parameterList.get(3), false);
		this.uccCheckDigit = Converter.parametersToBoolean(this, "print interpretation line above code", parameterList.get(4), false);
		this.mode = ZCode128Mode.getRotationByName(Converter.parametersToCharacter(this, "code 128 mode", new char[] {'N', 'U', 'A', 'D'}, parameterList.get(5), 'N'));
	}
	
	@Override
	public String getShortName() {
		return "^BC";
	}

	@Override
	public String getLongName() {
		return "Code 128 Barcode";
	}

	@Override
	public Layer getLayer() {
		return Layer.PROPERTIES;
	}

	@Override
	public String toString() {
		return "Code128BarCode [orientation=" + orientation + ", barCodeHeight=" + barcodeHeight
				+ ", printInterpretationLine=" + printInterpretationLine + ", printInterpretationLineAboveCode="
				+ printInterpretationLineAboveCode + "]";
	}

	public ZOrientation getOrientation() {
		return orientation;
	}

	public void setOrientation(ZOrientation orientation) {
		this.orientation = orientation;
	}

	public int getBarCodeHeight() {
		return barcodeHeight;
	}

	public void setBarCodeHeight(int barCodeHeight) {
		this.barcodeHeight = barCodeHeight;
	}

	public boolean isPrintInterpretationLine() {
		return printInterpretationLine;
	}

	public void setPrintInterpretationLine(boolean printInterpretationLine) {
		this.printInterpretationLine = printInterpretationLine;
	}

	public boolean isPrintInterpretationLineAboveCode() {
		return printInterpretationLineAboveCode;
	}

	public void setPrintInterpretationLineAboveCode(boolean printInterpretationLineAboveCode) {
		this.printInterpretationLineAboveCode = printInterpretationLineAboveCode;
	}

	public int getBarcodeHeight() {
		return barcodeHeight;
	}

	public void setBarcodeHeight(int barcodeHeight) {
		this.barcodeHeight = barcodeHeight;
	}

	public boolean isUccCheckDigit() {
		return uccCheckDigit;
	}

	public void setUccCheckDigit(boolean uccCheckDigit) {
		this.uccCheckDigit = uccCheckDigit;
	}

	public ZCode128Mode getMode() {
		return mode;
	}

	public void setMode(ZCode128Mode mode) {
		this.mode = mode;
	}
}
