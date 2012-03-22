package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class GuiConsole extends GuiScreen // My simple console no need to explain what it does because its directly from GuiChat.java
{
    /** The chat message. */
    protected String message;

    /** Counts the number of screen updates. Used to make the caret flash. */
    private int updateCounter;
    
    public static double xPosition;
    public static double yPosition;
    public static double zPosition;
    
    public GuiConsole()
    {
        message = "";
        updateCounter = 0;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        updateCounter++;
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1)
        {
            mc.displayGuiScreen(null);
            return;
        }

        if (par2 == 28)
        {
            String s = message.trim();

            if (s.length() > 0)
            {
                String s1 = message.trim();
            }
            
            if (s.equalsIgnoreCase("test")) {
              	 mc.thePlayer.addChatMessage("\2479[Console] \2472This is merely a test");
               }
               
               if (s.equalsIgnoreCase("Name")) {
              	 mc.thePlayer.addChatMessage("\2479[Console] \2472Username: " + mc.thePlayer.username);
               }
               
               if (s.equalsIgnoreCase("pos")) {
              	 mc.thePlayer.addChatMessage("\2479[Console] \2472POS X: " + mc.thePlayer.chunkCoordX);
              	 mc.thePlayer.addChatMessage("\2479[Console] \2472POS Z: " + mc.thePlayer.chunkCoordZ);
               }
               
               if (s.equalsIgnoreCase("seed")) {
              	 mc.thePlayer.addChatMessage("\2479[Console] \2472Seed: " + mc.theWorld.getSeed());
               }
            
              if (s.equalsIgnoreCase("Nofall on") && HitMe.No_fall == false) {
            	  HitMe.No_fall = true;
            	  mc.thePlayer.addChatMessage("\2479[Console] \2472No fall on"); 
              } 
              
              if (s.equalsIgnoreCase("Nofall off") && HitMe.No_fall == true) {
            	  HitMe.No_fall = false;
            	  mc.thePlayer.addChatMessage("\2479[Console] \2472No fall off");
              }
              
              if (s.equalsIgnoreCase("NoPush On") || s.equalsIgnoreCase("nopush on")) {
            	  
            	  HitMe.No_Push = !HitMe.No_Push;
            	  
            	  if (HitMe.No_Push) {
            		  
            		  mc.thePlayer.addChatMessage("\2479[Console] \2472No Push On");
            		  
            	  } else
            		  
            		  if (!HitMe.No_Push) {
            			  
            			  mc.thePlayer.addChatMessage("\2479[Console] \2472No Push Off");
            		  }
              }
              
              if (s.equalsIgnoreCase("help")) {
            	  // mc.thePlayer.addChatMessage("\2479[Console] \2472 Test - merely a test.");
            	  mc.thePlayer.addChatMessage("\2479[Console] \2472 Name - Display your current username.");
            	  mc.thePlayer.addChatMessage("\2479[Console] \2472 Pos - Show your current X & Z Positions.");
            	  mc.thePlayer.addChatMessage("\2479[Console] \2472 Seed - Display the level's seed.");
            	  mc.thePlayer.addChatMessage("\2479[Console] \2472 Nofall [on/off] - Toggle No Fall.");
            	  mc.thePlayer.addChatMessage("\247a[Console] \2472 Nofall [on/off] - Toggle No Push.");
              }
              
              if (s.equalsIgnoreCase("keys")) {
            	  mc.thePlayer.addChatMessage("\2471K- Kill Aura");
            	  mc.thePlayer.addChatMessage("\2472L- Logger");
            	  mc.thePlayer.addChatMessage("\2473LCONTROL- Sprint");
            	  mc.thePlayer.addChatMessage("\2474F9- Click GUI");
            	  mc.thePlayer.addChatMessage("\2475P- Tracers");
            	  mc.thePlayer.addChatMessage("\2476C- ChestESP");
            	  mc.thePlayer.addChatMessage("\2477X- Xray");
            	  mc.thePlayer.addChatMessage("\2478M- Dungeon Master");
            	  mc.thePlayer.addChatMessage("\2478A- No Push");
              }
              
            mc.displayGuiScreen(null);
            return;
        }

        if (par2 == 14 && message.length() > 0)
        {
            message = message.substring(0, message.length() - 1);
        }
        
        
        if (!(!ChatAllowedCharacters.func_48614_a(par1) || message.length() >= 100))
        {
            message += par1;
        }
        
        
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        drawRect(2, height - 14, width - 2, height - 2, 0x80000000);
        drawString(fontRenderer, (new StringBuilder()).append("\247a> ").append(message).append((updateCounter / 6) % 2 != 0 ? "" : "\247a_").toString(), 4, height - 12, 0xe0e0e0);
        super.drawScreen(par1, par2, par3);
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        if (par3 != 0)
        {
            return;
        }

        if (mc.ingameGUI.field_933_a == null)
        {
            super.mouseClicked(par1, par2, par3);
            return;
        }

        if (!(message.length() <= 0 || message.endsWith(" ")))
        {
            message += " ";
        }

        message += mc.ingameGUI.field_933_a;
        byte byte0 = 100;

        if (message.length() > byte0)
        {
            message = message.substring(0, byte0);
        }
    }
}
