package Futsal_The_Simulation;

public class Defender extends Player {
    private int defendingStat;

    Defender(Teams myTeam, int mySector) {
        super(myTeam, mySector);
    }

    public void setStats(int passingStat, int defendingStat) {
        this.passingStat = passingStat;
        this.defendingStat = defendingStat;
    }
}
