package Futsal_The_Simulation;

import java.util.Random;

public abstract class Player {
    private Teams myTeam;
    private int mySector;
    protected int passingStat;
    protected int shootingStat;
    protected int defendingStat;
    boolean amIOnTheBall;

    Player(Teams myTeam, int mySector, boolean amIOnTheBall, int shootingStat, int passingStat, int defendingStat) {
        this.myTeam = myTeam;
        this.mySector = mySector;
        this.amIOnTheBall = amIOnTheBall;
        this.defendingStat = defendingStat;
        this.passingStat = passingStat;
        this.shootingStat = shootingStat;
    }

    public Teams getMyTeam() {
        return myTeam;
    }

    public int getMySector() {
        return mySector;
    }

    public void setMySector(int mySector) {
        this.mySector = mySector;
    }

    public boolean getAmIOnTheBall() {
        return amIOnTheBall;
    }

    public void decideAmIOnTheBall(Ball ball) {
        if (myTeam == ball.getTeamOfTheBall() && mySector == ball.getSectorOfTheBall()) {
            amIOnTheBall = true;
            System.out.println("I'm on the ball");
        } else {
            amIOnTheBall = false;
            System.out.println("I'm not on the ball");
        }
    }

    public void decideIfInterceptionASRightSucceed(FieldGenerator field, Ball ball){
        Random randomGenerator = new Random();
        if (field.getAllSectors().get(mySector-1).getIsPlayerLeftHere()){
            int willISucceed = defendingStat + randomGenerator.nextInt(100);
            if(willISucceed>100){
                ball.setTeamOfTheBall(Teams.AS_RIGHT);
                decideAmIOnTheBall(ball);
                System.out.println("Interception succeeded");
            }
        }
    }

    public void decideIfInterceptionFCLeftSucceed(FieldGenerator field, Ball ball){
        Random randomGenerator = new Random();
        if (field.getAllSectors().get(mySector-1).getIsPlayerRightHere()){
            int willISucceed = defendingStat + randomGenerator.nextInt(100);
            if(willISucceed>100){
                ball.setTeamOfTheBall(Teams.FC_LEFT);
                decideAmIOnTheBall(ball);
            }
        }
    }

    public void interception(FieldGenerator field, Ball ball) {
        if(!amIOnTheBall)
        switch (myTeam) {
            case FC_LEFT:
                decideIfInterceptionFCLeftSucceed(field, ball);
                break;
            case AS_RIGHT:
                decideIfInterceptionASRightSucceed(field, ball);

        }
    }
}
