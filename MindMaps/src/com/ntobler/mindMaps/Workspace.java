package com.ntobler.mindMaps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	@Override
	public void paint(Graphics2D g2, DrawingLayer layer) {
		
    	for (Item i: items) {
			i.paintAbsolute(g2, layer);
		}	

	}
	
	public void save(File file) {
	
		try {
			FileOutputStream fileOut =
			new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
	 
			out.writeObject(items);

			out.close();
			fileOut.close();
			System.out.printf("Saved");
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public void open(File file) {
		
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			items = (ArrayList<Item>) in.readObject();

			in.close();
			fileIn.close();
		}
		catch(IOException i) {
			System.out.println("File not readable");
		}
		catch(ClassNotFoundException c) {
			System.out.println("File Corrupt");
			c.printStackTrace();
		}
		catch(ClassCastException cc) {
			System.out.println("File Corrupt");
		}
	}
	
	public Item getPickedItem(Point2D pick) {
		
		Rectangle2D bounds;
		
		for (Item i: items) {
			bounds = i.getBounds();
			if(bounds.contains(pick)) {
				return i;
			}
		}	
		return null;	
	}
	
}
