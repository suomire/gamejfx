import org.junit.Test;
import sample.Cell;
import sample.GameMapCore;
import sample.Player;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
/*
public class GameMapCoreTest {
    public Cell[][] NotNull(Cell[][] tmp) {
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                tmp[i][j] = new Cell(i, j, new Player(0));
            }
        }
        return tmp;
    }

    @Test
    public void move() {
        Player player = new Player(1);
        Cell[][] returnArray;
        Cell[][] testArray = new Cell[16][16];
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray[i].length; j++) {
                if ((i == 7) & (j == 7)) {
                    testArray[i][j] = new Cell(i, j, player);
                }
            }
        }
        GameMapCore gm = new GameMapCore();
        returnArray = gm.move(7, 7, false);
        for (int i = 0; i < 16; i++) {
            assertArrayEquals(testArray[i], returnArray[i]);
        }
    }

    //java.lang.AssertionError внезапно, если по частям (комментировать уже протестированные куски), то все работает (?)
    @Test
    public void winCheck() {
        Player player = new Player(2);
        Cell[][] test = new Cell[16][16];
        test = NotNull(test);
        //test for row
        test[2][3] = new Cell(2, 3, player);
        test[2][4] = new Cell(2, 4, player);
        test[2][5] = new Cell(2, 5, player);
        test[2][6] = new Cell(2, 6, player);
        test[2][7] = new Cell(2, 7, player);
        GameMapCore gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
        //test for column
        test = new Cell[16][16];
        test = NotNull(test);
        test[2][3] = new Cell(2, 3, player);
        test[3][3] = new Cell(3, 3, player);
        test[4][3] = new Cell(4, 3, player);
        test[5][3] = new Cell(5, 3, player);
        test[6][3] = new Cell(6, 3, player);
        gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
        //test for diag1
        //test = new Cell[16][16];
        test = NotNull(test);
        test[10][0] = new Cell(10, 0, player);
        test[11][1] = new Cell(11, 1, player);
        test[12][2] = new Cell(12, 2, player);
        test[13][3] = new Cell(13, 3, player);
        test[14][4] = new Cell(14, 4, player);
        gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
        //test for diag2
        test = new Cell[16][16];
        test = NotNull(test);
        test[0][10] = new Cell(0, 10, player);
        test[1][11] = new Cell(1, 11, player);
        test[2][12] = new Cell(2, 12, player);
        test[3][13] = new Cell(3, 13, player);
        test[4][14] = new Cell(4, 14, player);
        gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
        //test for sidediag1
        test = new Cell[16][16];
        test = NotNull(test);
        test[10][14] = new Cell(10, 14, player);
        test[11][13] = new Cell(10, 13, player);
        test[12][12] = new Cell(12, 12, player);
        test[13][11] = new Cell(13, 11, player);
        test[14][10] = new Cell(14, 10, player);
        gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
        //test for sidediag2
        test = new Cell[16][16];
        test = NotNull(test);
        test[0][4] = new Cell(0, 4, player);
        test[1][3] = new Cell(1, 3, player);
        test[2][2] = new Cell(2, 2, player);
        test[3][1] = new Cell(3, 1, player);
        test[4][0] = new Cell(4, 0, player);
        gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
    }

    @Test
    public void getCol() {
        GameMapCore gm = new GameMapCore();
        int[][] testedA;
        int[] retA;
        int[] checkA = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
        };
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                testedA = gm.move(i, j, i + j);
            }
        }
        retA = gm.getCol(1);
        assertArrayEquals(checkA, retA);
    }

    @Test
    public void getDiag() {
        GameMapCore gm = new GameMapCore();
        int[][] testedA;
        int[] retA;
        int[] checkA = new int[15];
        for (int i = 0; i < 15; i++) {
            checkA[i] = checkA[i] + i * 2;
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                testedA = gm.move(i, j, i + j);
            }
        }
        retA = gm.getDiag(0, false);
        assertArrayEquals(checkA, retA);
        retA = gm.getDiag(0, true);
        assertArrayEquals(checkA, retA);
        checkA = new int[]{10, 12, 14, 16, 18};
        retA = gm.getDiag(10, true);
        assertArrayEquals(checkA, retA);
        retA = gm.getDiag(10, false);
        assertArrayEquals(checkA, retA);
    }

    @Test
    public void getSideDiag() {
        GameMapCore gm = new GameMapCore();
        int[][] testedA;
        int[] retA;
        int[] checkA = new int[15];
        for (int i = 0; i < 15; i++) {
            checkA[i] = -1;
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                testedA = gm.move(i, j, i + j);
                if (i + j == 14) testedA = gm.move(i, j, -1);
            }
        }
        retA = gm.getSideDiag(0, false);
        assertArrayEquals(checkA, retA);
        checkA = new int[]{24, 24, 24, 24, 24};
        retA = gm.getSideDiag(10, false);
        assertArrayEquals(checkA, retA);
        checkA = new int[]{4, 4, 4, 4, 4};
        retA = gm.getSideDiag(10, true);
        assertArrayEquals(checkA, retA);
    }

    @Test
    public void subarrayFinding() {
        int[] test = {0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0};
        int pl = 2;
        GameMapCore gm = new GameMapCore();
        boolean t = gm.subarrayFinding(pl, test);
        assertTrue(t == true);
    }
}
*/