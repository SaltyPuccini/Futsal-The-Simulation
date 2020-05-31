package Futsal_The_Simulation;

public class Defender extends Player {
    private int defendingStat;

    Defender(Teams myTeam, int mySector, boolean amIOnTheBall, int shootingStat, int passingStat, int defendingStat, int myNumber) {
        super(myTeam, mySector, amIOnTheBall, shootingStat, passingStat, defendingStat, myNumber) ;
    }

    public void setStats(int passingStat, int defendingStat) {
        this.passingStat = passingStat;
        this.defendingStat = defendingStat;
    }
}
