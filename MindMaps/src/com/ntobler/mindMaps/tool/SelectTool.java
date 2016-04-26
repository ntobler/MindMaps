package com.ntobler.mindMaps.tool;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import com.ntobler.mindMaps.Entry;
import com.ntobler.mindMaps.Item;
import com.ntobler.mindMaps.Workspace;

public class SelectTool implements Tool {

	private Entry selectedEntry;
	
	private Entry parent;
	
	private boolean draging;
	
	@Override
	public void pick(Point2D pos, MouseEvent e, Workspace w) {

		if (e.getClickCount() == 2) {
			
			selectedEntry = (Entry) w.getPickedItem(pos);
			new PropertiesWindow(selectedEntry, w);
		}
		
		if (e.getClickCount() == 1) {
			
			if (draging) {
				draging = false;
				if (selectedEntry != null) {
					selectedEntry.setPos(pos);
				}
			}
			else {
				selectedEntry = (Entry) w.getPickedItem(pos);
				if (selectedEntry != null) {
					draging = true;
				}
			}
		}
		
		if (e.isControlDown()) {
			parent = (Entry) w.getPickedItem(pos);
		}

	}

	@Override
	public int getShortcutKey() {
		return KeyEvent.VK_SPACE;
	}

	@Override
	public void abort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getToolString() {
		
		String toolString = "Select: ";
		
		if (draging) {
			toolString += "Moving.. Click to place";
		}
		else {
			toolString += "Click to move, double click to edit";
		}
		return toolString;
	}

}
