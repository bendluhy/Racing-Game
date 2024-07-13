package engine.object;

import engine.Input;
import engine.util.Texture;

import static org.lwjgl.glfw.GLFW.*;

public class Player {
    public String car_sprite_path = "res/red_lambo";
    public Texture car_texture;
    public Player() {
        this.car_texture = new Texture(car_sprite_path);
        this.car_texture.bind();
    }
    //TODO make car movement
    //Checks player movement and applies it to the movement
    //apply every game loop
    public void tickMovement(Input input)  {
        if (input.getKey(GLFW_KEY_W)) {
            //apply forward movement to vehicle
        }
        if(input.getKey(GLFW_KEY_A)) {
            //apply left to steering
        }
        if(input.getKey(GLFW_KEY_D)) {
            //apply right to steering
        }
        if(input.getKey(GLFW_KEY_S)) {
            //apply brakes
        }
        if(input.getKey(GLFW_KEY_SPACE)) {
            //apply e-brake
        }
    }
}
