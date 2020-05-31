package Futsal_The_Simulation;

public class Defender extends Player {

    Defender(Teams myTeam, int mySector, boolean amIOnTheBall, int shootingStat, int passingStat, int defendingStat) {
        super(myTeam, mySector, amIOnTheBall, shootingStat, passingStat, defendingStat);
    }

    public void setStats(int passingStat, int defendingStat) {
        this.passingStat = passingStat;
        this.defendingStat = defendingStat;
    }
}
