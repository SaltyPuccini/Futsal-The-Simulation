package Futsal_The_Simulation;

public class Attacker extends Player {
    private int shootingStat;
    private int passingStat;


    Attacker(Teams myTeam, int mySector) {

        super(myTeam, mySector);
    }

    public void setStats(int shootingStat, int passingStat) {
        this.shootingStat = shootingStat;
        this.passingStat = passingStat;
        System.out.println("My shooting stats are: " + shootingStat + " and my passing stats are " + passingStat);
    }
}

