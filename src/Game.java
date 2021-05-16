
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.StyledDocument;

public class Game implements Runnable {

    private Sound music;
    private boolean playsound = true;
    private String playerName;
    private int trigger = -1;
    public void run() {

        // frame
        final JFrame frame = new JFrame("Breakout");
        frame.setLocation(600, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main playing area
        final Board court = new Board();
        frame.add(court, BorderLayout.CENTER);
        
        
        final JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.SOUTH);

        final JTextPane jpane = new JTextPane();
       
        final JTextPane name = new JTextPane();
        name.setText("Player : " );
        //HOW TO PLAY button
        final JButton info = new JButton("HOW TO PLAY");
        info.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(court, "Please press space bar to start the game\n\nand use arrow keys to move the paddle\n\n**Please watch out a countdown**", "Greetings from team ALOHA 🏝", JOptionPane.INFORMATION_MESSAGE);
                trigger ++ ;
                if (trigger==0) {
                    //Player's name
                    playerName = JOptionPane.showInputDialog(frame, "Please enter your name :", "👾", JOptionPane.PLAIN_MESSAGE);
                    if(playerName == null)
                        name.setText("Player : "+ "anonymous");
                    else name.setText("Player : "+ playerName);
                }
                court.requestFocusInWindow();
            }
        });
        panel.add(info);
        
        panel.add(name);
        
        //Countdown
        final JTextPane time = new JTextPane();
        time.setText("Timer : ");
        court.setCount(time);
        panel.add(time);
        
        //Score
        jpane.setText("Score: " + 0);
        court.setScoreJTP(jpane);
        panel.add(jpane);

        //Bricks left
        final JTextPane bricksLeft = new JTextPane();
        court.setBricksLeftJTP(bricksLeft);
        panel.add(bricksLeft);

        //Level
        final JTextPane level = new JTextPane();
        court.setLevelJTP(level);
        panel.add(level);

        music = new Sound("sound\\Fur_Elise.wav");
        music.playSound();

        final JButton sound = new JButton("Music Off");

        sound.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playsound) {
                    sound.setText("Music On");
                    music.stopSound();
                    playsound = false;
                } else {
                    sound.setText("Music Off");
                    music.playSound();
                    playsound = true;
                }
                court.requestFocusInWindow();
            }
        });
        panel.add(sound);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        court.reset();
        info.doClick();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
