package Futsal_The_Simulation;

public abstract class Player {

    private int whichSectorAmIIn;
    Teams myTeam;

    public char move(){
    }

    public boolean pass(){
        return true;
    }

    public boolean reception(){
        return true;
    }

    public int getWhichSectorAmIIn() {
        return whichSectorAmIIn;
    }

    public Teams getMyTeam() {
        return myTeam;
    }


}
