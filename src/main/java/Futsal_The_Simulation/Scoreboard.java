package Futsal_The_Simulation;

public class Scoreboard {
    private int goalsRight;
    private int goalsLeft;

    public Scoreboard(int goalsRight, int goalsLeft) {
        this.goalsRight = goalsRight;
        this.goalsLeft = goalsLeft;
    }

    public void operateOnScoreboard(Teams team) {
        switch (team) {
            case FC_LEFT:
                goalsRight=goalsRight + 1;
                break;
            case AS_RIGHT:
               goalsLeft = goalsLeft + 1;
                break;
        }
    }

    public int getGoalsRight() {
        return goalsRight;
    }

    public int getGoalsLeft() {
        return goalsLeft;
    }
}
