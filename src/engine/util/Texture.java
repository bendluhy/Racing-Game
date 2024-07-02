package engine.util;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;

public class Texture {
    private int textureID;
    int h;
    int w;
    public Texture(String path) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);


            // Load image
            ByteBuffer image = stbi_load(path, width, height, channels, 4);
            if (image == null) {
                throw new RuntimeException("Failed to load a texture file!" + System.lineSeparator() + stbi_failure_reason());
            }
            this.w = width.get();
            this.h = height.get();
            // Create a new OpenGL texture
            Object textureID = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureID);

            // Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

            // Upload the texture data
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.w, this.h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);

            // Generate Mip Map
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
            // Setup the texture parameters
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

            // Free the memory
            stbi_image_free(image);
        }
    }
    public int getHeight() {
        return h;
    }
    public int getWidth(){
        return w;
    }
    public int getTextureID() {
        return this.textureID;
    }
}
