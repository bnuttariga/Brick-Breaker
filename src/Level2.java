import java.awt.Color;

public class Level2 extends Level {
    LevelFactory level;
    private final static int BRICK_SEPARATION_X = 5;
    private final static int BRICK_SEPARATION_Y = 4;
    private int i;
    private int j;
    public Level2() {
        level = new LevelFactory();
        i=0; j=0;
    }

    public Brick[][] getLevelArray() {
        Brick[][] bricks = new Brick[12][14];

        for (i=0; i < bricks.length; i++) {
            for (j = 0; j < bricks[i].length; j++) {
                int A = 10, B = 11, C = 12, D = 13, E = 14, F = 15, J = 16;
                return new Brick[][]{
                    {null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, bb(2, 1), bb(3, 1), bb(4, 1), null, null, null, bb(8, 1), bb(9, 1), bb(10, 1), null},
                    {null, null, bb(2, 2), bb(3, 2), bb(4, 2), bb(5, 2), null, bb(7, 2), bb(8, 2), bb(9, 2), bb(10, 2), null},
                    {null, null, bb(2, 3), rb(3, 3), rb(4, 3), rb(5, 3), bb(6, 3), rb(7, 3), rb(8, 3), rb(9, 3), bb(10, 3), null},
                    {null, null, bb(2, 4), rb(3, 4), wb(4, 4), rb(5, 4), rb(6, 4), rb(7, 4), wb(8, 4), rb(9, 4), bb(10, 4), null},
                    {null, null, bb(2, 5), rb(3, 5), wb(4, 5), wb(5, 5), wb(6, 5), wb(7, 5), wb(8, 5), rb(9, 5), bb(10, 5), null},
                    {null, null, bb(2, 6), rb(3, 6), wb(4, 6), wb(5, 6), wb(6, 6), wb(7, 6), wb(8, 6), rb(9, 6), bb(10, 6), null},
                    {null, null, bb(2, 7), rb(3, 7), wb(4, 7), wb(5, 7), wb(6, 7), wb(7, 7), rb(8, 7), rb(9, 7), bb(10, 7), null},
                    {null, null, bb(2, 8), rb(3, 8), rb(4, 8), wb(5, 8), wb(6, 8), wb(7, 8), rb(8, 8), rb(9, 8), bb(10, 8), null},
                    {null, null, bb(2, 9), rb(3, 9), rb(4, 9), wb(5, 9), wb(6, 9), rb(7, 9), rb(8, 9), rb(9, 9), bb(10, 9), null},
                    {null, null, bb(2, A), rb(3, A), rb(4, A), rb(5, A), wb(6, A), rb(7, A), rb(8, A), rb(9, A), bb(10, A), null},
                    {null, null, bb(2, B), rb(3, B), rb(4, B), rb(5, B), rb(6, B), rb(7, B), rb(8, B), bb(9, B), bb(10, B), null},
                    {null, null, null, bb(3, C), rb(4, C), rb(5, C), rb(6, C), rb(7, C), bb(8, C), bb(9, C), null, null},
                    {null, null, null, null, bb(4, D), rb(5, D), rb(6, D), rb(7, D), bb(8, D), null, null, null},
                    {null, null, null, null, null, bb(5, E), rb(6, E), bb(7, E), null, null, null, null},
                    {null, null, null, null, null, null, bb(5, F), bb(6, F), null, null, null, null},
                    {null, null, null, null, null, null, bb(6, J), null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null},};
            }
        }
        return bricks;
    }
    
    public Brick rb(int i, int j) {
        return new Brick(BRICK_SEPARATION_X + ((BRICK_SEPARATION_X + Brick.WIDTH) * i), BRICK_SEPARATION_Y + ((BRICK_SEPARATION_Y + Brick.HEIGHT) * j), new Color(157,224,173));
    }

    public Brick bb(int i, int j) {
        return new Brick(BRICK_SEPARATION_X + ((BRICK_SEPARATION_X + Brick.WIDTH) * i), BRICK_SEPARATION_Y + ((BRICK_SEPARATION_Y + Brick.HEIGHT) * j), new Color(157,182,224));

    }
    public static Brick wb(int i, int j) {
        return new Brick(BRICK_SEPARATION_X + ((BRICK_SEPARATION_X + Brick.WIDTH) * i), BRICK_SEPARATION_Y + ((BRICK_SEPARATION_Y + Brick.HEIGHT) * j), Color.white);
    }
}
