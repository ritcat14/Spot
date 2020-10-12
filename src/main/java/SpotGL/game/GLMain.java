package SpotGL.game;

import SpotGL.core.GLEngine;
import SpotGL.game.states.Game;
import SpotGL.game.states.Start;

public class GLMain {

    public static void main(String[] args) {
        GLEngine glEngine = new GLEngine(new Start(), new Game());
        glEngine.start();
    }
}
