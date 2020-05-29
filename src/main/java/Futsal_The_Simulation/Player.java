package Futsal_The_Simulation;

public abstract class Player {
    private Teams myTeam;
    private int mySector;
    protected int passingStat;
    boolean amIOnTheBall;

    Player(Teams myTeam, int mySector, boolean amIOnTheBall) {
        this.myTeam = myTeam;
        this.mySector = mySector;
        this.amIOnTheBall = amIOnTheBall;
    }

    public boolean reception() {
        return true;
    }

    public Teams getMyTeam() {
        return myTeam;
    }

    public int getMySector() {
        return mySector;
    }

    public void setMySector(int mySector) {
        this.mySector = mySector;
    }

    public boolean getAmIOnTheBall() {
        return amIOnTheBall;
    }

    public void decideAmIOnTheBall(Ball ball) {
        if (myTeam == ball.getTeamOfTheBall() && mySector == ball.getSectorOfTheBall()) {
            amIOnTheBall = true;
            System.out.println("I'm on the ball");
        } else {
            amIOnTheBall = false;
            System.out.println("I'm not on the ball");
        }
    }
}
