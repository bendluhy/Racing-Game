package engine.util;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
    private int textureID;
    int h;
    int w;
    public Texture(String path) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer widthBuffer = stack.mallocInt(1);
            IntBuffer heightBuffer = stack.mallocInt(1);
            IntBuffer channelsBuffer = stack.mallocInt(1);

            // Load image
            ByteBuffer image = STBImage.stbi_load(path, widthBuffer, heightBuffer, channelsBuffer, 4);
            if (image == null) {
                throw new RuntimeException("Failed to load a texture file!" + System.lineSeparator() + STBImage.stbi_failure_reason());
            }
            this.w = widthBuffer.get();
            this.h = heightBuffer.get();

            STBImage.stbi_set_flip_vertically_on_load(true);

            // Create a new OpenGL texture
            this.textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, this.textureID);

            // Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

            // Upload the texture data
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.w, this.h, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);

            // Generate Mip Map
            glGenerateMipmap(GL_TEXTURE_2D);

            // Setup the texture parameters
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            // Free the memory
            STBImage.stbi_image_free(image);

            // Unbind the texture
            glBindTexture(GL_TEXTURE_2D, 0);
        }
    }
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, this.textureID);
    }
    public void unBind() {
        glBindTexture(GL_TEXTURE_2D, 0);
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
