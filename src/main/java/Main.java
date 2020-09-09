import core.graphics.Frame;
import core.states.StateManager;
import game.Start;

import java.awt.*;

public class Main implements Runnable {

    private final double SECOND = 1000000000;
    private final double MAX_TPS = 120;
    private final int MAX_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int MAX_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private Thread thread;
    private Frame frame;
    private StateManager stateManager;

    private boolean running;

    private int TPS, FPS;
    private int finalTPS, finalFPS;

    public Main() {
        frame = new Frame(MAX_WIDTH, MAX_HEIGHT);
        stateManager = new StateManager();
        stateManager.addState(new Start());
    }

    public synchronized void start() {
        thread = new Thread(this, "Main");
        running = true;
        thread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ns = SECOND / (MAX_TPS/2);
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                delta--;
            }
            render();
            if(System.currentTimeMillis() - timer > 1000) {
                finalTPS = TPS;
                finalFPS = FPS;
                frame.setCounters(finalTPS, finalFPS);
                timer += 1000;
                TPS = 0;
                FPS = 0;

            }
        }

        stop();

    }

    private void update() {
        TPS++;
        // Update
    }

    private void render() {
        FPS++;
        frame.render(stateManager);
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace(); // Failed to stop thread, force exit. Ensure everything is appropriately closed first!
            close();
            System.exit(0);
        }
    }

    public void close() {
        stateManager.close();
        frame.close();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
