package Futsal_The_Simulation;

public class Attacker extends Player {
    private int shootingStat;

    Attacker(Teams myTeam, int mySector, int passingStat, int shootingStat) {
        super(myTeam, mySector);
        this.passingStat=passingStat;
        this.shootingStat=shootingStat;
    }

    public void setStats(int shootingStat, int passingStat) {
        this.shootingStat = shootingStat;
        this.passingStat = passingStat;
        System.out.println("My shooting stats are: " + shootingStat + " and my passing stats are " + passingStat);
    }
}

