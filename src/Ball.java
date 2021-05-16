
import java.awt.*;

public class Ball extends GameObject {

    final static int DIAMETER = 10;
   
    public Ball(int x, int y, int velocityX, int velocityY) {
        super(x, y, velocityX, velocityY, DIAMETER, DIAMETER);
        color = Color.RED;
    }
    
    public void accelerate() {
        if (x < 0) {
            velocityX = Math.abs(velocityX);
        } else if (x > rightBound) {
            velocityX = -Math.abs(velocityX);
        }
        if (y < 0) {
            velocityY = Math.abs(velocityY);
        } else if (y > bottomBound) {
        }
    }

    public void bounce(Hit i) {
        switch (i) {
            case NONE:
                break;
            case UP:
                velocityY = -Math.abs(velocityY);
                break;
            case DOWN:
                velocityY = Math.abs(velocityY);
                break;
            case LEFT:
                velocityX = -Math.abs(velocityX);
                break;
            case RIGHT:
                velocityX = Math.abs(velocityX);
                break;
        }
    }
    
   @Override
    public void move(){
       x += velocityX;
       y += velocityY; 
       accelerate();
    }
   
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }
}
