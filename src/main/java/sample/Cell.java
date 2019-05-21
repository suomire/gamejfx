package sample;

public class Cell {
    private int rowCoord;
    private int colCoord;
    private Player playerNum;

    public void setRowCoord(int rowCoord) {
        this.rowCoord = rowCoord;
    }

    public void setColCoord(int colCoord) {
        this.colCoord = colCoord;
    }

    public void setPlayerNum(Player playerNum) {
        this.playerNum = playerNum;
    }

    public int getRowCoord() {
        return rowCoord;
    }

    public int getColCoord() {
        return colCoord;
    }

    public Player getPlayerNum() {
        return playerNum;
    }

    public Cell(int row, int col, Player player) {
        this.rowCoord = row;
        this.colCoord = col;
        this.playerNum = player;
    }

    public Cell() {
        this.rowCoord = 0;
        this.colCoord = 0;
        this.playerNum = new Player(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cell)) {
            return false;
        }
        Cell c = (Cell) obj;
        if (!(c.getRowCoord() == this.getColCoord())) {
            return false;
        }
        if (!(c.getRowCoord() == this.getRowCoord())) {
            return false;
        }
        if (!(c.getPlayerNum() == this.getPlayerNum())) {
            return false;
        }
        return true;
    }
}
