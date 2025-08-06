// Imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;


public class Pipe {
    // Member variables for pipe dimensions, positon and colour
    private int x;
    private int width;
    private int topHeight;
    private int bottomHeight;
    private int gap;
    private Image topImage;
    private Image bottomImage;


    // Use rectangles for collison detection
    public Rectangle topRectangle;
    public Rectangle bottomRectangle;

    // Variable for scoring system
    private boolean passed = false;

    

    // Constructor for pipe
    public Pipe(int x, int y, int width, int topHeight, int bottomHeight, int gap, Image topImage, Image bottomImage) {
        this.x = x;
        this.width = width;
        this.topHeight = topHeight;
        this.bottomHeight = bottomHeight;
        this.gap = gap;
        this.topImage = topImage;
        this.bottomImage = bottomImage;
        
        // Initialise rectangles  for collision
        this.topRectangle = new Rectangle(x, 0, width, topHeight);
        this.bottomRectangle = new Rectangle(x, topHeight + gap, width, bottomHeight);
    }

    public void draw(Graphics g) {
        g.drawImage(topImage, topRectangle.x, topRectangle.y, topRectangle.width, topRectangle.height, null); // Draw top rectangle
        g.drawImage(bottomImage, bottomRectangle.x, bottomRectangle.y, bottomRectangle.width, bottomRectangle.height, null); // Draw bottom rectangle
    }

    public void move(int velocityX) {
        x -= velocityX;
        topRectangle.x = x;
        bottomRectangle.x = x;
    }

    public boolean isPassed(){
        return passed;
    }

    public void setPassed(boolean passed){
        this.passed = passed;
    }
}
