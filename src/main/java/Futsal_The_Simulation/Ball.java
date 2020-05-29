package Futsal_The_Simulation;

public class Ball {
    int sectorOfTheBall;
    Teams teamOfTheBall;

    Ball (int sectorOfTheBall, Teams teamOfTheBall){
        this.sectorOfTheBall = sectorOfTheBall;
        this.teamOfTheBall = teamOfTheBall;
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
}
