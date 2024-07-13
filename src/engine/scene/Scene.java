package engine.scene;

import engine.Input;
import engine.Renderer;

public class Scene {
    //Scene Template Class
    public void init() {
        System.out.println("Didn't Override Init Function");
    }
    public void onChange() {
        System.out.println("Didn't Ovveride onChange function");
    }
    public void update(Renderer renderer, Input input, SceneManager sceneManager) {
        System.out.println("Didn't Override Update Function");
    }
}
