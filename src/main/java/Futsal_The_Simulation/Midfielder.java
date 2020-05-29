package Futsal_The_Simulation;

public class Midfielder extends Player {
    private int shootingStat;
    private int defendingStat;

    Midfielder(Teams myTeam, int mySector, boolean inPossesion){
        super(myTeam, mySector, inPossesion);
    }

    public void setStats(int shootingStat, int passingStat, int defendingStat) {
        this.shootingStat = shootingStat;
        this.passingStat = passingStat;
        this.defendingStat = defendingStat;
    }

}
