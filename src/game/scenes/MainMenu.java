package game.scenes;

import engine.Input;
import engine.Renderer;
import engine.scene.Scene;
import engine.scene.SceneManager;
import engine.util.Texture;

import static org.lwjgl.glfw.GLFW.*;

public class MainMenu extends Scene {
    private Texture bgimg;
    @Override
    public void init() {
        this.bgimg = new Texture("res/bg.png");
        this.bgimg.bind();
    }
    @Override public void onChange() {
        this.bgimg.unBind();
    }
    @Override public void update(Renderer renderer, Input input, SceneManager sceneManager) {
        renderer.renderIMG(0,0,this.bgimg);
        System.out.println("currently in scene1");
        if(input.getMouseButton(0))
        {
            if(0 < input.getMouseX() && input.getMouseX() < 100){
                if(0 < input.getMouseY() && input.getMouseY() < 100) {
                    sceneManager.nextScene();
                }
            }
        }
    }
}
