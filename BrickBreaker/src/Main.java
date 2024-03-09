import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Creating JFrame
        JFrame frame = new JFrame("Brick Breaker?");

        GamePlay gamePlay = new GamePlay();

        frame.setBounds(10, 10, 700, 600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}