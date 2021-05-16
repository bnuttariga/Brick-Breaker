
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pnapasson
 */
public class Level4 extends Level{
        LevelFactory level;
    private final static int BRICK_SEPARATION_X = 5;
    private final static int BRICK_SEPARATION_Y = 4;
    public Level4() {
        level = new LevelFactory();
    }

    public Brick[][] getLevelArray() {
        Brick[][] bricks = new Brick[12][14];

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length;) {
                int A = 10, B = 11, C = 12, D = 13, E = 14;
                return new Brick[][]{
                        {null, null, null, null, null, bb(5, 0), bb(6, 0), bb(7, 0), bb(8, 0), bb(9, 0), null, null, null, null, null, null, null},
                        {null, null, null, bb(3, 1), bb(4, 1), yb(5, 1), yb(6, 1), yb(7, 1), yb(8, 1), yb(9, 1), bb(A, 1), bb(B,1), null},
                        {null, null, bb(2, 2), yb(3, 2), yb(4, 2), yb(5, 2), yb(6, 2), yb(7, 2), yb(8, 2), yb(9, 2), yb(A, 2), yb(B, 2), bb(C, 2),null},
                        {null, bb(1, 3), yb(2, 3), yb(3, 3), yb(4, 3), yb(5, 3), yb(6, 3), yb(7, 3), yb(8, 3), yb(9, 3), yb(A, 3), yb(B, 3), yb(C, 3),bb(D, 3)},
                        {null, bb(1, 4), yb(2, 4), yb(3, 4), yb(4, 4), yb(5, 4), yb(6, 4), yb(7, 4), yb(8, 4), yb(9, 4), yb(A, 4), yb(B, 4),yb(C,4),bb(D, 4)},
                        {bb(0,5), bb(1, 5), bb(2, 5), bb(3, 5), bb(4, 5), bb(5, 5), bb(6, 5), bb(7, 5), bb(8, 5), bb(9, 5), bb(A, 5), bb(B, 5), bb(C, 5),bb(D, 5),bb(E, 5)},
                        {bb(0,6), yb(1, 6), bb(2, 6), wb(3,6), wb(4, 6), bb(5, 6), bb(6, 6), bb(7, 6), bb(8, 6), wb(9, 6), wb(A, 6), bb(B, 6),bb(C, 6),yb(D, 6),bb(E, 6)},
                        {bb(0,7), yb(1, 7), bb(2, 7), wb(3,7), bb(4, 7), bb(5, 7), bb(6, 7), yb(7, 7), bb(8, 7), wb(9, 7), bb(A, 7), bb(B, 7),bb(C, 7),yb(D, 7),bb(E, 7)},
                        {bb(0,8), yb(1, 8), yb(2, 8), bb(3, 8), bb(4, 8), bb(5, 8), yb(6, 8), yb(7, 8), yb(8, 8), bb(9, 8), bb(A, 8), bb(B, 8),yb(C, 8),yb(D, 8),bb(E, 8)},
                        {bb(0,9), yb(1, 9), yb(2, 9), yb(3, 9), yb(4, 9), yb(5, 9), yb(6, 9), yb(7, 9), yb(8, 9), yb(9, 9), yb(A, 9), yb(B, 9), yb(C,9), yb(D,9), bb(E, 9)},
                        {null, bb(1,A) , yb(2, A), yb(3, A), yb(4, A), yb(5, A), yb(6, A), yb(7, A), yb(8, A), yb(9, A), bb(A, A), yb(B, A), yb(C, A), bb(D, A)},
                        {null, null, bb(2, B), yb(3, B), yb(4, B), yb(5, B), yb(6, B), bb(7, B), bb(8, B), bb(9, B), yb(A, B), yb(B, B),bb(C, B)},
                        {null, null, bb(2, C), yb(3, C), yb(4, C), yb(5, C), yb(6, C), yb(7, C), yb(8, C), yb(9, C), yb(9, C), yb(A,C),yb(B,C),bb(C,C)},
                        {null, null, null, bb(3, D), bb(4, D), yb(5, D), yb(6, D), yb(7, D), yb(8, D), bb(9, D), bb(A, D), bb(B, D)},
                        {null, null, null, null, null, bb(5, E), bb(6, E), bb(7, E), bb(8, E), bb(9, E)},
                        {null, null, null, null, null, null, null, null, null, null, null, null,null, null, null, null, null}};
                }
            }
        

        return bricks;
    }
    
    public Brick yb(int i, int j) {
        return new Brick(BRICK_SEPARATION_X+65 + ((BRICK_SEPARATION_X-17 + Brick.WIDTH) * i), BRICK_SEPARATION_Y + ((BRICK_SEPARATION_Y+2 + Brick.HEIGHT) * j), Color.yellow);
    }

    public Brick bb(int i, int j) {
        return new Brick(BRICK_SEPARATION_X+65 + ((BRICK_SEPARATION_X-17 + Brick.WIDTH) * i), BRICK_SEPARATION_Y + ((BRICK_SEPARATION_Y+2 + Brick.HEIGHT) * j), Color.black);

    }
    public static Brick wb(int i, int j) {
        return new Brick(BRICK_SEPARATION_X+65 + ((BRICK_SEPARATION_X-17 + Brick.WIDTH) * i), BRICK_SEPARATION_Y + ((BRICK_SEPARATION_Y+2 + Brick.HEIGHT) * j), Color.white);
    }
}
