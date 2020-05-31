package Futsal_The_Simulation;

import java.util.Random;

public class Attacker extends Player {

    Attacker(Teams myTeam, int mySector, boolean amIOnTheBall, int shootingStat, int passingStat, int defendingStat) {
        super(myTeam, mySector, amIOnTheBall, shootingStat, passingStat, defendingStat);
    }

    public void setStats(int shootingStat, int passingStat) {
        this.shootingStat = shootingStat;
        this.passingStat = passingStat;
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

    public void shoot(FieldGenerator field) {
        if (amIOnTheBall) {
            Random randomGenerator = new Random();
            if (checkIfImInPositionToShoot(field)){
                int willISucceed = shootingStat + randomGenerator.nextInt(100);
            }

        }
    }

}

