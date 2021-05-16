
import java.awt.Color;
import java.awt.Graphics;

public abstract class GameObject {

    int x;
    int y;
    int width;
    int height;
    int velocityX; // Pixels to move
    int velocityY;
    int rightBound;
    int bottomBound;
    Color color;

    public GameObject(int x, int y, int velocityX, int velocityY, int width, int height) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.width = width;
        this.height = height;
    }

    public void setBounds(int width, int height) {
        rightBound = width - this.width;
        bottomBound = height - this.height;
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void move() {
        x += velocityX;
        y += velocityY;

        accelerate();
        clip();
    }


    public void clip() {
        if (x < 0) {
            x = 0;
        } else if (x > rightBound) {
            x = rightBound;
        }

        if (y < 0) {
            y = 0;
        } else if (y > bottomBound) {
            y = bottomBound;
        }
    }

    public Hit hits(GameObject other) {
        if (other.x > x + width || other.y > y + height || other.x + other.width < x || other.y + other.height < y) {
            return Hit.NONE;
        }
        double dx = other.x + other.width / 2 - (x + width / 2);
        double dy = other.y + other.height / 2 - (y + height / 2);

        double theta = Math.atan2(dy, dx);
        double diagTheta = Math.atan2(height, width);

        if (-diagTheta <= theta && theta <= diagTheta) {
            return Hit.RIGHT;
        }
        if (diagTheta <= theta && theta <= Math.PI - diagTheta) {
            return Hit.DOWN;
        }
        if (Math.PI - diagTheta <= theta || theta <= diagTheta - Math.PI) {
            return Hit.LEFT;
        }
        return Hit.UP;
    }

    public void setColor(Color c) {
        color = c;
    }

    public abstract void accelerate();

    public abstract void draw(Graphics g);
}
