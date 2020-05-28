package Futsal_The_Simulation;

import org.json.JSONArray;
import org.json.JSONException;

public class OtherPlayersAround {
    private Teams myTeam;
    private int mySector;
    Sectors mySectorThatWeCheck;
    JSONArray connectedSectors;
    int[] arrayOfConnectedSectors;
    boolean isPlayerRightHere;
    boolean isPlayerLeftHere;
    Sectors neighbourSectorToCheck;

    public void onWhomAmIOperating(Player player) {
        myTeam = player.getMyTeam();
        mySector = player.getMySector();
    }

    public void gettingInformationAboutConnectedSectors(FieldGenerator generator, Player player) {
        onWhomAmIOperating(player);
        mySectorThatWeCheck = generator.getAllSectors().get(mySector - 1);
        connectedSectors = mySectorThatWeCheck.getConnectedSectors();
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

    public int checkingForTeammates(FieldGenerator generator, Player player) throws JSONException {
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
        System.out.println("There's noone nearby - I think i shall move...");
        return 0;
    }
}
