package Futsal_The_Simulation;

public class Defender extends Player {
    private int defendingStat;

    Defender(Teams myTeam, int mySector, int passingStat, int defendingStat) {
        super(myTeam, mySector);
        this.passingStat=passingStat;
        this.defendingStat=defendingStat;
    }

    public void setStats(int passingStat, int defendingStat) {
        this.passingStat = passingStat;
        this.defendingStat = defendingStat;
    }
}
