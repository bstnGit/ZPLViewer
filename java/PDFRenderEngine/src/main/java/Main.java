import java.awt.Color;
import java.io.File;

public class Main {
	public static void main(String[] args) {
		PDFRenderEngine engine = new PDFRenderEngine(500, 500);
		
		engine.setLineWidth(1);
		engine.setStrokingColor(Color.black);
		
		engine.drawRoundedRectangle(10, 100, 350, 250, 100);
		engine.drawLine(100, 500, 100, 100);

		engine.save(new File("output.pdf"));
		System.out.println("Saved");
	}
}
