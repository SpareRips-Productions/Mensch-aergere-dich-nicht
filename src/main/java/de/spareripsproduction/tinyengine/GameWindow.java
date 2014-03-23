package  de.spareripsproduction.tinyengine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-08
 */
public class GameWindow extends Canvas{

    private BufferStrategy strategy;

    private JFrame frame;

    private static GameWindow instance;

    public static GameWindow getInstance() {
        return getInstance("GameWindow", 800, 600);
    }

    public static GameWindow getInstance(String title, int width, int height) {
        if(GameWindow.instance == null) {
            GameWindow.instance = new GameWindow(title, width, height);
        }
        return GameWindow.instance;
    }

    public GameWindow(String title, int width, int height) {

        frame = new JFrame(title);

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(width,height));
        setBounds(0,0,width,height);
        panel.add(this);

        //ignore refresh
        this.setIgnoreRepaint(true);

        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Core.exit(0);
            }
        });

        requestFocus();
        /*
         *   create the buffering strategy which will allow AWT
         *   to manage our accelerated graphics
         */
        createBufferStrategy(2);
        strategy = getBufferStrategy();

    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public Graphics2D getGraphicsContext() {
        return (Graphics2D) strategy.getDrawGraphics();
    }

    public void repaint() {
        getGraphicsContext().dispose();
        strategy.show();
    }

    public JFrame getFrame() {
        return frame;
    }
}
