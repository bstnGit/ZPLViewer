import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
import org.apache.pdfbox.util.Matrix;

public class PDFRenderEngine {

	private PDDocument pdDocument;
	private int canvasWidth, canvasHeight;
	private PDPageContentStream contentStream;
	private float originX, originY;
	private int lineWidth;
	private Color strokingColor;

	public PDFRenderEngine() {
		init();
	}

	public PDFRenderEngine(int width, int height) {
		this.canvasWidth = width;
		this.canvasHeight = height;

		init();
	}

	private void init() {
		this.pdDocument = new PDDocument();
		PDRectangle pdRectangle = null;

		if (canvasWidth != 0 && canvasHeight != 0) {
			pdRectangle = new PDRectangle(canvasWidth, canvasHeight);
		} else {
			pdRectangle = PDRectangle.A4;
		}

		PDPage pdPage = new PDPage(pdRectangle);


		this.pdDocument.addPage(pdPage);

		try {
			this.contentStream = new PDPageContentStream(pdDocument, pdPage, AppendMode.APPEND, false, false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		originY = pdPage.getMediaBox().getHeight();
	}

	public void drawRoundedRectangle(int x, int y, int width, int height, int degreeOfCornerRounding, boolean inverted) {
		try {
			if (lineWidth > width / 2) {
				lineWidth = width / 2;
			} else if (lineWidth > height / 2) {
				lineWidth = height / 2;
			}
			
			if(inverted) {
				setBlendeMode(1);
			}
			
			float borderOffset = lineWidth / 2.0f;
			float adjustedX = originX + x + borderOffset;
			float adjustedY = originY - y - height + borderOffset;
			float adjustedWidth = width - lineWidth;
			float adjustedHeight = height - lineWidth;

			contentStream.addRect(adjustedX, adjustedY, adjustedWidth, adjustedHeight);
			contentStream.stroke();
			setBlendeMode(0);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawRoundedRectangle(int x, int y, int width, int height, int degreeOfCornerRounding) {
	    int halfLineWidth = lineWidth / 2;
	    
	    // Adjust the coordinates to account for line width
	    x += halfLineWidth;
	    y += halfLineWidth;
	    width -= lineWidth -1;
	    height -= lineWidth -1;
	    
		int fillGap = 1;
		
		// Line Point A to Point B
		drawLine(x + degreeOfCornerRounding, y, x + (width - degreeOfCornerRounding), y);

		// Curve top right - Point B
		drawCurve(x + (width - degreeOfCornerRounding) - fillGap, y, x + width, y, x + width, y + degreeOfCornerRounding + fillGap);
		
		// Curve top left - Point A
		drawCurve(x, y + degreeOfCornerRounding + fillGap, x, y, x + degreeOfCornerRounding + fillGap, y);

		// Line Point A to Point B
		drawLine(x + degreeOfCornerRounding, y, x + (width - degreeOfCornerRounding), y);

		// Curve top right - Point B
		drawCurve(x + (width - degreeOfCornerRounding) - fillGap, y, x + width, y, x + width, y + degreeOfCornerRounding + fillGap);

		// Line Point B to Point C
		drawLine(x + width, y + degreeOfCornerRounding, x + width, y + (height - degreeOfCornerRounding));

		// Curve bottom right - Point C
		drawCurve(x + width, y + (height - degreeOfCornerRounding) - fillGap , x + width, y + height, x + (width - degreeOfCornerRounding) - fillGap, y + height);

		// Line Point C to Point D
		drawLine(x + (width - degreeOfCornerRounding), y + height, x + degreeOfCornerRounding, y + height);

		// Curve bottom left - Point D
		drawCurve(x, y + (height - degreeOfCornerRounding) - fillGap , x, y + height, x + degreeOfCornerRounding + fillGap, y + height);

		// Line Point D to Point A
		drawLine(x, y + (height - degreeOfCornerRounding), x, y + degreeOfCornerRounding);
		
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		try {
			float adjustedX1 = originX + x1;
			float adjustedY1 = originY - y1;
			float adjustedX2 = originX + x2;
			float adjustedY2 = originY - y2;

			contentStream.moveTo(adjustedX1, adjustedY1);
			contentStream.lineTo(adjustedX2, adjustedY2);
			contentStream.stroke();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawCurve(int x1, int y1, int x2, int y2, int x3, int y3) {
		try {
			float adjustedX1 = originX + x1;
			float adjustedY1 = originY - y1;

			float adjustedX2 = originX + x2;
			float adjustedY2 = originY - y2;

			float adjustedX3 = originX + x3;
			float adjustedY3 = originY - y3;

			contentStream.moveTo(adjustedX1, adjustedY1);
			contentStream.curveTo(adjustedX1, adjustedY1, adjustedX2, adjustedY2, adjustedX3, adjustedY3);
			contentStream.stroke();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setBlendeMode(int n) {
		try {
			PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
			gs.setNonStrokingAlphaConstant(1f);
			gs.setStrokingAlphaConstant(1f);
			if(n == 1) {
				gs.setBlendMode(BlendMode.DIFFERENCE);
			}else {
				gs.setBlendMode(BlendMode.NORMAL);
			}
			gs.setLineWidth(3f);

			contentStream.setGraphicsStateParameters(gs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save(File file) {
		try {
			contentStream.close();

			pdDocument.save(file);
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
	
	private static void addWatermarkText(PDDocument doc, PDPage page, PDFont font, String text) throws IOException {
	    PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true);

	    float fontHeight = 100;
	    float width = page.getMediaBox().getWidth();
	    float height = page.getMediaBox().getHeight();

	    int rotation = page.getRotation();
	    switch (rotation) {
	        case 90:
	            width = page.getMediaBox().getHeight();
	            height = page.getMediaBox().getWidth();
	            cs.transform(Matrix.getRotateInstance(Math.toRadians(90), height, 0));
	            break;
	        case 180:
	            cs.transform(Matrix.getRotateInstance(Math.toRadians(180), width, height));
	            break;
	        case 270:
	            width = page.getMediaBox().getHeight();
	            height = page.getMediaBox().getWidth();
	            cs.transform(Matrix.getRotateInstance(Math.toRadians(270), 0, width));
	            break;
	        default:
	            break;
	    }

	    float stringWidth = font.getStringWidth(text) / 1000 * fontHeight;
	    float diagonalLength = (float) Math.sqrt(width * width + height * height);
	    float angle = (float) Math.atan2(height, width);
	    float x = (diagonalLength - stringWidth) / 2;
	    float y = -fontHeight / 4;
	    cs.transform(Matrix.getRotateInstance(angle, 0, 0));
	    cs.setFont(font, fontHeight);
	    cs.setRenderingMode(RenderingMode.STROKE);
	    
	    PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
	    gs.setNonStrokingAlphaConstant(0.2f);
	    gs.setStrokingAlphaConstant(0.2f);
	    gs.setBlendMode(BlendMode.DIFFERENCE);
	    gs.setLineWidth(3f);
	    cs.setGraphicsStateParameters(gs);

	    cs.setNonStrokingColor(Color.red);
	    cs.setStrokingColor(Color.red);

	    cs.beginText();
	    cs.newLineAtOffset(x, y);
	    cs.showText(text);
	    cs.endText();
	    cs.close();
	}


	public int getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;

		try {
			contentStream.setLineWidth(lineWidth);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Color getStrokingColor() {
		return strokingColor;
	}

	public void setStrokingColor(Color strokingColor) {
		this.strokingColor = strokingColor;

		try {
			contentStream.setStrokingColor(strokingColor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}