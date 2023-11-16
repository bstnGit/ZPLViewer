package com.bstn.zplviewer.zpl.constants;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.bstn.zplviewer.util.Logger;
import com.bstn.zplviewer.zpl.command.Command;

public enum ZFont {
    ZERO(0, PDType1Font.HELVETICA),
    ONE(1, PDType1Font.HELVETICA),
    A(2, PDType1Font.COURIER);

    private final int value;
    private PDFont font;

    ZFont(int value, PDFont font) {
        this.value = value;
        this.font = font;
    }

    public int getValue() {
        return value;
    }

    public PDFont getPDFont() {
        return font;
    }

	public void setFont(PDFont font) {
		this.font = font;
	}
	
	public static ZFont getByCharacter(Command command, char character) {
        switch (character) {
            case '0':
                return ZERO;
            case '1':
                return ONE;
            case 'A':
                return A;
            default:
                Logger.warning(command.getLongName() + " '" + command.getShortName() + "' in line " + command.getLineNumber() + " at index " + command.getStartIndex() + " - couldn't find font: " + character + "; used font '0' instead.");
                return ZERO;
        }
    }
}