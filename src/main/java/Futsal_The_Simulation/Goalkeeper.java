package Futsal_The_Simulation;

import java.util.ArrayList;
import java.util.Random;

public class Goalkeeper {

    private final Teams myTeam;
    private final int goalkeepingStat;
    private final int mySector;
    private int intervention = 0;
    private int successfulIntervention = 0;

    public Goalkeeper(Teams myTeam, int mySector, int goalkeepingStat) {
        this.myTeam = myTeam;
        this.mySector = mySector; //goalkeepers are invisible for other players. Their position is not marked on the field.
        this.goalkeepingStat = goalkeepingStat;
    }

    public boolean doIHaveToSaveAndIsItSuccessful(Ball ball) {
        Random randomGenerator = new Random();
        if (ball.getAmIshoot() && ball.getTeamOfTheBall() != myTeam) {
            intervention++;
            int willISucceed = randomGenerator.nextInt(100) + 1;
            if (willISucceed > goalkeepingStat) {
                return true;
            } else {
                successfulIntervention++;
            }
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
            System.out.println(myTeam + "'s goalkeeper conceded a goal.");
            scoreboard.operateOnScoreboard(myTeam);
        } else {
            if(ball.getAmIshoot())System.out.println(myTeam + "'s goalkeeper saved incoming shot.");
        }
        operateOnTheBall(ball, field);
    }


    public int choseNearestSectorToPass(ArrayList<Integer> arrayOfFriendlyPosition) {
        int sectorNumber;
        if (myTeam == Teams.AS_RIGHT)
            sectorNumber = arrayOfFriendlyPosition.get(arrayOfFriendlyPosition.size() - 1);
        else
            sectorNumber = arrayOfFriendlyPosition.get(0);
        System.out.println(myTeam + "'s goalkeeper passes the ball to " + (sectorNumber + 1));
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

    public int getIntervention() {
        return intervention;
    }

    public int getSuccessfulIntervention() {
        return successfulIntervention;
    }

    public Teams getMyTeam() {
        return myTeam;
    }
}
