package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.util.Logger;
import com.bstn.zplviewer.zpl.ZPL;

public class GraphicField extends Command {
	
	/*
	 * Accepted Values:
	 * A = ASCII hexadecimal (follows the format for other
	 *     download commands)
	 * B = binary (data sent after the c parameter is strictly binary)
	 * C = compressed binary (data sent after the c parameter is in
	 *     compressed binary format. The data is compressed on
	 *     the host side using Zebra’s compression algorithm. The
	 *     data is then decompressed and placed directly into the
	 *     bitmap.)
	 *     
	 * Default Value: A
	 */
	private char compressionType;
	
	/*
	 * Accepted Values: 1 to 99999
	 * This is the total number of bytes to be transmitted for the total
	 * image or the total number of bytes that follow parameter d.
	 * For ASCII download, the parameter should match parameter
	 * c. Out-of-range values are set to the nearest limit.
	 * 
	 * Default Value: command is ignored if a value is not specified
	 */
	private int binaryByteCount;
	
	/* 
	 * Accepted Values: 1 to 99999
	 * This is the total number of bytes comprising the graphic
	 * format (width x height), which is sent as parameter d. Count
	 * divided by bytes per row gives the number of lines in the
	 * image. This number represents the size of the image, not
	 * necessarily the size of the data stream (see d)
	 * Default Value: command is ignored if a value is not specified
	 */
	private int graphicFieldCount;
	
	/*
	 * Accepted Values: 1 to 99999
	 * This is the number of bytes in the downloaded data that
	 * comprise one row of the image.
	 * 
	 * Default Value: command is ignored if a value is not specified
	 */
	private int bytesPerRow;
	
	/*
	 * Accepted Values:
	 * ASCII hexadecimal data: 00 to FF
	 * A string of ASCII hexadecimal numbers, two digits per image
	 * byte. CR and LF can be inserted as needed for readability. The
	 * number of two-digit number pairs must match the above
	 * count. Any numbers sent after count is satisfied are ignored.
	 * A comma in the data pads the current line with 00 (white
	 * space), minimizing the data sent. ~DN or any caret or tilde
	 * character prematurely aborts the download.
	 * Binary data: Strictly binary data is sent from the host. All
	 * control prefixes are ignored until the total number of bytes
	 * needed for the graphic format is sent.
	 */
	private String data;

	@Override
	public void execute(InterpreterEnvironment env) {
		if(binaryByteCount != -1 && graphicFieldCount != -1 && bytesPerRow != -1 && data != "") {
			env.drawGraphic(data, bytesPerRow, 1, 1);
		}else {
			Logger.warning("Graphic Field '^GF' - ignored because a value is not specified");
		}
	}

	@Override
	public void parse(String parameters) {
		List<String> parameterList = Converter.parametersStringToList(parameters, 5, String.valueOf(ZPL.delimiter));

		compressionType = Converter.parametersToCharacter(this, "Compression type", new char[] {'A', 'B', 'C'}, parameterList.get(0), 'A');
		binaryByteCount = Converter.parameterToIntWithRange(this, "binary byte count", parameterList.get(1), 1, 99999, -1);
		graphicFieldCount = Converter.parameterToIntWithRange(this, "graphic field count", parameterList.get(2), 1, 99999, -1);
		bytesPerRow = Converter.parameterToIntWithRange(this, "bytes per row", parameterList.get(3), 1, 99999, -1);
		data = Converter.parameterToString(this, "defining image data", -1, parameterList.get(4), "");
	}

	@Override
	public String getShortName() {
		return "^GF";
	}

	@Override
	public String getLongName() {
		return "Graphic Field";
	}

	@Override
	public Layer getLayer() {
		return Layer.RENDERING;
	}

	@Override
	public String toString() {
		return "GraphicField [compressionType=" + compressionType + ", binaryByteCount=" + binaryByteCount
				+ ", graphicFieldCount=" + graphicFieldCount + ", bytesPerRow=" + bytesPerRow + ", data=" + data + "]";
	}

	public char getCompressionType() {
		return compressionType;
	}

	public void setCompressionType(char compressionType) {
		this.compressionType = compressionType;
	}

	public int getBinaryByteCount() {
		return binaryByteCount;
	}

	public void setBinaryByteCount(int binaryByteCount) {
		this.binaryByteCount = binaryByteCount;
	}

	public int getBytesPerRow() {
		return bytesPerRow;
	}

	public void setBytesPerRow(int bytesPerRow) {
		this.bytesPerRow = bytesPerRow;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getGraphicFieldCount() {
		return graphicFieldCount;
	}

	public void setGraphicFieldCount(int graphicFieldCount) {
		this.graphicFieldCount = graphicFieldCount;
	}
}
