package Futsal_The_Simulation;

public abstract class Player {
    Teams myTeam;
    int mySector;

    Player(Teams myTeam, int mySector) {
        this.myTeam=myTeam;
        this.mySector=mySector;
    }

    public char move() {

        return '0';
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
}
