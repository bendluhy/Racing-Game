package engine.scene;

import engine.Input;
import engine.Renderer;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private List <Scene> scenes = new ArrayList<Scene>();
    private Renderer renderer;
    private Input input;
    private int sceneIndex = 0;
    public SceneManager(Scene scene, Renderer renderer, Input input) {
      scene.init();
      this.scenes.add(scene);
      this.renderer = renderer;
      this.input = input;
    }
    public void addScene(Scene scene) {
        scenes.add(scene);
    }
    public Scene getCurrentScene() {
        return scenes.get(this.sceneIndex);
    }
    public void setSceneIndex(int sceneIndex) {
        scenes.get(this.sceneIndex).onChange();
        scenes.get(this.sceneIndex).init();
        this.sceneIndex = sceneIndex;
    }
    public void nextScene() {
        scenes.get(this.sceneIndex).onChange();
        scenes.get(this.sceneIndex +1).init();
        this.sceneIndex++;
    }
    public void render() {
        try {
            this.scenes.get(this.sceneIndex).update(renderer, input, this);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Last Scene");
            this.setSceneIndex(this.sceneIndex - 1);
        }
    }
}
