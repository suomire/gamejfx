package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMapCore {
    private Cell[][] gameMap;
    private Player player1;
    private Player player2;

    public GameMapCore() {
        setGameMap();
    }

    public void setGameMap() {
        this.gameMap = new Cell[16][16];
        for (int i = 0; i < this.gameMap.length; i++) {
            for (int j = 0; j < this.gameMap[i].length; j++) {
                this.gameMap[i][j] = new Cell();
            }
        }
        player1 = new Player(1);
        player2 = new Player(2);
    }

    //конструктор для теста
    public GameMapCore(Cell[][] test) {
        this.gameMap = test;
    }

    //метод для хода. После всегда проверяется наличие выигрышной комбинации
    public boolean move(int r, int c, boolean player) { //cell[][]->boolean -- вернет продолжать игру или нет
        if (player) {
            this.gameMap[r][c] = new Cell(r, c, this.player1);
            this.player1.setStones(this.player1.getStones() - 1);
            return this.winCheck(player1);
        } else {
            this.gameMap[r][c] = new Cell(r, c, this.player2);
            this.player2.setStones(this.player2.getStones() - 1);
            return this.winCheck(player2);
        }
    }

    //метод для выделения колонки из игрового поля
    public Cell[] getCol(int iCol) {
        Cell[] subCol = new Cell[16];
        for (int i = 0; i < subCol.length; i++) {
            subCol[i] = gameMap[i][iCol];
        }
        return subCol;
    }

    //метод для преобразования листа объектов Cell в массив
    public Cell[] FromListToArray(List<Cell> list) {
        Cell[] temp = new Cell[list.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = list.get(i);
        }
        return temp;
    }

    //метод для получения главной диагонали или ей параллельных
    public Cell[] getDiag(int n, boolean j_on) {
        /**j_on - отвечает за местоположение диагонали, true - над главной (i+j < 15),
         *  false - под главной (i+j>15)
         *  n - номер диагонали считая от самоц большоц начиная с 0
         *  для n =0 значение j_on не играет значения
         */
        List<Cell> subDiag = new ArrayList<>();
        if (!j_on) {
            for (int i = n, j = 0; i < 16 & j < 16; i++, j++) {
                subDiag.add(gameMap[i][j]);
            }
        } else {
            for (int i = 0, j = n; i < 16 & j < 16; i++, j++) {
                subDiag.add(gameMap[i][j]);
            }
        }
        return FromListToArray(subDiag);
    }

    public boolean winCheck(Player player) {
        boolean score = false;
        Cell[] subArr;
        boolean turnJ = false;
        for (int i = 0; i < 16; i++) {
            subArr = Arrays.copyOfRange(gameMap[i], 0, 16); //строки анализ
            score = subarrayFinding(player.getNumber(), subArr);
            if (score) break;
            subArr = getCol(i); //столбцы анализ
            score = subarrayFinding(player.getNumber(), subArr);
            if (score) break;
        }
        if (score) return score;
        //анализ диагоналей
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 16; k++) {
                subArr = getDiag(k, turnJ);
                if (subArr.length > 4) {
                    score = subarrayFinding(player.getNumber(), subArr);
                }
                if (score) break;
            }
            if (score) break;
            turnJ = true;
        }
        turnJ = false;
        if (score) return score;
        //анализ побочных диагоналей
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 16; k++) {
                subArr = getSideDiag(k, turnJ);
                if (subArr.length < 4) break;
                score = subarrayFinding(player.getNumber(), subArr);
                if (score) break;
            }
            if (score) break;
            turnJ = true;
        }
        return score;
    }

    //метод для получения побочной диагонали и ей параллельных
    public Cell[] getSideDiag(int n, boolean j_on) {
        List<Cell> subDiag = new ArrayList<>();
        /**j_on - отвечает за местоположение диагонали, true - над побочной (i+j < 15),
         *  false - под побочной (i+j>15)
         *  n - номер диагонали считая от самоц большоц начиная с 0
         *  для n =0 значение j_on не играет значения
         */

        if (!j_on) {
            for (int i = 15, j = n; i > -1 + n & j < 16; i--, j++) {
                subDiag.add(gameMap[i][j]);
            }
        } else {
            for (int i = 15 - n, j = 0; i > -1 & j < 16 - n; i--, j++) {
                subDiag.add(gameMap[i][j]);
            }
        }
        return FromListToArray(subDiag);
    }

    //метод, который аннализирует подмассив и находит есть ли выиграшная комбинация
    public boolean subarrayFinding(int player, Cell[] cells_array) {
        int[] int_array = new int[cells_array.length];
        for (int i = 0; i < int_array.length; i++) {
            int_array[i] = cells_array[i].getPlayerNum().getNumber();
        }
        int[] def_array = {player, player, player, player, player};
        boolean found = false;
        boolean endReached = (def_array.length > int_array.length);
        int globalSearchIndex = 0;
        while (!(found || endReached)) {
            int localSearchIndex = 0;
            while (!found && def_array[localSearchIndex] == int_array[globalSearchIndex + localSearchIndex]) {
                localSearchIndex++;
                found = (localSearchIndex == def_array.length);
            }
            globalSearchIndex++;
            endReached = (globalSearchIndex == (int_array.length - def_array.length + 1));
        }
        return found;
    }

}
