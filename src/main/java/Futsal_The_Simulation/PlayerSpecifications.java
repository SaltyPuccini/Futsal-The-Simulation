package Futsal_The_Simulation;

public class PlayerSpecifications {
    private int shootingStats;
    private int passingStats;
    private int defendingStats;
    private int whichSectorAmIIn;
    Teams myTeam;

    public PlayerSpecifications(int shootingStats, int passingStats, int defendingStats, Teams myTeam, int whichSectorAmIIn) {
        this.defendingStats = defendingStats;
        this.passingStats = passingStats;
        this.shootingStats = shootingStats;
        this.myTeam = myTeam;
        this.whichSectorAmIIn = whichSectorAmIIn;
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

    public int getWhichSectorAmIIn() {
        return whichSectorAmIIn;
    }

    public Teams getMyTeam() {
        return myTeam;
    }

}