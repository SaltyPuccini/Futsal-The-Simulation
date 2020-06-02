package Futsal_The_Simulation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * FieldGenerator is responsible for generating field consisting of sectors and operating on them.
 */
public class FieldGenerator {

    private final ArrayList<Sector> allSectors = new ArrayList<>();

    /**
     * Fills ArrayList with values.
     * @param id - id of sector
     * @param connectedSectors - sectors connected to the sector
     * @param isPlayerRightHere - check is player from AS_Right at this sector
     * @param isPlayerLeftHere - check is player from FC_Left at this sector
     * @param isWallNextToMe - checks if there is wall next to the sector
     */
    private void addLocalVariablesToGlobalField(int id, JSONArray connectedSectors, boolean isPlayerRightHere, boolean isPlayerLeftHere, String isWallNextToMe) {
        Sector sector = new Sector(id, connectedSectors, isPlayerRightHere, isPlayerLeftHere, isWallNextToMe);
        allSectors.add(sector);
    }

    /**
     * Loads information from JSON to ArrayList allSectors.
     * @throws IOException
     * @throws JSONException
     */
    public void loadSectors() throws IOException, JSONException {
        String path = new String(Files.readAllBytes(Paths.get("src/main/resources/sektory.json")));
        JSONArray jsonArray = new JSONArray(path);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("id");
            int capacity = jsonObject.getInt("capacity");
            boolean isPlayerRightHere = jsonObject.getBoolean("isPlayerRightHere");
            boolean isPlayerLeftHere = jsonObject.getBoolean("isPlayerLeftHere");
            JSONArray connectedSectors = jsonObject.getJSONArray("connectedSectors");
            String isWallNextToMe = jsonObject.getString("isWallNextToMe");
            addLocalVariablesToGlobalField(id, connectedSectors, isPlayerRightHere, isPlayerLeftHere, isWallNextToMe);
        }
    }

    public ArrayList<Sector> getAllSectors() {
        return allSectors;
    }

    public void giveInformationPlayerLeaving(Player player) {
        if (player.getMyTeam() == Teams.FC_LEFT) {
            allSectors.get(player.getMySector() - 1).setPlayerLeftHere(false);
        }
        if (player.getMyTeam() == Teams.AS_RIGHT) {
            allSectors.get(player.getMySector() - 1).setPlayerRightHere(false);
        }
    }

    public void giveInformationPlayerAppearing(Player player) {
        if (player.getMyTeam() == Teams.FC_LEFT) {
            allSectors.get(player.getMySector() - 1).setPlayerLeftHere(true);
        }
        if (player.getMyTeam() == Teams.AS_RIGHT) {
            allSectors.get(player.getMySector() - 1).setPlayerRightHere(true);
        }
    }

    /**
     * Gives information about FC_Left's players positions.
     * @param player - ArrayList of players from FC_Left.
     * @param sizeOfTeam
     */
    public void giveStartingSectorsInformationLeft(ArrayList<Player> player, int sizeOfTeam) {
        for (int i = 0; i < sizeOfTeam; i++) {
            for (int k = 0; k < 30; k++) {
                if (player.get(i).getMySector() == getAllSectors().get(k).getId()) {
                        getAllSectors().get(k).setPlayerLeftHere(true);
                        //System.out.println("I set field number " + (k + 1) + "  with player left");
                    System.out.println("FCLeft's player number " +player.get(i).getMyNumber() + " sets up in sector number " + (k + 1));
                }
            }
        }
    }

    /**
     * Gives information about AS_Right's players positions.
     * @param player - ArrayList of players from AS_Right.
     * @param sizeOfTeam
     */
    public void giveStartingSectorsInformationRight(ArrayList<Player> player, int sizeOfTeam) {
        for (int i = 0; i < sizeOfTeam; i++) {
            for (int k = 0; k < 30; k++) {
                if (player.get(i).getMySector() == getAllSectors().get(k).getId()) {
                    getAllSectors().get(k).setPlayerRightHere(true);
                    System.out.println("ASRight's player number " +player.get(i).getMyNumber() + " sets up in sector number " + (k + 1));
                }
            }
        }
    }

}