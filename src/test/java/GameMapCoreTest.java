import org.junit.Test;
import sample.Cell;
import sample.GameMapCore;
import sample.Player;

import static org.junit.Assert.*;

public class GameMapCoreTest {
    public Cell[][] makeNotNullArray(Cell[][] tmp) {
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                tmp[i][j] = new Cell(i, j, new Player(0));
            }
        }
        return tmp;
    }

    @Test
    public void winCheck() {
        Player player = new Player(2);
        Cell[][] test = new Cell[16][16];
        test = makeNotNullArray(test);
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
        test = makeNotNullArray(test);
        test[2][3] = new Cell(2, 3, player);
        test[3][3] = new Cell(3, 3, player);
        test[4][3] = new Cell(4, 3, player);
        test[5][3] = new Cell(5, 3, player);
        test[6][3] = new Cell(6, 3, player);
        gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
        //test for diag1
        //test = new Cell[16][16];
        test = makeNotNullArray(test);
        test[10][0] = new Cell(10, 0, player);
        test[11][1] = new Cell(11, 1, player);
        test[12][2] = new Cell(12, 2, player);
        test[13][3] = new Cell(13, 3, player);
        test[14][4] = new Cell(14, 4, player);
        gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
        //test for diag2
        test = new Cell[16][16];
        test = makeNotNullArray(test);
        test[0][10] = new Cell(0, 10, player);
        test[1][11] = new Cell(1, 11, player);
        test[2][12] = new Cell(2, 12, player);
        test[3][13] = new Cell(3, 13, player);
        test[4][14] = new Cell(4, 14, player);
        gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
        //test for sidediag1
        test = new Cell[16][16];
        test = makeNotNullArray(test);
        test[10][14] = new Cell(10, 14, player);
        test[11][13] = new Cell(10, 13, player);
        test[12][12] = new Cell(12, 12, player);
        test[13][11] = new Cell(13, 11, player);
        test[14][10] = new Cell(14, 10, player);
        gm = new GameMapCore(test);
        assertTrue(gm.winCheck(player));
        //test for sidediag2
        test = new Cell[16][16];
        test = makeNotNullArray(test);
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
        //test for column
        Cell[][] test = new Cell[16][16];
        Player player = new Player(2);
        test = makeNotNullArray(test);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (j == 3) test[i][j] = new Cell(i, j, player);
            }
        }
        GameMapCore gm = new GameMapCore(test);
        Cell[] returnedSubCol = gm.getCol(3);
        for (Cell cell : returnedSubCol) {
            assertEquals(2, cell.getPlayerNum().getNumber());
        }
    }

    @Test
    public void getDiag() {
        Cell[][] test = new Cell[16][16];
        Player player = new Player(2);
        test = makeNotNullArray(test);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i == j) test[i][j] = new Cell(i, j, player);
            }
        }
        GameMapCore gm = new GameMapCore(test);
        Cell[] returnedSubCol = gm.getDiag(0, false);
        for (Cell cell : returnedSubCol) {
            assertEquals(2, cell.getPlayerNum().getNumber());
        }
    }

    @Test
    public void getSideDiag() {
        Cell[][] test = new Cell[16][16];
        Player player = new Player(2);
        test = makeNotNullArray(test);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i + j == 15) test[i][j] = new Cell(i, j, player);
            }
        }
        GameMapCore gm = new GameMapCore(test);
        Cell[] returnedSubCol = gm.getSideDiag(0, true);
        for (Cell cell : returnedSubCol) {
            assertEquals(2, cell.getPlayerNum().getNumber());
        }
    }

    @Test
    public void subarrayFinding() {
        Cell[] test = new Cell[16];
        Player specialPlayer = new Player(0);
        Player checkPlayer = new Player(2);
        for (int i = 0; i < test.length; i++) {
            test[i] = new Cell(0, 0, specialPlayer);
        }
        test[2] = new Cell(0, 0, checkPlayer);
        test[3] = new Cell(0, 0, checkPlayer);
        test[4] = new Cell(0, 0, checkPlayer);
        test[5] = new Cell(0, 0, checkPlayer);
        test[6] = new Cell(0, 0, checkPlayer);

        int pl = 2;
        GameMapCore gm = new GameMapCore();
        assertTrue(gm.subarrayFinding(pl, test));
    }
}