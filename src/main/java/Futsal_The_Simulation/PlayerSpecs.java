package Futsal_The_Simulation;

public class PlayerSpecs {
    int shootingStats;
    int passingStats;
    int defendingStats;
    Teams myTeam;

    PlayerSpecs(int shootingStats, int passingStats, int defendingStats) {
        this.defendingStats = defendingStats;
        this.passingStats = passingStats;
        this.shootingStats = shootingStats;
        this.myTeam = myTeam;
    }

    public int getDefendingStats() {
        return defendingStats;
    }

    public int getPassingStats() {
        return passingStats;
    }

    public int getShootingStats() {
        return shootingStats;
    }

    public Teams getMyTeam() {
        return myTeam;
    }

}