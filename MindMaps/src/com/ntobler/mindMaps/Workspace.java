package com.ntobler.mindMaps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Workspace implements Paintable {

	private List<Item> items;
	
	public static final Font NORMAL_FONT = new Font("Calibri", Font.PLAIN, 16);
	
	
	
	private RenderTransformer renderTransformer;
		
	public Workspace(RenderTransformer renderTransformer) {
		
		this.renderTransformer = renderTransformer;
		items = new ArrayList<Item>();
	}
	
	public void addItem(Item item) {
		if (item != null) {
			items.add(item);
		}
	}

	
	
	public List<Item> getItems() {
		return items;
	}

	public RenderTransformer getRenderTransformer() {
		return renderTransformer;
	}

	public Item getNearestItem(Complex pos) {
		
		double nearestDistance = Double.POSITIVE_INFINITY;
		Item nearestItem = null;
		
		for (Item i: items) {
			double distance = i.getPos().minus(pos).abs();
			if (distance < nearestDistance) {
				nearestDistance = distance;
				nearestItem = i;
			}
		}
		
		return nearestItem;
	}
	
	@Override
	public void paint(Graphics2D g2) {
		// TODO Auto-generated method stub
		
		g2.setPaint(Color.BLACK);
		
		AffineTransform normalTransform = g2.getTransform();
		
		AffineTransform gameTransform = renderTransformer.getTransformation();
    	g2.setTransform(gameTransform);
    	
    	for (Item i: items) {
			i.paintAbsolute(g2);
		}	

    	g2.setTransform(normalTransform);
			
	}
	
	

}
