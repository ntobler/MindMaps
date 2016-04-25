package com.ntobler.mindMaps;


import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class RenderTransformer {

	private Dimension screenDimension;
	private Item focus;
	private int zoomTicks = 0;
	private double zoom = 1;
	
	public RenderTransformer () {
	}
	
	public AffineTransform getTransformation() {

		Complex t = getTransformVector();
		
		AffineTransform transform = new AffineTransform();
		
	    transform.translate(t.x, t.y);

		transform.scale(zoom, zoom);
		
		return transform;
	}
	
	public Complex getTransformVector () {
		
		Complex centerVector = new Complex (screenDimension.getWidth() / 2, screenDimension.getHeight() / 2);
	
		Complex transformVector;
		
		if (focus != null) {
			transformVector = centerVector.minus(focus.getPos().scalarMultiply(zoom));
		}
		else {
			transformVector = centerVector;
		}
		
		return transformVector;
	}
	
	public Complex getGamePos(Complex mousePos) {
		
		Complex gamePos;
		
		gamePos =  mousePos.minus(getTransformVector()).scalarDivide(zoom);

		return gamePos;
	}
	
	public Complex getScreenPos(Complex gamePos) {
		
		Complex screenPos;
		
		screenPos =  gamePos.scalarMultiply(zoom).plus(getTransformVector());
		
		return screenPos;
	}
	
	public double getZoom() {
		return zoom;
	}
	
	public Item getFocus() {
		return focus;
	}

	public void setFocus(Item focus) {
		this.focus = focus;
	}
	
	public void setScreenDimension(Dimension screenDimension) {
		this.screenDimension = screenDimension;
	}
	
	public void addZoomTicks(int ticks) {
		this.zoomTicks += ticks;
		zoom = Math.pow(10, ((double) zoomTicks) / 10);
	}
	
	public void setZoomTicks(int ticks) {
		this.zoomTicks = ticks;
		zoom = Math.pow(10, ((double) zoomTicks) / 10);
	}
	
	
	
}
