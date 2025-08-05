// Imports
import java.awt.Color;
import java.awt.Graphics;

// Create Bird class
public class Bird {
    // Member variables of 'Bird' class storing horizontal position 'x', vertical position 'y', dimensions 'width, height' and color 'color'
    private int x;
    private  int y;
    private int width;
    private int height;
    private Color color;

    // Constructor for Bird class
    public Bird(int x, int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Method used to draw the bird
    public void draw(Graphics g){ // take in a Graphics object from from Swing
        g.setColor(color);
        g.fillRect(x, y, width, height); // Draw a rectangle at birds x and y coordinates
    }
}
