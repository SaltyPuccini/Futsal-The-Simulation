package Futsal_The_Simulation;

import java.util.Random;

public abstract class Player {
    private Teams myTeam;
    private int mySector;
    protected int passingStat;
    protected int shootingStat;
    protected int defendingStat;
    boolean amIOnTheBall;
    private int myNumber;

    Player(Teams myTeam, int mySector, boolean amIOnTheBall, int shootingStat, int passingStat, int defendingStat, int myNumber) {
        this.myTeam = myTeam;
        this.mySector = mySector;
        this.amIOnTheBall = amIOnTheBall;
        this.defendingStat = defendingStat;
        this.passingStat = passingStat;
        this.shootingStat = shootingStat;
        this.myNumber = myNumber;
    }

    public Teams getMyTeam() {
        return myTeam;
    }

    public int getMyNumber() {
        return myNumber;
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
            System.out.println("I'm on the ball.");
        } else {
            amIOnTheBall = false;
            System.out.println("I'm not on the ball.");
        }
    }

    public void decideIfInterceptionASRightSucceed(FieldGenerator field, Ball ball) {
        Random randomGenerator = new Random();
        if (ball.getSectorOfTheBall() == mySector) {
            int willISucceed = defendingStat + randomGenerator.nextInt(100);
            if (willISucceed > 100) {
                ball.setTeamOfTheBall(Teams.AS_RIGHT);
                System.out.println("I manadged to steal the ball. Interception succeeded");
                decideAmIOnTheBall(ball);
            }
        } else {
            System.out.println("Noone to challenge for the ball in the sector that I'm in.");
        }
    }

    public void decideIfInterceptionFCLeftSucceed(FieldGenerator field, Ball ball) {
        Random randomGenerator = new Random();
        if (ball.getSectorOfTheBall() == mySector) {
            int willISucceed = defendingStat + randomGenerator.nextInt(100);
            if (willISucceed > 100) {
                ball.setTeamOfTheBall(Teams.FC_LEFT);
                System.out.println("I manadged to steal the ball. Interception succeeded.");
                decideAmIOnTheBall(ball);
            }
        } else {
            System.out.println("Noone to challenge for the ball in the sector that I'm in.");
        }
    }

    public void interception(FieldGenerator field, Ball ball) {
        if (!amIOnTheBall && ball.getTeamOfTheBall() != myTeam) {
            switch (myTeam) {
                case FC_LEFT:
                    decideIfInterceptionFCLeftSucceed(field, ball);
                    break;
                case AS_RIGHT:
                    decideIfInterceptionASRightSucceed(field, ball);
                default:
                    ball.setAmIshoot(false); //jeœli przejêcie jest blokiem, pi³ka nie jest ju¿ "wystrzelona"
                    //jeœli nie jest zwyk³ym przejêciem, statement nic nie robi.
            }
        } else {
            System.out.println("My team already has the ball. No need to recover it.");
        }
    }

    public boolean checkIfImInPositionToShoot(FieldGenerator field) {
        switch (getMyTeam()) {
            case FC_LEFT:
                if (getMySector() > 20 && !field.getAllSectors().get(getMySector() - 1).getIsPlayerRightHere()) {
                    return true;
                }
                break;
            case AS_RIGHT:
                if (getMySector() < 11 && !field.getAllSectors().get(getMySector() - 1).getIsPlayerLeftHere()) {
                    return true;
                }
                break;
        }
        return false;
    }

    public void shot(Ball ball) {
        ball.setAmIshoot(true);
    }

    public boolean preparingToShoot(FieldGenerator field, Ball ball, Goalkeeper enemyGoalkeeper) {
        if (amIOnTheBall) {
            Random randomGenerator = new Random();
            if (checkIfImInPositionToShoot(field)) {
                System.out.println("All right. I'm in position to shoot. So, here we go...");
                int willISucceed = shootingStat + randomGenerator.nextInt(100);
                if (willISucceed > 100) {
                    System.out.println("It's a shot on goal.");
                    shot(ball);
                } else {
                    System.out.println("I missed.");
                    enemyGoalkeeper.operateOnTheBall(ball, field); //jak nie trafi, bramkarz od razu rozpoczyna grê przeciwników.
                }
                return true;
            } else {
                System.out.println("I'm in no position to shoot");
            }

        }
        return false; //jak zwróci true, to jest break w pêtli i zaczynamy od interwencji bramkarza.
    }
}