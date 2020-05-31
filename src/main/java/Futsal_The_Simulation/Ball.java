package Futsal_The_Simulation;

public class Ball {
   private int sectorOfTheBall;
   private Teams teamOfTheBall;
   private boolean amIshoot;

    Ball (int sectorOfTheBall, Teams teamOfTheBall, boolean amIshoot){
        this.sectorOfTheBall = sectorOfTheBall;
        this.teamOfTheBall = teamOfTheBall;
        this.amIshoot = amIshoot;
    }

    public void setSectorOfTheBall(int sectorOfTheBall) {
        this.sectorOfTheBall = sectorOfTheBall;
    }

    public void setTeamOfTheBall(Teams teamOfTheBall) {
        this.teamOfTheBall = teamOfTheBall;
    }

    public int getSectorOfTheBall() {
        return sectorOfTheBall;
    }

    public Teams getTeamOfTheBall() {
        return teamOfTheBall;
    }

    public boolean getAmIshoot() {
        return amIshoot;
    }

    public void setAmIshoot(boolean amIshoot) {
        this.amIshoot = amIshoot;
    }
}
