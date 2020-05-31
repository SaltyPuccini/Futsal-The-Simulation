package Futsal_The_Simulation;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

public class OtherPlayersOnThePitch {
    private Teams myTeam;
    private int mySector;
    private boolean isPlayerRightHere;
    private boolean isPlayerLeftHere;
    private Sector playersSectorThatWeCheck;
    private Sector neighbourSectorToCheck;
    private JSONArray connectedSectors;
    private int[] arrayOfConnectedSectors;



    public void onWhomAmIOperating(Player player) {
        myTeam = player.getMyTeam();
        mySector = player.getMySector();
    }

    public void gettingInformationAboutConnectedSectors(FieldGenerator generator, Player player) {
        onWhomAmIOperating(player);
        playersSectorThatWeCheck = generator.getAllSectors().get(mySector - 1);
        connectedSectors = playersSectorThatWeCheck.getConnectedSectors();
    }

    public void fillingArrayOfConnectedSectors(FieldGenerator generator, Player player) throws JSONException {
        gettingInformationAboutConnectedSectors(generator, player);
        arrayOfConnectedSectors = new int[connectedSectors.length()]; //dru¿yna prawa sprawdza od indeksu 0, a lewa od najwuy¿szego
        for (int i = 0; i < connectedSectors.length(); i++)
            arrayOfConnectedSectors[i] = connectedSectors.getInt(i);
    }

    public void searchTeamLeft(FieldGenerator generator, int iterator) {
        neighbourSectorToCheck = generator.getAllSectors().get(arrayOfConnectedSectors[iterator] - 1);
        isPlayerLeftHere = neighbourSectorToCheck.getIsPlayerLeftHere();
    }

    public void searchTeamRight(FieldGenerator generator, int iterator) {
        neighbourSectorToCheck = generator.getAllSectors().get(arrayOfConnectedSectors[iterator] - 1);
        isPlayerRightHere = neighbourSectorToCheck.getIsPlayerRightHere();
    }

    public int checkingForNearbyTeammates(FieldGenerator generator, Player player) throws JSONException {
        fillingArrayOfConnectedSectors(generator, player);
        switch (myTeam) {
            case FC_LEFT:
                for (int i = connectedSectors.length() - 1; i >= 0; i--) {
                    searchTeamLeft(generator, i);
                    if (isPlayerLeftHere) {
                        System.out.println("There's someone in sector " + arrayOfConnectedSectors[i]);
                        return arrayOfConnectedSectors[i];
                    }
                }
                break;
            case AS_RIGHT:
                for (int i = 0; i < connectedSectors.length(); i++) {
                    searchTeamRight(generator, i);
                    if (isPlayerRightHere) {
                        System.out.println("There's someone in sector " + arrayOfConnectedSectors[i]);
                        return arrayOfConnectedSectors[i];
                    }
                }
                break;
        }
        System.out.println("There's noone nearby");
        return 0;
    }



    public int drawSectorAndPassItForward(ArrayList<Integer> arrayOfFriendlyPosition) {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(arrayOfFriendlyPosition.size());
        System.out.println("Hell yeah there's my teammate in sector number " + (arrayOfFriendlyPosition.get(randomNumber) + 1));
        return arrayOfFriendlyPosition.get(randomNumber) + 1;
    }


    public int checkingFarTeammatesPosition(FieldGenerator generator, Player player) {
        onWhomAmIOperating(player);
        ArrayList<Integer> arrayOfFriendlyPosition = new ArrayList<>();
        for (int i = 0; i < generator.getAllSectors().size(); i++) {
            Sector sectorWeAreChecking = generator.getAllSectors().get(i);
            switch (myTeam) {
                case FC_LEFT:
                    if (sectorWeAreChecking.getIsPlayerLeftHere()) {
                        if (sectorWeAreChecking.getId() != mySector) {
                            arrayOfFriendlyPosition.add(i);
                        }
                    }
                    break;
                case AS_RIGHT:
                    if (sectorWeAreChecking.getIsPlayerRightHere()) {
                        if (sectorWeAreChecking.getId() != mySector)
                            arrayOfFriendlyPosition.add(i);
                    }
                    break;
            }
        }
        if (arrayOfFriendlyPosition.size() == 0) {
            System.out.println("There's noone to pass on the whole field!!! :<");
            return 0;
        }
        return drawSectorAndPassItForward(arrayOfFriendlyPosition);
    }

}
