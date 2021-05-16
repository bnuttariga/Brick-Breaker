import java.awt.*;

public class Paddle extends GameObject {

   final static int HEIGHT = 14;
   final static int WIDTH = 70;
   
   public Paddle(int courtwidth, int courtheight) {
      super((courtwidth - WIDTH) / 2, courtheight - HEIGHT - HEIGHT, 0, 0, WIDTH, HEIGHT);
      color = Color.WHITE;
   }
   
   public void accelerate() {
      if (x < 0 || x > rightBound) {
         velocityX = 0;
      }
      if (y < 0 || y > bottomBound) {
         velocityY = 0;
      }
   }
   
   
   public void draw(Graphics g) {
      LevelFactory l = new LevelFactory();
      int lvl = l.levelOn();
      if(lvl==3)
        g.setColor(Color.black);
      else
        g.setColor(color);
      g.fillRect(x, y, width, height);
   }
}
