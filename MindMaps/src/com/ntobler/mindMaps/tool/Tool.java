package com.ntobler.mindMaps.tool;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import com.ntobler.mindMaps.Workspace;

public interface Tool {

	public void pick(Point2D pos, MouseEvent e, Workspace w);
	public int getShortcutKey();
	public void abort();
	public String getToolString();
	
}
