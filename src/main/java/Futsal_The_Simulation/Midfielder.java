package Futsal_The_Simulation;

public class Midfielder extends Player {
    private int shootingStat;
    private int defendingStat;

    Midfielder(Teams myTeam, int mySector, int passingStat, int shootingStat, int defendingStat) {
        super(myTeam, mySector);
        this.passingStat=passingStat;
        this.shootingStat=shootingStat;
        this.defendingStat=defendingStat;
    }

    public void setStats(int shootingStat, int passingStat, int defendingStat) {
        this.shootingStat = shootingStat;
        this.passingStat = passingStat;
        this.defendingStat = defendingStat;
    }

}
