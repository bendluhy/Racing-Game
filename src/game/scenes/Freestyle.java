package game.scenes;

import engine.Input;
import engine.Renderer;
import engine.scene.Scene;
import engine.scene.SceneManager;
import engine.util.Texture;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class Freestyle extends Scene {
    private Texture starimg;
    @Override
    public void init() {
        this.starimg = new Texture("res/star.png");
        this.starimg.bind();
    }
    @Override public void onChange() {
        this.starimg.unBind();
    }
    @Override public void update(Renderer renderer, Input input, SceneManager sceneManager) {
        renderer.renderIMG(0,0,this.starimg);
        System.out.println("currently in scene2");
        if(input.getKey(GLFW_KEY_SPACE))
        {
            sceneManager.setSceneIndex(0);
        }
    }
}
