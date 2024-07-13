package engine;

import org.lwjgl.glfw.GLFW;

//USE GLFW KEYCODES
public class Input {
    private long window;
    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST + 1];
    private boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST + 1];
    public double mouseX, mouseY;
    public Input(long pWindow) {
        this.window = pWindow;
        //Key press callback
        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key >= 0 && key < keys.length) {
                keys[key] = action != GLFW.GLFW_RELEASE;
            }
        });

        //Mouse button callback
        GLFW.glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            if (button >= 0 && button < mouseButtons.length) {
                mouseButtons[button] = action != GLFW.GLFW_RELEASE;
            }
        });

        //Mouse Position callback
        GLFW.glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
            mouseX = xpos;
            mouseY = ypos;
        });
    }
    //Checks array of keystates to see if its pressed
    public boolean getKey(int keyCode) {
        if (keyCode >= 0 && keyCode < keys.length) {
            return keys[keyCode];
        } else {
            return false;
        }
    }
    public boolean getMouseButton(int button) {
        if (button >= 0 && button < mouseButtons.length) {
            return mouseButtons[button];
        } else {
            return false;
        }
    }
    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }
}
