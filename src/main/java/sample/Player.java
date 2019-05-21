package sample;

public class Player {
    private int stones;
    private int number = 0;

    public void setNumber(int number) {
        this.number = number;
    }

    public Player() {
        this.stones = 0;
        this.number = 0;
    }

    public Player(int num) {
        this.stones = 50;
        this.number = num;
    }

    public int afterMove() {
        this.stones = this.stones - 1;
        return this.stones;
    }

    public int getNumber() {
        return this.number;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public int getStones() {
        return stones;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        Player c = (Player) obj;
        if (!(c.getNumber() == this.getNumber())) {
            return false;
        }
        return true;
    }
}
