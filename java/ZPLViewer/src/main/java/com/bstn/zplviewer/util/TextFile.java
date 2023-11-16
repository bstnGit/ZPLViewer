package com.bstn.zplviewer.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Jerome Bastien
 */
public class TextFile {

	private File file;

	/**
	 * Constructs a TextFile instance with the given file path.
	 *
	 * @param filePath The path to the text file.
	 */
	public TextFile(File file) {
		this.file = file;
	}

	/**
	 * Read the contents of the text file and return them as a String.
	 *
	 * @return The contents of the text file as a String.
	 */
	public String read() {
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	/**
	 * Write the given text to the text file, overwriting its previous contents.
	 *
	 * @param text The text to write to the file.
	 */
	public void write(String text) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Append the given text to the end of the text file.
	 *
	 * @param text The text to append to the file.
	 */
	public void append(String text) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return file;
	}
}