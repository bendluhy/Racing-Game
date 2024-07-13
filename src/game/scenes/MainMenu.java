package game.scenes;

import engine.Input;
import engine.Renderer;
import engine.scene.Scene;
import engine.scene.SceneManager;
import engine.util.Texture;

import static org.lwjgl.glfw.GLFW.*;

public class MainMenu extends Scene {
    private Texture freestyle_btn_img;
    int button_w = 400;
    int button_h = 200;
    int button_x = (1920/2) - 200;
    int button_y = 1080/2;

    @Override
    public void init() {
        this.freestyle_btn_img = new Texture("res/freestyle_button.png");
        this.freestyle_btn_img.bind();
    }
    @Override public void onChange() {
        this.freestyle_btn_img.unBind();
    }
    @Override public void update(Renderer renderer, Input input, SceneManager sceneManager) {
        renderer.renderIMG(button_x,button_y,this.freestyle_btn_img);
        System.out.println("currently in scene1");
        if(input.getMouseButton(0))
        {
            if(button_x < input.getMouseX() && input.getMouseX() < button_x + button_w){
                if(button_y < input.getMouseY() && input.getMouseY() < button_y + button_h) {
                    sceneManager.nextScene();
                }
            }
        }
    }
}
