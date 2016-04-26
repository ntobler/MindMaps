package com.ntobler.mindMaps.tool;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import com.ntobler.mindMaps.Entry;
import com.ntobler.mindMaps.Workspace;

public class CreateNewTool implements Tool {

	private Entry parent;
	
	@Override
	public void pick(Point2D pos, MouseEvent e, Workspace w) {

		if (e.isControlDown()) {
			parent = (Entry) w.getPickedItem(pos);
		}
		else {
		
			Entry entry = new Entry();
			entry.setPos(pos);
			entry.setParent(parent);
			
			new PropertiesWindow(entry, w);
		}
	}

	@Override
	public int getShortcutKey() {
		return KeyEvent.VK_N;
	}

	@Override
	public void abort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getToolString() {
		return "Add new: Click to add";
	}

}
