package Futsal_The_Simulation;

public abstract class Player {
    private Teams myTeam;
    private int mySector;
    protected int passingStat;

    Player(Teams myTeam, int mySector) {
        this.myTeam=myTeam;
        this.mySector=mySector;
    }

    public boolean pass() {

        return true;
    }

    public boolean reception() {
        return true;
    }

    public Teams getMyTeam() {
        return myTeam;
    }

    public int getMySector(){
        return mySector;
    }

    public void setMySector(int mySector) {
        this.mySector = mySector;
    }
}
