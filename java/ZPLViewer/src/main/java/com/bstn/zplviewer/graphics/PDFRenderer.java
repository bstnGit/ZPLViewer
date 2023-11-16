package com.bstn.zplviewer.graphics;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.bstn.zplviewer.graphics.barcode.BarcodeGenerator;
import com.bstn.zplviewer.zpl.constants.ZFont;
import com.bstn.zplviewer.zpl.constants.ZJustification;

import renderables.Code128Barcode;
import renderables.Graphic;
import renderables.Rectangle;
import renderables.Text;

public class PDFRenderer implements Renderer {
	
	private PDDocument pdDocument;
	private PDPageContentStream contentStream;
	private float originX, originY;
	
	public PDFRenderer() {
		init();
	}

	private void init() {
		this.pdDocument = new PDDocument();
		PDRectangle pdRectangle = null;

		PDPage pdPage = new PDPage(pdRectangle);

		this.pdDocument.addPage(pdPage);

		try {
			this.contentStream = new PDPageContentStream(pdDocument, pdPage, AppendMode.APPEND, false, false);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ZFont.ZERO.setFont(PDType0Font.load(pdDocument, new File("C:\\Users\\JerBa\\Desktop\\synka\\ZPLViewer\\java\\ZPLViewer\\src\\main\\resources\\font\\Swiss-721-Condensed-Bold.ttf")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		originY = pdPage.getMediaBox().getHeight();
	}
	
	/**
	 * Draws a string on the canvas with the specified parameters.
	 *
	 * @param text   The text to be drawn.
	 * @param x      The X-coordinate where the text should be drawn.
	 * @param y      The Y-coordinate where the text should be drawn.
	 * @param width  The width of the text.
	 * @param height The height of the text.
	 * @param color  The color of the text.
	 * @param font   The font used for rendering the text.
	 */
	@Override
	public void drawText(Text text) {		
		try {
            contentStream.setNonStrokingColor(text.getZColor().getColor());
            
	        int fontHeight = Math.round((text.getFont().getPDFont().getFontDescriptor().getCapHeight()) / 1000 * text.getHeight());
	        
	        int justificationX = 0;
	        int justificationY = 0;
	        
	        if(text.getJustification() == ZJustification.BOTTOM_LEFT) {
	        	justificationY += text.getHeight() - 3;
	        }
	        
	        contentStream.setFont(text.getFont().getPDFont(), text.getHeight());
	        contentStream.beginText();
	        
	        if(text.getWidth() == 0) {
	        	contentStream.setHorizontalScaling(100);
	        }else {
	        	contentStream.setHorizontalScaling((text.getWidth() / text.getHeight()) * 100);
	        }

	        contentStream.newLineAtOffset(originX + text.getX() + justificationX, originY - fontHeight - text.getY() + justificationY);
	        contentStream.showText(text.getData());
	        contentStream.endText();
	       	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}  

	/**
	 * Draws a rectangle on the canvas.
	 *
	 * @param x The x-coordinate of the top-left corner of the rectangle.
	 * @param y The y-coordinate of the top-left corner of the rectangle.
	 * @param width The width of the rectangle.
	 * @param height The height of the rectangle.
	 * @param borderThickness The thickness of the rectangle's border.
	 * @param color The color of the rectangle.
	 * @param degreeOfCornerRounding The degree of corner rounding for the rectangle accepted are values from 1 to 8.
	 */
	@Override
	public void drawRectangle(Rectangle rectangle) {
		try {
			contentStream.setStrokingColor(rectangle.getZColor().getColor());
			
	        if (rectangle.getBorderThickness() > rectangle.getWidth() / 2) {
	        	rectangle.setBorderThickness(rectangle.getWidth() / 2);
	        	
	        } else if (rectangle.getBorderThickness() > rectangle.getHeight() / 2) {
	        	rectangle.setBorderThickness(rectangle.getHeight() / 2);
	        }
	        
	        int justificationX = 0;
	        int justificationY = 0;
	        
	        if(rectangle.getJustification() == ZJustification.BOTTOM_LEFT) {
	        	justificationY += rectangle.getHeight();
	        }
	        
			float borderOffset = rectangle.getBorderThickness() / 2.0f;
	        float adjustedX = originX + rectangle.getX() + borderOffset + justificationX;
	        float adjustedY = originY - rectangle.getY() - rectangle.getHeight() + borderOffset + justificationY;
	        float adjustedWidth = rectangle.getWidth() - rectangle.getBorderThickness();
	        float adjustedHeight = rectangle.getHeight() - rectangle.getBorderThickness();
			
	        contentStream.setLineWidth(rectangle.getBorderThickness());
	        contentStream.addRect(adjustedX, adjustedY, adjustedWidth, adjustedHeight);
	        contentStream.stroke();
	        
	    } catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void drawGraphic(Graphic graphic) {
		try {
			PDImageXObject imageXObject = LosslessFactory.createFromImage(pdDocument, graphic.getBufferedImage());
			
			float adjustedX = originX + graphic.getX();
		    float adjustedY = originY - graphic.getY();

		    contentStream.drawImage(imageXObject, adjustedX, adjustedY, graphic.getWidth() * graphic.getScaleX(), graphic.getHeight() * graphic.getScaleY());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void drawCode128Barcode(Code128Barcode barcode) {
		BarcodeGenerator barcodeGenerator = new BarcodeGenerator();
		BufferedImage bufferedImage = barcodeGenerator.getBarCode128(barcode.getData(), (int) barcode.getWidth(), (int) barcode.getHeight(), true, barcode.getMode());
		
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            
			PDImageXObject barcodeImage = PDImageXObject.createFromByteArray(pdDocument, baos.toByteArray(), "Barcode");
			
		    int imageWidth = bufferedImage.getWidth();
		    int imageHeight = bufferedImage.getHeight();
			
			float adjustedX = originX + barcode.getX();
		    float adjustedY = originY - barcode.getY() - imageHeight;

			contentStream.drawImage(barcodeImage, adjustedX, adjustedY, imageWidth, imageHeight);
						
			PDFont font = ZFont.ZERO.getPDFont();
			int fontSize = 18;
			
            float textWidth = font.getStringWidth(barcode.getData()) * fontSize / 1000f;
			
			contentStream.setFont(font, fontSize);
		    contentStream.beginText();
		    contentStream.newLineAtOffset(adjustedX + imageWidth /2 - textWidth /2, adjustedY - fontSize /2 - 10);
		    contentStream.showText(barcode.getData());
		    contentStream.endText();
			contentStream.stroke();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public boolean save(File file) {
        try {
        	contentStream.close();
			pdDocument.save(file);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void closeStreamWriter() {
		try {
			contentStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void close() {
        try {
	        pdDocument.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PDDocument getPdDocument() {
		return pdDocument;
	}

	public void setPdDocument(PDDocument pdDocument) {
		this.pdDocument = pdDocument;
	}

}