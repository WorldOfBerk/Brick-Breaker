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

    public void paint(Graphics g){
        //Setting up background color
        g.setColor(Color.pink);
        g.fillRect(1, 1, 692, 592);

        //Drawing map
        map.draw((Graphics2D)g);

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

        //Check End conditions
        if(totalBricks <= 0){
            //When all bricks are broken, win the game
            play = false; //stop game
            ballXdirection = 0;
            ballYdirection = 0;
            //Display the winning message on the screen
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU WON; CONGRATS!", 150, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press 'ENTER' to Restart", 230, 350);
        }

        else if(ballPosY > 570){
            //When the ball hits the ground, lose the game
            play = false; //stop game
            ballXdirection = 0;
            ballYdirection = 0;
            //Display the losing message on the screen
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("GAME OVER, YOU LOST!",100, 300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press 'Enter' to Restart",230,350);
        }
        g.dispose(); //Dispose of the graphics object
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play){
            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdirection=-ballYdirection;
            }
            A: for (int i = 0; i < map.map.length; i++){
                for (int j = 0; j < map.map[0].length; j++){
                   if (map.map[i][j] > 0){
                       int brickX = j*map.brickWidth+80;
                       int brickY = i*map.brickHeight+50;
                       int brickWidth = map.brickWidth;
                       int brickHeight = map.brickHeight;

                       Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                       Rectangle ballRectangle = new Rectangle(ballPosX, ballPosY, 20, 20);
                       Rectangle brickRectangle = rect;
                       if (ballRectangle.intersects(brickRectangle)){
                           map.setBrickValue(0, i, j);
                           totalBricks--;
                           score+=5;

                           if (ballPosX+19 < -brickRectangle.x || ballPosX+1 >= brickRectangle.x +brickRectangle.width){
                               ballXdirection=-ballXdirection;
                           }
                           else{
                               ballYdirection=-ballYdirection;
                           }
                           break A;
                       }
                   }
                }
            }
            ballPosX+=ballXdirection;
            ballPosY+=ballYdirection;
            if (ballPosX < 0){
                ballXdirection=-ballXdirection;
            }
            if (ballPosY < 0){
                ballYdirection=-ballYdirection;
            }
            if (ballPosX > 670){
                ballXdirection=-ballXdirection;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            if (playerX >= 600){
                playerX = 600;
            }
            else {
                moveRight();
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            if (playerX < 10){
                playerX  = 10;
            }
            else {
                moveLeft();
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            if (!play){
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdirection=-1;
                ballYdirection=-2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3, 7);

                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveRight(){
        play = true;
        playerX+=20;
    }

    public void moveLeft(){
        play = true;
        playerX-=20;
    }
}


