package engine;

import engine.util.Color;
import engine.util.Texture;
import org.lwjgl.opengl.GL11;

public class Renderer {
    public Renderer() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 1920, 1080, 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }
    public void rect(int x1, int y1, int x2, int y2, Color color) {
        GL11.glColor3f(color.getR(),color.getG(),color.getB());
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x1, y1); // Top-left
        GL11.glVertex2f(x2, y1); // Top-right
        GL11.glVertex2f(x2, y2); // Bottom-left
        GL11.glVertex2f(x1, y2); // Bottom-right
        GL11.glEnd();
    }
    public void renderIMG(int x, int y, Texture texture)  {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y + texture.getHeight()); // Bottom-left
        GL11.glTexCoord2f(1, 0);

        GL11.glVertex2f(x +texture.getWidth(), y +texture.getHeight()); // Bottom-right
        GL11.glTexCoord2f(1, 1);

        GL11.glVertex2f(x + texture.getWidth(), y); // Top-right
        GL11.glTexCoord2f(0, 1);

        GL11.glVertex2f(x, y); // Top-left
        GL11.glEnd();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
}
