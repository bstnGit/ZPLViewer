package com.bstn.zplviewer.util;

import java.io.File;
import java.io.IOException;

public class PDFUtil {
	
    /**
     * Opens the specified PDF file using the default PDF viewer on the system.
     * The method determines the operating system and uses the appropriate command
     * to launch the viewer.
     *
     * @param pdfFile The PDF file to be opened.
     */
    public static void open(File pdfFile) {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                // Windows
                processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", pdfFile.getAbsolutePath());
            } else if (os.contains("mac")) {
                // macOS
                processBuilder = new ProcessBuilder("open", pdfFile.getAbsolutePath());
            } else {
                // Linux or other platforms
                processBuilder = new ProcessBuilder("xdg-open", pdfFile.getAbsolutePath());
            }

            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
