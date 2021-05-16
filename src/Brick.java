import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Brick extends GameObject {

    private final int interval = 35;
    final static int HEIGHT = 12;
    final static int WIDTH = 45;
    private final String brickBreakSoundFile = "sound\\pop.wav";

    Timer animationTimer;

    private int ANIMATION_STEPS = 2;

    public int step = 0;

    public boolean visible = true;

    public int hits = 0;

    public Brick(int x, int y) {
        super(x, y, 0, 0, WIDTH, HEIGHT);
    }

    public Brick(int x, int y, Color c) {
        super(x, y, 0, 0, WIDTH, HEIGHT);
        color = c; 
        if (c == Color.white) {
            hits = -1;
        }
    }

    @Override
    public void accelerate() {}

    @Override
    public void draw(Graphics g) {
        if (visible == false) {
            return;
        }
        String str;
        LevelFactory l = new LevelFactory();
        int lvl = l.levelOn();
        if (color.equals(Color.white)) {
            if(step==0)
                g.setColor(color);
            else g.setColor(Color.CYAN);
            g.fillRect(x, y, width, height);
        }else{
            g.setColor(color);
            if(lvl==3){
                g.fillRect(x, y, width-11, height+5);
            }else{
                g.fillRect(x, y, width, height);
            }
        }
    }

    @Override

    public Hit hits(GameObject other) {
        Hit i = super.hits(other);
        if (i != Hit.NONE) {
        }
        return i;
    }

    void disappear(final Board pc) {
        if (hits < 0) {
            hits++;
            step++;
            return;
        }

        animationTimer = new Timer(interval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                animationTick(pc);
            }
        });
        animationTimer.start();
    }


    public void animationTick(Board pc) {
        if (step < ANIMATION_STEPS - 1) {
            step++;
        } else {
            visible = false;
            animationTimer.stop();
            pc.repaint();
        }
    }


    void playSound() {
        Sound brickBreakSound = new Sound(brickBreakSoundFile);
        brickBreakSound.playSoundOnce();
    }
}
