// Imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// Create Bird class
public class Bird {
    // Member variables of 'Bird' class storing horizontal position 'x', vertical position 'y', dimensions 'width, height' and color 'color'
    private int x;
    private  int y;
    private int width;
    private int height;
    private Color color;

    // Add variable for vertical velocity
    private int yVelocity;

    // Add rectangle for bird hitbox
    public Rectangle hitbox;

    // Constructor for Bird class
    public Bird(int x, int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.yVelocity = 0;
        this.hitbox = new Rectangle(x, y, width, height); // Initialise hitbox
    }

    // Method used to draw the bird
    public void draw(Graphics g){ // take in a Graphics object from from Swing
        g.setColor(color);
        g.fillRect(x, y, width, height); // Draw a rectangle at birds x and y coordinates
    }

    // Method to update birds position based on it vertical velocity
    public void update(){
        y += yVelocity;
        hitbox.y = y; // Update hitbox position to match birds position
    }

    // Getter and setters for y and yVelocity
    public int getY(){
        return y;
    }

    public void setyVelocity(int yVelocity){
        this.yVelocity = yVelocity;
    }

    public int getYVelocity(){
        return yVelocity;
    }

    public void flap(){
        // Set a strong negative velovity to make bird move up
        setyVelocity(-9);
    }
}
