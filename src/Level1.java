import java.awt.Color;

public class Level1 extends Level {

    private final static int BRICK_SEPARATION_X = 8;
    private final static int BRICK_SEPARATION_Y = 4;

    public Level1() {
    }

    public Brick[][] getLevelArray() {
        Brick[][] bricks = new Brick[12][14];

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (j == 6 && i == 5) {
                    bricks[i][j] = wb(i, j);
                }
                if (j == 6 && i == 6) {
                    bricks[i][j] = wb(i, j);
                }
            }
        }
        return bricks;
    }

    public static Brick wb(int i, int j) {
        return new Brick(BRICK_SEPARATION_X + ((BRICK_SEPARATION_X + Brick.WIDTH) * i), BRICK_SEPARATION_Y + ((BRICK_SEPARATION_Y + Brick.HEIGHT) * j), Color.white);
    }
}
