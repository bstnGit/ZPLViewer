package renderables;

import com.bstn.zplviewer.graphics.Renderer;
import com.bstn.zplviewer.zpl.constants.ZColor;
import com.bstn.zplviewer.zpl.constants.ZJustification;
import com.bstn.zplviewer.zpl.constants.ZOrientation;

public class Rectangle extends Renderable {
	private float borderThickness;
	private float degreeOfCornerRounding;
	
	public Rectangle(float x, float y, float width, float height, ZColor color, ZOrientation orientation, ZJustification justification, float borderThickness, float degreeOfCornerRounding) {
		super(x, y, width, height, color, orientation, justification);
		this.borderThickness = borderThickness;
		this.degreeOfCornerRounding = degreeOfCornerRounding;
	}

	@Override
	public void render(Renderer renderer) {
		renderer.drawRectangle(this);
	}
	
	@Override
	public String toString() {
		return "Rectangle [" + getStringRepresentation() + "borderThickness=" + borderThickness + ", degreeOfCornerRounding=" + degreeOfCornerRounding
				+ "]";
	}

	public float getBorderThickness() {
		return borderThickness;
	}

	public void setBorderThickness(float borderThickness) {
		this.borderThickness = borderThickness;
	}

	public float getDegreeOfCornerRounding() {
		return degreeOfCornerRounding;
	}

	public void setDegreeOfCornerRounding(float degreeOfCornerRounding) {
		this.degreeOfCornerRounding = degreeOfCornerRounding;
	}
}
