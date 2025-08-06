// Imports for creating GUI components
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Random;

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

    // List to hold pipes
    private ArrayList<Pipe> pipes;
    
    // Timer to spawn new pipes
    private Timer pipeSpawnTimer;
    private Random random = new Random();

    // Pipe properties
    private int pipeWidth = 60;
    private int pipeGap = 150;
    private int pipeVelocityX = 4;

    // Add gameover flag
    private boolean gameOver = false;

    // Score variable
    private int score = 0;

    // Game started flag
    private boolean gameStarted = false;

    // Constructor of flappy bird class
    public FlappyBird(){
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT)); // sets preferred size of the panel
        setBackground(Color.cyan); // sets background colour of the panel
        setFocusable(true); // Allows panel to receive keyboard input
        addKeyListener(new MyKeyAdapter()); 

        setupGame();
    }


    //Method to setup game
    private void setupGame(){
        // Initialise 'Bird' object in constructor
        int birdWidth = 34;
        int birdHeight = 24;
        int birdX = BOARD_WIDTH / 8;
        int birdY = BOARD_HEIGHT / 2;
        Color birdColor = Color.YELLOW;
        bird = new Bird(birdX, birdY, birdWidth, birdHeight, birdColor);

        // Initialise pipes list
        pipes = new ArrayList<Pipe>();

        // Set up game loop but do not start timers
        gameLoop = new Timer(1000 / 60, this); // Fires an event 60 times per second

        // Timer for spawning pipes
        pipeSpawnTimer = new Timer(1500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                placePipes();
            }
        });
    }

    // Method to reset the game
    private void resetGame(){
        gameOver = false;
        score = 0;
        pipes.clear();

        // reset birds position and velocity
        int birdX = BOARD_WIDTH / 8;
        int birdY = BOARD_HEIGHT / 2;
        
        bird = new Bird(birdX, birdY, bird.hitbox.width, bird.hitbox.height, Color.yellow);

        // Stop the timers
        gameLoop.stop();
        pipeSpawnTimer.stop();

        // Update screen
        repaint();

        // Reset game satrted flag
        gameStarted = false;
    }

    // Method to generate and add new pipes
    private void placePipes() {
        int randomPipeY = (int) (random.nextInt(BOARD_HEIGHT / 2) + BOARD_HEIGHT / 4);
        int topHeight = randomPipeY;
        int bottomHeight = BOARD_HEIGHT - topHeight - pipeGap;

        Pipe newPipe = new Pipe(BOARD_HEIGHT, 0, pipeWidth, pipeGap, topHeight, bottomHeight, Color.GREEN);
        pipes.add(newPipe);
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw start screen
        if (!gameStarted){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Press space to start", BOARD_WIDTH / 2 - 150, BOARD_HEIGHT / 2);
        } else {
            bird.draw(g);

            // draw all pipes in list
            for (Pipe pipe : pipes) {
                pipe.draw(g);
            }

            // Draw score
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            if (!gameOver){
                g.drawString(String.valueOf(score), 10, 35);
            } else {
                // Game over message if the game ends
                g.drawString("Score: " + String.valueOf(score), 10, 35);           
                g.setColor(Color.red);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("Game Over", BOARD_WIDTH / 2 - 120, BOARD_HEIGHT / 2);
                g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("Press 'R' to restart", BOARD_WIDTH / 2 - 75, BOARD_HEIGHT / 2 + 50);
            }
        }
    }

    // Method called every time the timer 'ticks'
    @Override
    public void actionPerformed(ActionEvent e){
        // Check game is not over
        if (gameStarted && !gameOver){
            // Apply gravity to the bird's velocity
            bird.setyVelocity(bird.getYVelocity() + gravity);
            // Update bird's position
            bird.update();

            // move all pipes
            for (Pipe pipe : pipes) {
                pipe.move(pipeVelocityX);

                // Score logic
                if (bird.hitbox.x > pipe.topRectangle.x + pipe.topRectangle.width && !pipe.isPassed()){
                    score++;
                    pipe.setPassed(true);
                }
                // Check for collision with pipe
                if (bird.hitbox.intersects(pipe.topRectangle) || bird.hitbox.intersects(pipe.bottomRectangle)) {
                    gameOver = true;
                    // Stop the timers
                    gameLoop.stop();
                    pipeSpawnTimer.stop();
                }
            }

            // Check for collision with ground
            if (bird.getY() >= BOARD_HEIGHT - bird.hitbox.height || bird.getY() < 0) {
                gameOver = true;
                gameLoop.stop();
                pipeSpawnTimer.stop();
            }
        }

        // Redraw the panel
        repaint();
    }

    // Inner class to handle key events
    private class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            // Start game
            // Check if pressed key is the space bar
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                if (!gameStarted){
                    gameStarted = true;
                    gameLoop.start();
                    pipeSpawnTimer.start();
                } else if (!gameOver){
                    bird.flap();
                }
            }
            // Restart logic
            if (e.getKeyCode() == KeyEvent.VK_R){
                if (gameOver){
                    resetGame();
                }
            }
        }
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
