package Futsal_The_Simulation;

public class Midfielder extends Player {
    private int shootingStat;
    private int passingStat;
    private int defendingStat;

    Midfielder(Teams myTeam, int mySector) {

        super(myTeam, mySector);
    }

    public void setStats(int shootingStat, int passingStat, int defendingStat) {
        this.shootingStat = shootingStat;
        this.passingStat = passingStat;
        this.defendingStat = defendingStat;
    }

}
