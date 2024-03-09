import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;

    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdirection = -1;
    private int ballYdirection = -2;

    private MapGenerator map;
    public GamePlay(){
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay, this);
        timer.start();
    }

    public void Paint(Graphics g){
        //Setting up background color
        g.setColor(Color.pink);
        g.fillRect(1, 1, 692, 592);

        //Drawing map
        map.Draw((Graphics2D)g);

        //borders
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //Score
        g.setColor(Color.BLUE);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Your Score; " + score, 500, 30);

        //Platform
        g.setColor(Color.BLACK);
        g.fillRect(playerX, 550, 100, 10);

        //Ball
        g.setColor(Color.RED);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
