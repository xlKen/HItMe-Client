package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;

/* Input */

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiHitMeClick extends GuiScreen {
	
	private int dragOffSetX = 0;
	private int dragOffSetY = 0;
	private boolean dragging = false;
	public static boolean guit = false;
	public static boolean guic = false;
	private boolean Keys[];
	private CustomFont newArialFont;
	public int White = 0xffffff;
	public int Green = 0x0ff00;
	public int Red = 0xff0000;
	
	
	
	public GuiHitMeClick(Minecraft mc) {
		
		Keys = new boolean [256];
		newArialFont = null;
	}
	
	public boolean KeyCheck(int i) {
    	
    	if (mc.currentScreen != null) {
    		
    		return false;
    		
    	}
    	
    	if (Keyboard.isKeyDown(i) != Keys[i]) {
    		
    		return Keys[i] = !Keys[i];
    		
    	} else {
    		
    		return false;
    	}
    }
	
	public void drawScreen(int i, int j, float f) {
		
		drawDefaultBackground();
		if (newArialFont == null)
    		newArialFont = new CustomFont(mc, "Arial", 10);
		/* Reads Screen as the top box in the left corner */	
		drawBorderedRect(2, 2, 102, 14, 1, 0xff5c5c5c, 0xff3c3c3c);  /* Top Section to Draw font Renderer */
		newArialFont.drawString(this, "Main Hacks", 4, 4, 0xFFFFFF);
		/*Draws the box under the Screen Box */
		drawBorderedRect(2, 15, 102, 70, 1, 0xFF5c5c5c, 0xFF3c3c3c);
		newArialFont.drawString(this, "Dungeons", 4, 17, White);
		newArialFont.drawString(this, "Tracers", 4, 27, White);
		newArialFont.drawString(this, "Xray ", 4, 37, White);
		newArialFont.drawString(this, "ChestESP ", 4, 47, White);
		newArialFont.drawString(this, "Kill Aura ", 4, 57, White);
		
		if (HitMe.Dungeon_Master) {
			newArialFont.drawString(this, "On", 82, 18, Green);
		} else {
			newArialFont.drawString(this, "Off", 82, 18, Red);
		}
		
		if (HitMe.Tracers) {
			newArialFont.drawString(this, "On", 82, 28, Green);
		} else {
			newArialFont.drawString(this, "Off", 82, 28, Red);
		}
		
		if (HitMe.Xray) {
			newArialFont.drawString(this, "On", 82, 38, Green);
		} else {
			newArialFont.drawString(this, "Off", 82, 38, Red);
		}
		
		if (HitMe.ChestESP) {
			newArialFont.drawString(this, "On", 82, 48, Green);
		} else {
			newArialFont.drawString(this, "Off", 82, 48, Red);
		}
		
		if (HitMe.Kill_Aura) {
			newArialFont.drawString(this, "On", 82, 59, Green);
		} else {
			newArialFont.drawString(this, "Off", 82, 59, Red);
		}
 		
	}
		
	
	
	protected void mouseClicked(int i, int j, int k) {
		/*Screen Box*/
		
		if (i>80 && i<100 && j>17 && j<28) {
			HitMe.Dungeon_Master = !HitMe.Dungeon_Master;
		}
		
		if (i>80 && i<100 && j>27 && j<38) {
			HitMe.Tracers = !HitMe.Tracers;
		}
		
		if (i>80 && i<100 && j>38 && j<49) {
			HitMe.Xray = !HitMe.Xray;
			mc.renderGlobal.loadRenderers();
		}
		
		if (i>80 && i<100 && j>49 && j<59) {
			HitMe.ChestESP = !HitMe.ChestESP;
		}
		
		if (i>80 && i<100 && j>59 && j<69) {
			HitMe.Kill_Aura = !HitMe.Kill_Aura;
		}
 	}
	
	public void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC) {
		
		drawRect(x, y, x1, y1, borderC);
		drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
	}
	


}
