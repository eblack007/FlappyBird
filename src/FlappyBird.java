// Imports for creating GUI components
import javax.swing.*;
import java.awt.*;

// Creating class 'FlappyBird' that can be added to JFrame
public class FlappyBird extends JPanel {
    // Define size of game window
    public static final int BOARD_WIDTH = 360;
    public static final int BOARD_HEIGHT = 640;

    // Constructor of flappy bird class
    public FlappyBird(){
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT)); // sets preferred size of the panel
        setBackground(Color.cyan); // sets background colour of the panel
        setFocusable(true); // Allows panel to receive keyboard input
    }
    // Main method of game
    public static void main(String[] args){
        JFrame frame = new JFrame("Flappy Bird"); // Create a new frame with title 'Flappy Bird'
        FlappyBird gamePanel = new FlappyBird(); // Create instance of 'FlappyBird' panel
        frame.add(gamePanel); // add panel to frame
        frame.pack(); // resize frame to fit preferred size of panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLocationRelativeTo(null); // centers window on screen
        frame.setVisible(true);
    }
}
