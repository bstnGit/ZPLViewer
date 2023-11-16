package com.bstn.zplviewer.graphics;

import java.io.File;

import renderables.Code128Barcode;
import renderables.Graphic;
import renderables.Rectangle;
import renderables.Text;

public interface Renderer {
	void drawText(Text text);
	void drawRectangle(Rectangle rectangle);
	void drawCode128Barcode(Code128Barcode code128Barcode);
	void drawGraphic(Graphic graphic);
	boolean save(File file);
	
}
