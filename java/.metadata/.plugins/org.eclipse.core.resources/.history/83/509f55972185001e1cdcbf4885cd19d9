package renderables;

import com.bstn.zplviewer.zpl.constants.ZColor;
import com.bstn.zplviewer.zpl.constants.ZJustification;
import com.bstn.zplviewer.zpl.constants.ZOrientation;

public abstract class Barcode extends Renderable {
	private String data;
	private float moduleWidth;
	private float widthRatio;
	private boolean interpretationLineEnabled;
	private boolean interpretationLineAbove;
	
	public Barcode(float x, float y, float width, float height, ZColor color, ZOrientation orientation,
			ZJustification justification, String data, float moduleWidth, float widthRatio,
			boolean interpretationLineEnabled, boolean interpretationLineAbove) {
		super(x, y, width, height, color, orientation, justification);
		this.data = data;
		this.moduleWidth = moduleWidth;
		this.widthRatio = widthRatio;
		this.interpretationLineEnabled = interpretationLineEnabled;
		this.interpretationLineAbove = interpretationLineAbove;
	}

	public String getBarcodeStringRepresentation() {
		return getStringRepresentation() + "data=" + data + ", moduleWidth=" + moduleWidth + ", widthRatio=" + widthRatio
				+ ", interpretationLineEnabled=" + interpretationLineEnabled + ", interpretationLineAbove="
				+ interpretationLineAbove + ", ";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public float getModuleWidth() {
		return moduleWidth;
	}

	public void setModuleWidth(float moduleWidth) {
		this.moduleWidth = moduleWidth;
	}

	public float getWidthRatio() {
		return widthRatio;
	}

	public void setWidthRatio(float widthRatio) {
		this.widthRatio = widthRatio;
	}

	public boolean isInterpretationLineEnabled() {
		return interpretationLineEnabled;
	}

	public void setInterpretationLineEnabled(boolean interpretationLineEnabled) {
		this.interpretationLineEnabled = interpretationLineEnabled;
	}

	public boolean isInterpretationLineAbove() {
		return interpretationLineAbove;
	}

	public void setInterpretationLineAbove(boolean interpretationLineAbove) {
		this.interpretationLineAbove = interpretationLineAbove;
	}
	
}
