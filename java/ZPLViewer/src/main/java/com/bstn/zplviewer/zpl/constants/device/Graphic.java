package com.bstn.zplviewer.zpl.constants.device;

public class Graphic {
	private String fileName;
	private String data;
	private int totalNumberOfBytes;
	private int numberOfBytesPerRow;
	
	public Graphic(String fileName, String data, int totalNumberOfBytes, int numberOfBytesPerRow) {
		this.fileName = fileName;
		this.data = data;
		this.totalNumberOfBytes = totalNumberOfBytes;
		this.numberOfBytesPerRow = numberOfBytesPerRow;
	}

	@Override
	public String toString() {
		return "Graphic [fileName=" + fileName + ", data=" + data + ", totalNumberOfBytes=" + totalNumberOfBytes
				+ ", numberOfBytesPerRow=" + numberOfBytesPerRow + "]";
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public int getTotalNumberOfBytes() {
		return totalNumberOfBytes;
	}
	
	public void setTotalNumberOfBytes(int totalNumberOfBytes) {
		this.totalNumberOfBytes = totalNumberOfBytes;
	}
	
	public int getNumberOfBytesPerRow() {
		return numberOfBytesPerRow;
	}
	
	public void setNumberOfBytesPerRow(int numberOfBytesPerRow) {
		this.numberOfBytesPerRow = numberOfBytesPerRow;
	}
}
