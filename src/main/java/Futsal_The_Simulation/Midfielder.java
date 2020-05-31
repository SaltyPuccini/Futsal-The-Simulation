package Futsal_The_Simulation;

public class Midfielder extends Player {


    Midfielder(Teams myTeam, int mySector, boolean amIOnTheBall, int shootingStat, int passingStat, int defendingStat, int myNumber, Role myRole) {
        super(myTeam, mySector, amIOnTheBall, shootingStat, passingStat, defendingStat, myNumber, myRole) ;
    }

    public void setStats(int shootingStat, int passingStat, int defendingStat, int myNumber) {
        this.shootingStat = shootingStat;
        this.passingStat = passingStat;
        this.defendingStat = defendingStat;
        this. myNumber = myNumber;
        myRole=Role.MIDFIELDER;
    }

}
