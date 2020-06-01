package Futsal_The_Simulation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FieldGenerator {

    private final ArrayList<Sector> allSectors = new ArrayList<>();

    public void addLocalVariablesToGlobalField(int id, JSONArray connectedSectors, boolean isPlayerRightHere, boolean isPlayerLeftHere, String isWallNextToMe) {
        Sector sector = new Sector(id, connectedSectors, isPlayerRightHere, isPlayerLeftHere, isWallNextToMe);
        allSectors.add(sector);
    }

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
/* zaczynaj¹c symulacjê:
1. wczytujemy info z JSONA o boisku za pomoc¹ loadSectors;
2. pytamy o specyfikacje symulacji i tworzymy pi³karzy;
3. wczytujemy info o pozycjach pi³karzy do allSectors za pomoc¹ metody giveStartingSectorsInformationAboutPlayersPosition;
4. za ka¿dym razem, gdy player siê porusza:
    - giveInformationPlayerLeaving usuwa info o miejscu pobytu gracza z allSectors
    - giveInformationPlayerAppearing podaje info o miejscu nowego pobytu gracza do allSectors
 */