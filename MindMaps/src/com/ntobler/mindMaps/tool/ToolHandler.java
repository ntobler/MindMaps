package com.ntobler.mindMaps.tool;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.ntobler.mindMaps.Workspace;

public class ToolHandler {

	private List<Tool> tools;
	private Tool activeTool;
	
	public ToolHandler() {
		
		tools = new ArrayList<Tool>();
		
		tools.add(new SelectTool());
		tools.add(new CreateNewTool());
		
		activeTool = tools.get(0);
		
	}
	
	public void keyPressed (KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			activeTool.abort();
			activeTool = tools.get(0);
		}
		
		
		for (Tool t: tools) {
			if (t.getShortcutKey() == e.getKeyCode()) {
				activeTool.abort();
				activeTool = t;
			}
		}
		
	}
	
	public void pick(Point2D pos, MouseEvent e, Workspace w) {
		activeTool.pick(pos, e, w);
	}
	
	
	
}
