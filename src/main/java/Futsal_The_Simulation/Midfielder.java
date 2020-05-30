package Futsal_The_Simulation;

public class Midfielder extends Player {


    Midfielder(Teams myTeam, int mySector, boolean amIOnTheBall, int shootingStat, int passingStat, int defendingStat) {
        super(myTeam, mySector, amIOnTheBall, shootingStat, passingStat, defendingStat);
    }

    public void setStats(int shootingStat, int passingStat, int defendingStat) {
        this.shootingStat = shootingStat;
        this.passingStat = passingStat;
        this.defendingStat = defendingStat;
    }

}
