
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JComponent {

    public int score = 0;
    boolean started = false;
    private int smallPowerUpTimeLeft = -1;
    private int bigPowerUpTimeLeft = -1;
    private int yellowBallTimeLeft = -1;

    private JTextPane score_jtp = null;
    private JTextPane bricks_left_jtp = null;
    private JTextPane level_jtp = null;
    private JTextPane count = null;

    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private Paddle paddle;
    Brick[][] bricks;
    ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
    LevelFactory levelfactory = null;

    private int space = 35;
    private Timer timer;
    private Timer timer2;
    final int BOARDWIDTH = 650;
    final int BOARDHEIGHT = 400;
    final int PADDLE_VEL = 15;
    final int POWER_UP_DURATION = 4500;
    private int countdown;
    private int prev;

    public Board() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);
        timer = new Timer(space, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();
        timer2 = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                count();
            }
        });
        timer2.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    //Move left
                    paddle.setVelocity(-PADDLE_VEL, 0);
                    if (!started) {
                        for (Ball b : balls) {
                            b.setVelocity(-PADDLE_VEL, 0);
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    //Move right
                    paddle.setVelocity(PADDLE_VEL, 0);
                    if (!started) {
                        for (Ball b : balls) {
                            b.setVelocity(PADDLE_VEL, 0);
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_R) {
                    reset();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE && !started) {
                    //press SPACEBAR to start the game
                    int bvelx;
                    if (paddle.velocityX == 0) {
                        bvelx = 4;
                    } else {
                        bvelx = (paddle.velocityX / Math.abs(paddle.velocityX)) * 7;
                    }
                    for (Ball b : balls) {
                        b.setVelocity(bvelx, -8);
                    }
                    started = true;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Stop moving the paddle (and the connected balls, if any) when the
                //arrow keys are released
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    paddle.setVelocity(0, 0);
                    if (!started) {
                        for (Ball b : balls) {
                            b.setVelocity(0, 0);
                        }
                    }
                }
            }
        });
    }

 
    public void lose() {
        started = false;
        int option = JOptionPane.showOptionDialog(this, "Well lol but I see that you just lost. \n               Start over?", "Oops", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Yes", "No"}, null);
        if (option == 0) {
            reset();
        } else if (option == 1) {
            System.exit(0);
        } else if (option == JOptionPane.CLOSED_OPTION) {
            reset();

        }
    }

    public void win() {
        int option = JOptionPane.showOptionDialog(this, "OH. MY. GOSH. You won! Would you like to play from the level 1 again with the same score?", "IMPOSSIBLE", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Yes", "No"}, null);
        if (option == 0 || option == JOptionPane.CLOSED_OPTION) {
            resetCourt();
            levelfactory = new LevelFactory();
            bricks = levelfactory.getLevel();
            level_jtp.setText("Level: " + levelfactory.levelOn());
        } else if (option == 1) {
            System.exit(0);
        }
    }

    public void reset() {
        levelfactory = new LevelFactory(true);
        bricks = LevelFactory.getLevel();
        level_jtp.setText("Level: " + levelfactory.levelOn());
        score = 0;
        score_jtp.setText("Score: " + 0);
        countdown = 30;
        resetCourt();
        requestFocusInWindow();
    }

    public void newLevel() {
        resetPowerups();
        prev = countdown;
        countdown = 50 + prev;
        if (levelfactory.hasMoreLevels()) {
            levelfactory.nextLevel();
            bricks = levelfactory.getLevel();
            level_jtp.setText("Level: " + levelfactory.levelOn());
            resetCourt();
        } else {
            win();
        }
    }

    void count() {
        if (started) {
            countdown--;
        }
        if(countdown<=10)
            count.setText("Time: " + countdown + " !!");
        else count.setText("Time: " + countdown);
        if (countdown == 0) {
            lose();
        }
    }

    void tick() {
        decrementPowerUps();

        if (bigPowerUpTimeLeft >= 0) {
            paddle.width = Paddle.WIDTH + 40;
        } else if (smallPowerUpTimeLeft >= 0) {
            paddle.width = Paddle.WIDTH - 30;
        } else {
            paddle.width = Paddle.WIDTH;
        }
        paddle.setBounds(getWidth(), getHeight());

        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).setBounds(getWidth(), getHeight());
            if (!started
                    && ((paddle.x <= 0 && balls.get(i).velocityX < 0) || (paddle.x >= paddle.rightBound && balls.get(i).velocityX > 0))) {
            } else {
                balls.get(i).move();
            }
        }
        paddle.move();

        for (int i = 0; i < powerups.size(); i++) {
            PowerUp p = powerups.get(i);
            if (p != null && Math.abs(p.y) > BOARDHEIGHT) {
                powerups.set(i, null);
                powerups.remove(i);
            }

            if (p == null) {
                powerups.remove(i);
                continue;
            }
            p.move();
            if (p.hits(paddle) != Hit.NONE) {
                activatePowerUp(p);
                powerups.set(i, null);
            }
        }

        for (int i = 0; i < balls.size(); i++) {
            Hit paddle_ball = paddle.hits(balls.get(i));
            if (started && paddle_ball != Hit.NONE) {
                (new Sound("sound\\baseball.wav")).playSoundOnce();
                balls.get(i).bounce(paddle.hits(balls.get(i)));
                balls.get(i).velocityX += ((balls.get(i).x + balls.get(i).width / 2) - (paddle.x + paddle.width / 2)) / (paddle.width / 9);
                if (paddle.velocityX > 0) {
                    balls.get(i).velocityX -= 2;
                } else if (paddle.velocityX < 0) {
                    balls.get(i).velocityX += 2;
                }
            }
        }

        int bricksLeft = 0;
        for (int col = 0; col < bricks.length; col++) {
            for (int row = 0; row < bricks[col].length; row++) {

                if (bricks[col][row] == null || !bricks[col][row].visible) {
                    bricks[col][row] = null;
                } else {
                    bricksLeft++;
                }
                for (int i = 0; i < balls.size(); i++) {
                    if (balls.get(i) == null || bricks[col][row] == null) {
                        continue;
                    }
                    Hit hit = bricks[col][row].hits(balls.get(i));
                    if (yellowBallTimeLeft < 0) {
                        balls.get(i).bounce(hit);
                    }
                    if (hit != Hit.NONE) {

                        if (Math.random() * 100 < 10 && bricks[col][row].hits == 0) {
                            powerups.add(PowerUp.randomPowerUp(bricks[col][row].x + bricks[col][row].width / 2 - PowerUp.DIAMETER / 2,
                                    bricks[col][row].y));
                        }
                        if (yellowBallTimeLeft < 0) {
                            bricks[col][row].disappear(this);
                            bricks[col][row].playSound();
                        } else {
                            bricks[col][row] = null;
                        }
                        score++;
                        score_jtp.setText("Score: " + score);
                    }
                }
            }
        }
        bricks_left_jtp.setText("Bricks left: " + bricksLeft);
        if (bricksLeft == 0) {
            score = score + (int) (countdown * 1.5);
            score_jtp.setText("Score: " + score);
            newLevel();
        }

        Ball b = balls.get(0);
        if (b != null && Math.abs(b.y) > BOARDHEIGHT) {
            balls.remove(b);
            if (balls.isEmpty()) {
                lose();
            }
        }
        repaint();
    }

    private void resetCourt() {
        started = false;
        paddle = new Paddle(BOARDWIDTH, BOARDHEIGHT);
        balls.clear();
        Ball ball = new Ball(((BOARDWIDTH - Ball.DIAMETER) / 2),
                (BOARDHEIGHT - 2 * Paddle.HEIGHT - Ball.DIAMETER), 0, 0);
        balls.add(ball);
        ball.setBounds(getWidth(), getHeight());
        paddle.setBounds(getWidth(), getHeight());
        resetPowerups();
    }

    private void activatePowerUp(PowerUp p) {
        if (p instanceof SmallPaddlePowerUp) {
            smallPowerUpTimeLeft = POWER_UP_DURATION;
        } else if (p instanceof BigPaddlePowerUp) {
            bigPowerUpTimeLeft = POWER_UP_DURATION;
        } else if (p instanceof MultiBallPowerUp) {
            Ball b1 = new Ball(paddle.x + paddle.width / 2, paddle.y - paddle.height, 4, -8);
            Ball b2 = new Ball(paddle.x + paddle.width / 2, paddle.y - paddle.height, -5, -8);
            if (yellowBallTimeLeft >= 0) {
                b1.setColor(Color.ORANGE);
                b2.setColor(Color.ORANGE);
            }
            balls.add(b1);
            balls.add(b2);
        }
    }

    private void decrementPowerUps() {
        if (smallPowerUpTimeLeft >= 0) {
            smallPowerUpTimeLeft -= space;
        }
        if (yellowBallTimeLeft >= 0) {
            yellowBallTimeLeft -= space;
        }
        if (bigPowerUpTimeLeft >= 0) {
            bigPowerUpTimeLeft -= space;
        }
        if (yellowBallTimeLeft < 0) {
            for (Ball ball : balls) {
                ball.color = Color.red;
            }
        }
    }

    private void resetPowerups() {
        powerups.clear();
        bigPowerUpTimeLeft = -1;
        yellowBallTimeLeft = -1;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D myG = (Graphics2D) g;

        LevelFactory l = new LevelFactory();
        int lvl = l.levelOn();
        if (lvl == 3) {
            myG.setColor(Color.LIGHT_GRAY);
        } else {
            myG.setColor(Color.BLACK);
        }
        myG.fillRect(0, 0, 700, 400);
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).draw(g);
        }
        paddle.draw(g);
        for (int col = 0; col < bricks.length; col++) {
            for (int row = 0; row < bricks[col].length; row++) {
                Brick brick = bricks[col][row];
                if (brick != null) {
                    brick.draw(g);
                }
            }
        }
        for (int i = 0; i < powerups.size(); i++) {
            if (powerups.get(i) != null) {
                powerups.get(i).draw(g);
            }
        }
    }

    public void setScoreJTP(JTextPane j) {
        score_jtp = j;
    }

    public void setBricksLeftJTP(JTextPane j) {
        bricks_left_jtp = j;
    }

    public void setLevelJTP(JTextPane j) {
        level_jtp = j;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARDWIDTH, BOARDHEIGHT);
    }

    public void setCount(JTextPane count) {
        this.count = count;
    }
}
