import java.awt.Color;

public class Level3 extends Level {
    LevelFactory level;
    private final static int BRICK_SEPARATION_X = 5;
    private final static int BRICK_SEPARATION_Y = 4;
    private int i;
    private int j;
    public Level3() {
        i=0;j=0;
    }
        public Brick[][] getLevelArray() {
        Brick[][] bricks = new Brick[12][14];

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                int A = 10, B = 11, C = 12, D = 13, E = 14, F = 15, G = 16, H = 17;
                return new Brick[][]{
                        {null, null, null, null, bb(4, 0), bb(5, 0), bb(6, 0), null, null, null, bb(A, 0), bb(B, 0), bb(C, 0), null, null, null, null},
                        {null, null, null, null, bb(4, 1), pb(5, 1), bb(6, 1), null, null, null, bb(A, 1), pb(B,1), bb(C, 1)},
                        {null, null, null, bb(3, 2), bb(4, 2), bb(5, 2), bb(6, 2), null, null, null, bb(A, 2), bb(B, 2), bb(C, 2),bb(D, 2)},
                        {null, null, null, bb(3, 3), pb(4, 3), pb(5, 3), bb(6, 3), null, null, null, bb(A, 3), pb(B, 3), pb(C, 3),bb(D, 3)},
                        {bb(0,4), bb(1, 4), bb(2, 4), bb(3, 4), pb(4, 4), bb(5, 4), pb(6, 4), bb(7, 4), bb(8, 4), bb(9, 4), pb(A, 4), bb(B, 4),pb(C,4),bb(D, 4),bb(E, 4),bb(F, 4),bb(G, 4)},
                        {bb(0,5), pb(1, 5), bb(2, 5), null, bb(4, 5), pb(5, 5), pb(6, 5), bb(7, 5), bb(8, 5), bb(9, 5), pb(A, 5), pb(B, 5), bb(C, 5),null,bb(E, 5),pb(F, 5),bb(G, 5),},
                        {bb(0,6), pb(1, 6), bb(2, 6), null, bb(4, 6), pb(5, 6), pb(6, 6), pb(7, 6), pb(8, 6), pb(9, 6), pb(A, 6), pb(B, 6),bb(C, 6),null,bb(E, 6),pb(F, 6),bb(G, 6)},
                        {bb(0,7), pb(1, 7), bb(2, 7), null, bb(4, 7), pb(5, 7), bb(6, 7), pb(7, 7), pb(8, 7), pb(9, 7), bb(A, 7), pb(B, 7),bb(C, 7),null,bb(E, 7),pb(F, 7),bb(G, 7)},
                        {bb(0,8), pb(1, 8), bb(2, 8), null, bb(4, 8), pb(5, 8), bb(6, 8), pb(7, 8), pb(8, 8), pb(9, 8), bb(A, 8), pb(B, 8),bb(C, 8),null,bb(E, 8),pb(F, 8),bb(G, 8)},
                        {bb(0,9), pb(1, 9), pb(2, 9), bb(3, 9), bb(4, 9), pb(5, 9), bb(6, 9), pb(7, 9), pb(8, 9), pb(9, 9), bb(A, 9), pb(B, 9), bb(C,9), bb(D,9), pb(E, 9), pb(F, 9), bb(G, 9)},
                        {bb(0,A), bb(1,A) , pb(2, A), pb(3, A), pb(4, A), pb(5, A), pb(6, A), pb(7, A), pb(8, A), pb(9, A), pb(A, A), pb(B, A), pb(C, A), pb(D, A),pb(E,A),bb(F, A),bb(G,A)},
                        {null, null, bb(2, B), pb(3, B), pb(4, B), pb(5, B), pb(6, B), bb(7, B), bb(8, B), bb(9, B), pb(A, B), pb(B, B),pb(C, B),pb(D, B),bb(E, B)},
                        {null, null, null, bb(3, C), bb(4, C), pb(5, C), pb(6, C), bb(7, C), null, bb(9, C), bb(9, C), bb(9, C), pb(A,C),pb(B,C),bb(C,C),bb(D,C)},
                        {null, null, bb(2, D), bb(3, D), bb(4, D), bb(5, D), bb(6, D), bb(7, D), null, bb(9, D), bb(A, D), bb(B, D),bb(C, D), bb(D, D),bb(E, D),null,null,null},
                        {null, null, bb(2, E), pb(3, E), bb(4, E), null, null, null, null, null, null, null,bb(C, E), pb(D, E), bb(E, E), null, null},
                        {null, null, bb(2, F), bb(3, F), null, null, null, null, null, null, null, null,null, bb(D, F), bb(E, F), null, null},
                        {null, null, null, null, null, null, null, null, null, null, null, null,null, null, null, null, null}};
                }
            }
        

        return bricks;
    }

    public Brick pb(int i, int j) {
        return new Brick(BRICK_SEPARATION_X+22 + ((BRICK_SEPARATION_X-15 + Brick.WIDTH) * i), BRICK_SEPARATION_Y + ((BRICK_SEPARATION_Y+2 + Brick.HEIGHT) * j), new Color(112,48,160));
    }

    public Brick bb(int i, int j) {
        return new Brick(BRICK_SEPARATION_X+22 + ((BRICK_SEPARATION_X-15+ Brick.WIDTH) * i), BRICK_SEPARATION_Y + ((BRICK_SEPARATION_Y+2 + Brick.HEIGHT) * j), Color.black);

    }
}

