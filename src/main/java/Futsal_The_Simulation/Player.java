package Futsal_The_Simulation;

import java.util.Random;

public abstract class Player {
    protected Teams myTeam;
    protected Role myRole;
    private int mySector;
    protected int passingStat;
    protected int shootingStat;
    protected int defendingStat;
    private boolean amIOnTheBall;
    protected int myNumber;

    Player(Teams myTeam, int mySector, boolean amIOnTheBall, int shootingStat, int passingStat, int defendingStat, int myNumber, Role myRole) {
        this.myTeam = myTeam;
        this.mySector = mySector;
        this.amIOnTheBall = amIOnTheBall;
        this.defendingStat = defendingStat;
        this.passingStat = passingStat;
        this.shootingStat = shootingStat;
        this.myNumber = myNumber;
        this.myRole = myRole;
    }

    public Role getMyRole() {
        return myRole;
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
            System.out.println("Player number " + myNumber + " from " + myTeam + " is on the ball in sector " + mySector + ".");
        } else {
            amIOnTheBall = false;
//            System.out.println("I'm not on the ball.");
        }
    }

    public void decideIfInterceptionASRightSucceed(Ball ball) {
        Random randomGenerator = new Random();
        if (ball.getSectorOfTheBall() == mySector) {
            int willISucceed = randomGenerator.nextInt(100)+1;
            if (willISucceed < defendingStat) {
                interceptionSuccessful++;
                ball.setTeamOfTheBall(Teams.AS_RIGHT);
                System.out.println("Player number " + myNumber + " from " + myTeam + " managed to steal the ball in sector " + mySector + ".");
                decideAmIOnTheBall(ball);
            }
            interception++;
        }
    }


    public void decideIfInterceptionFCLeftSucceed(Ball ball) {
        Random randomGenerator = new Random();
        if (ball.getSectorOfTheBall() == mySector) {
            int willISucceed = randomGenerator.nextInt(100)+1;
            if (willISucceed < defendingStat) {
                interceptionSuccessful++;
                ball.setTeamOfTheBall(Teams.FC_LEFT);
                System.out.println("Player number " + myNumber + " from " + myTeam + " managed to steal the ball in sector " + mySector + ".");
                decideAmIOnTheBall(ball);
            }
            interception++;
        }
    }

    public void interception(Ball ball) {
        if (!amIOnTheBall && ball.getTeamOfTheBall() != myTeam) {
            switch (myTeam) {
                case FC_LEFT:
                    decideIfInterceptionFCLeftSucceed(ball);
                    break;
                case AS_RIGHT:
                    decideIfInterceptionASRightSucceed(ball);
                default:
                    ball.setAmIshoot(false); //jeœli przejêcie jest blokiem, pi³ka nie jest ju¿ "wystrzelona"
                    //jeœli nie jest zwyk³ym przejêciem, statement nic nie robi.
            }
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
                shot++;
                int willISucceed = randomGenerator.nextInt(100)+1;
                if (willISucceed < shootingStat ) {
                    shotOnTarget++;
                    System.out.println("Player number " + myNumber + " from " + myTeam + " shoots on target.");
                    shot(ball);
                } else {
                    System.out.println("Player number " + myNumber + " from " + myTeam + " misses a shot.");
                    enemyGoalkeeper.operateOnTheBall(ball, field); //jak nie trafi, bramkarz od razu rozpoczyna grê przeciwników.
                }
                return true;
            }
        }
        return false; //jak zwróci true, to jest break w pêtli i zaczynamy od interwencji bramkarza.
    }

    int shotOnTarget = 0;
    int shot = 0;
    int interceptionSuccessful = 0;
    int interception = 0;

    public int getInterception() {
        return interception;
    }

    public int getInterceptionSuccessful() {
        return interceptionSuccessful;
    }

    public int getShot() {
        return shot;
    }

    public int getShotOnTarget() {
        return shotOnTarget;
    }

    public int getPassingStat() {
        return passingStat;
    }

    public int getDefendingStat() {
        return defendingStat;
    }

    public int getShootingStat() {
        return shootingStat;
    }
}
