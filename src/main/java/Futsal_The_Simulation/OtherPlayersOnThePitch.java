package Futsal_The_Simulation;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

/**
 * OtherPlayersOnThePitch is responsible for distributing information about players' positions.
 */
public class OtherPlayersOnThePitch {
    private Teams myTeam;
    private int mySector;
    private boolean isPlayerRightHere;
    private boolean isPlayerLeftHere;
    private Sector neighbourSectorToCheck;
    private JSONArray connectedSectors;
    private int[] arrayOfConnectedSectors;


    /**
     * Sets team and sector of currently checking player.
     * @param player - currently checking player.
     */
    private void onWhomAmIOperating(Player player) {
        myTeam = player.getMyTeam();
        mySector = player.getMySector();
    }

    private void gettingInformationAboutConnectedSectors(FieldGenerator generator, Player player) {
        onWhomAmIOperating(player);
        Sector playersSectorThatWeCheck = generator.getAllSectors().get(mySector - 1);
        connectedSectors = playersSectorThatWeCheck.getConnectedSectors();
    }

    private void fillingArrayOfConnectedSectors(FieldGenerator generator, Player player) throws JSONException {
        gettingInformationAboutConnectedSectors(generator, player);
        arrayOfConnectedSectors = new int[connectedSectors.length()]; //AS_Right checks from index 0, FC_Left index last.
        for (int i = 0; i < connectedSectors.length(); i++)
            arrayOfConnectedSectors[i] = connectedSectors.getInt(i);
    }

    private void searchForPlayersFCLeft(FieldGenerator generator, int iterator) {
        neighbourSectorToCheck = generator.getAllSectors().get(arrayOfConnectedSectors[iterator] - 1);
        isPlayerLeftHere = neighbourSectorToCheck.getIsPlayerLeftHere();
    }

    private void searchForPlayersASRight(FieldGenerator generator, int iterator) {
        neighbourSectorToCheck = generator.getAllSectors().get(arrayOfConnectedSectors[iterator] - 1);
        isPlayerRightHere = neighbourSectorToCheck.getIsPlayerRightHere();
    }

    public int checkingForNearbyTeammates(FieldGenerator generator, Player player) throws JSONException {
        fillingArrayOfConnectedSectors(generator, player);
        switch (myTeam) {
            case FC_LEFT:
                for (int i = connectedSectors.length() - 1; i >= 0; i--) {
                    searchForPlayersFCLeft(generator, i);
                    if (isPlayerLeftHere) {
//                        System.out.println("There's someone in sector " + arrayOfConnectedSectors[i]);
                        return arrayOfConnectedSectors[i];
                    }
                }
                break;
            case AS_RIGHT:
                for (int i = 0; i < connectedSectors.length(); i++) {
                    searchForPlayersASRight(generator, i);
                    if (isPlayerRightHere) {
//                        System.out.println("There's someone in sector " + arrayOfConnectedSectors[i]);
                        return arrayOfConnectedSectors[i];
                    }
                }
                break;
        }
//        System.out.println("There's noone nearby");
        return 0;
    }



    private int drawSectorAndPassItForward(ArrayList<Integer> arrayOfFriendlyPosition) {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(arrayOfFriendlyPosition.size());
        return arrayOfFriendlyPosition.get(randomNumber) + 1;
    }


    private void checkerFar(FieldGenerator generator, ArrayList<Integer> arrayOfFriendlyPosition, int iterator ){
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



    public int checkingFarTeammatesPosition(FieldGenerator generator, Player player) {
        onWhomAmIOperating(player);
        ArrayList<Integer> arrayOfFriendlyPosition = new ArrayList<>();
        for (int i = 0; i < generator.getAllSectors().size(); i++) {
           checkerFar(generator, arrayOfFriendlyPosition, i);
        }
        if (arrayOfFriendlyPosition.size() == 0) {
            return 0;
        }
        return drawSectorAndPassItForward(arrayOfFriendlyPosition);
    }

    public int positionOfRandomEnemy(ArrayList<Player> listOfEnemies, UserInputReader input) {
        Random randomGenerator = new Random();
        int whereToPass;
        int randomNumber = +randomGenerator.nextInt(input.getSimulationProperties().get(1));
        whereToPass = listOfEnemies.get(randomNumber).getMySector();
        return whereToPass;
    }


}
