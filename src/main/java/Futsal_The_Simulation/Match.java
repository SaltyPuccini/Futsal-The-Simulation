package Futsal_The_Simulation;

public class Match {

    private int goalsRight;
    private int goalsLeft;

    public Match(int goalsRight, int goalsLeft) {
        this.goalsRight = goalsRight;
        this.goalsLeft = goalsLeft;
    }

    public void setGoalsLeft(int goalsLeft) {
        this.goalsLeft = goalsLeft;
    }

    public void setGoalsRight(int goalsRight) {
        this.goalsRight = goalsRight;
    }

    public int getGoalsLeft() {
        return goalsLeft;
    }

    public int getGoalsRight() {
        return goalsRight;
    }
}
