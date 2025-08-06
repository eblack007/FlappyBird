// Imports for creating GUI components
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Creating class 'FlappyBird' that can be added to JFrame
public class FlappyBird extends JPanel implements ActionListener{
    // Define size of game window
    public static final int BOARD_WIDTH = 360;
    public static final int BOARD_HEIGHT = 640;

    // Bird instance as member variable
    private Bird bird;

    // Define gravity and timer
    private Timer gameLoop;
    private int gravity = 1;

    // Constructor of flappy bird class
    public FlappyBird(){
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT)); // sets preferred size of the panel
        setBackground(Color.cyan); // sets background colour of the panel
        setFocusable(true); // Allows panel to receive keyboard input

        // Initialise 'Bird' object in constructor
        int birdWidth = 34;
        int birdHeight = 24;
        int birdX = BOARD_WIDTH / 8;
        int birdY = BOARD_HEIGHT / 2;
        Color birdColor = Color.YELLOW;
        bird = new Bird(birdX, birdY, birdWidth, birdHeight, birdColor);

        // Set up and start game loop
        gameLoop = new Timer(1000 / 60, this); // Fires an event 60 times per second
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        bird.draw(g);
    }

    // Method called every time the timer 'ticks'
    @Override
    public void actionPerformed(ActionEvent e){
        // Apply gravity to the bird's velocity
        bird.setyVelocity(bird.getYVelocity() + gravity);
        // Update bird's position
        bird.update();
        // Redraw the panel
        repaint();
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
