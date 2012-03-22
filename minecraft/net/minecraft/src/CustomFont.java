package net.minecraft.src;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class CustomFont
{
    private int texID;
    private int xPos[];
    private int yPos[];
    private int startChar;
    private int endChar;
    private FontMetrics metrics;

    public CustomFont(Minecraft minecraft, String s, int i)
    {
        this(minecraft, s, i, 32, 126);
    }

    public CustomFont(Minecraft minecraft, String s, int i, int j, int k)
    {
        startChar = j;
        endChar = k;
        xPos = new int[k - j];
        yPos = new int[k - j];
        BufferedImage bufferedimage = new BufferedImage(256, 256, 2);
        Graphics g = bufferedimage.getGraphics();
        g.setFont(new Font(s, 0, i));
        g.setColor(new Color(255, 255, 255, 0));
        g.fillRect(0, 0, 256, 256);
        g.setColor(Color.WHITE);
        metrics = g.getFontMetrics();
        int l = 2;
        int i1 = 2;
        for (int j1 = j; j1 < k; j1++)
        {
            g.drawString((new StringBuilder()).append("").append((char)j1).toString(), l, i1 + g.getFontMetrics().getAscent());
            xPos[j1 - j] = l;
            yPos[j1 - j] = i1;
            l += metrics.stringWidth((new StringBuilder()).append("").append((char)j1).toString()) + 2;
            if (l >= 250 - metrics.getMaxAdvance())
            {
                l = 2;
                i1 += metrics.getMaxAscent() + metrics.getMaxDescent() + 2;
            }
        }

        texID = minecraft.renderEngine.allocateAndSetupTexture(bufferedimage);
    }

    public void drawStringS(Gui gui, String s, int i, int j, int k)
    {
        int l = k & 0xff000000;
        int i1 = (k & 0xfcfcfc) >> 2;
        i1 += l;
        drawString(gui, s, i + 1, j + 1, i1);
        drawString(gui, s, i, j, k);
    }

    public void drawString(Gui gui, String s, int i, int j, int k)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, texID);
        float f = (float)(k >> 16 & 0xff) / 255F;
        float f1 = (float)(k >> 8 & 0xff) / 255F;
        float f2 = (float)(k & 0xff) / 255F;
        float f3 = (float)(k >> 24 & 0xff) / 255F;
        GL11.glColor3f(f, f1, f2);
        int l = i;
        for (int i1 = 0; i1 < s.length(); i1++)
        {
            char c = s.charAt(i1);
            if (c == '\\')
            {
                char c1 = s.charAt(i1 + 1);
                if (c1 == 'n')
                {
                    j += metrics.getAscent() + 2;
                    i = l;
                }
                i1++;
            }
            else
            {
                drawChar(gui, c, i, j);
                i = (int)((double)i + metrics.getStringBounds((new StringBuilder()).append("").append(c).toString(), null).getWidth());
            }
        }
    }

    public FontMetrics getMetrics()
    {
        return metrics;
    }

    public int getStringWidth(String s)
    {
        return (int)getBounds(s).getWidth();
    }

    public int getStringHeight(String s)
    {
        return (int)getBounds(s).getHeight();
    }

    private Rectangle getBounds(String s)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        for (int l = 0; l < s.length(); l++)
        {
            char c = s.charAt(l);
            if (c == '\\')
            {
                char c1 = s.charAt(l + 1);
                if (c1 == 'n')
                {
                    j += metrics.getAscent() + 2;
                    if (k > i)
                    {
                        i = k;
                    }
                    k = 0;
                }
                l++;
            }
            else
            {
                k += metrics.stringWidth((new StringBuilder()).append("").append(c).toString());
            }
        }

        if (k > i)
        {
            i = k;
        }
        j += metrics.getAscent();
        return new Rectangle(0, 0, i, j);
    }

    private void drawChar(Gui gui, char c, int i, int j)
    {
        Rectangle2D rectangle2d = metrics.getStringBounds((new StringBuilder()).append("").append(c).toString(), null);
        gui.drawTexturedModalRect(i, j, xPos[(byte)c - startChar], yPos[(byte)c - startChar] + metrics.getDescent(), (int)rectangle2d.getWidth(), (int)rectangle2d.getHeight());
    }
}
