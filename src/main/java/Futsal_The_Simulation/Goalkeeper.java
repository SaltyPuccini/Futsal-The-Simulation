package Futsal_The_Simulation;

import java.util.ArrayList;
import java.util.Random;

public class Goalkeeper {

    private Teams myTeam;
    private int passingStat;
    private int goalkeepingStat;
    private int mySector;

    public Goalkeeper(Teams myTeam, int mySector, int passingStat, int goalkeepingStat) {
        this.myTeam = myTeam;
        this.mySector = mySector; //goalkeepers are invisible for other players. Their position is not marked on the field.
        this.passingStat = passingStat;
        this.goalkeepingStat = goalkeepingStat;
    }

    public boolean doIHaveToSaveAndIsItSuccessful(Ball ball) {
        Random randomGenerator = new Random();
        if (ball.getAmIshoot() && ball.getTeamOfTheBall() != myTeam) {
            int willISucceed = goalkeepingStat + randomGenerator.nextInt(100);
            return willISucceed < 120;
        }
        return false;
    }

    public int checkingFarTeammatesPosition(FieldGenerator generator) {
        ArrayList<Integer> arrayOfFriendlyPosition = new ArrayList<>();
        for (int i = 0; i < generator.getAllSectors().size(); i++) {
            checkerFar(generator, arrayOfFriendlyPosition, i);
        }
        return choseNearestSectorToPass(arrayOfFriendlyPosition);
    }


    public void operateOnTheBall(Ball ball, FieldGenerator field) {
        ball.setTeamOfTheBall(myTeam);
        ball.setAmIshoot(false);
        ball.setSectorOfTheBall(checkingFarTeammatesPosition(field));
    }


    public void save(Ball ball, FieldGenerator field, Scoreboard scoreboard) {
        if (doIHaveToSaveAndIsItSuccessful(ball)) {
            System.out.println("Nice shot. I lost the goal for team: " + myTeam);
            scoreboard.operateOnScoreboard(myTeam);
        }
        else{
            System.out.println("Too easy to save.");
        }
        operateOnTheBall(ball, field);
    }


    public int choseNearestSectorToPass(ArrayList<Integer> arrayOfFriendlyPosition) {
        int sectorNumber;
        if(myTeam ==Teams.AS_RIGHT)
            sectorNumber=arrayOfFriendlyPosition.get(arrayOfFriendlyPosition.size() - 1);
        else
            sectorNumber=arrayOfFriendlyPosition.get(0);
        System.out.println("I start from my penalty area. I shall pass to my teammate in sector " + (sectorNumber+1));
        return sectorNumber + 1;
    }


    public void checkerFar(FieldGenerator generator, ArrayList<Integer> arrayOfFriendlyPosition, int iterator) {
        Sector sectorWeAreChecking = generator.getAllSectors().get(iterator);
        switch (myTeam) {
            case FC_LEFT:
                if (sectorWeAreChecking.getIsPlayerLeftHere()) {
                    if (sectorWeAreChecking.getId() != mySector) {
                        arrayOfFriendlyPosition.add(iterator);
                    }
                }
                break;
            case AS_RIGHT:
                if (sectorWeAreChecking.getIsPlayerRightHere()) {
                    if (sectorWeAreChecking.getId() != mySector)
                        arrayOfFriendlyPosition.add(iterator);
                }
                break;
        }
    }


}
