package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class GraphCanvas extends Canvas{

	public void paint(Graphics g){
		int width = getWidth();
		int height = getHeight();
		drawGrid(g,width+2,2,width,height);
	}
	
	private void drawGrid(Graphics g,int sX,int sY,int w,int h){
		int eX = sX+(2*w);
		int eY = sY+(2*h);
		boolean c = true;
		for(int y=sY;y!=eY;y=y+h) {
			for(int x=sX;x!=eX;x=x+w) {	
				c = !c;
				if(c) {
					g.setColor(Color.BLACK);
				} else {
					g.setColor(Color.WHITE);
				}
				g.fillRect(x,y,w,h);
			}
			c = !c;
		}
	}
}
