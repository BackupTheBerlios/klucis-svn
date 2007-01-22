package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.VelocityView;

/**
 * This component displays a rectangular area and fills it with appropriate
 * content (eventually boxes will be stacked or connected with arrows as in a
 * diagram)
 * 
 * @author kap
 */
public class SquareBox extends AbstractComponent {

	private boolean drawBoundingBox = true;

	private boolean fillBoundingBox = false;

	// This default value should be read from the default model
	private float size = 200.0F;

	// This default value should be read from the default model
	private float rotate = 0.0F;

	// Probably the parent should pass offsetX=x and offsetY=y for
	// transpose(x,y)
	// this "num" is not very reasonable.
	private int numX;

	private int numY;

	private float offsetX;

	private float offsetY;

	private Component content;

	// If no default, then null is default and nothing is painted
	private String label;

	public void setSize(int size) {
		this.size = size;
	}

	public void setContent(Component content) {
		this.content = content;
		// TODO: Copy the size down, so it can rotate

		// if (content instanceof Transform) {
		// ((Transform)content).setSize(size);
		// }
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setNumX(int numX) {
		this.numX = numX;
		offsetX = (size + 20) * numX + 100;
	}

	public void setNumY(int numY) {
		this.numY = numY;
		offsetY = (size + 20) * numY + 100;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}

	public void setDrawBoundingBox(boolean drawBoundingBox) {
		this.drawBoundingBox = drawBoundingBox;
	}

	public void setFillBoundingBox(boolean fillBoundingBox) {
		this.fillBoundingBox = fillBoundingBox;
	}

	public String render() {
		VelocityView view = new VelocityView();
		view.setTemplateName("SquareBox1");

		// TODO: this should be accessible from the template as
		// $_component.getSize() etc.
		this.addObject("_offsetX", offsetX);
		this.addObject("_offsetY", offsetY);
		this.addObject("_drawBoundingBox", drawBoundingBox);
		this.addObject("_fillBoundingBox", fillBoundingBox);

		this.addObject("_numX", numX);
		this.addObject("_numY", numY);
		this.addObject("_size", size);
		this.addObject("_label", label);
		this.addObject("_content", content);

		if (rotate != 0) {
			// TODO: find out about the sign of '-'
			this.addObject("_rotate", -rotate);
		}
		view.setContextParams(model);
		return view.render();
	}

	public void setRotate(int rotation) {
		this.rotate = rotation;
	}
}
