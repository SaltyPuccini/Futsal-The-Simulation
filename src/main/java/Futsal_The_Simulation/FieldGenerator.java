package Futsal_The_Simulation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FieldGenerator {

    ArrayList <Sectors> allSectors = new ArrayList<>();

    void addLocalVariablesToGlobalField(int id, int capacity, JSONArray connectedSectors, boolean isPlayerRightHere, boolean isPlayerLeftHere){
        Sectors sector = new Sectors(id, capacity, connectedSectors, isPlayerRightHere, isPlayerLeftHere);
        allSectors.add(sector);
    }

    void loadSectors() throws IOException, JSONException {
        String path = new String(Files.readAllBytes(Paths.get("src/main/resources/sektory.json")));
        JSONArray jsonArray = new JSONArray(path);
        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("id");
            int capacity=jsonObject.getInt("capacity");
            boolean isPlayerRightHere=jsonObject.getBoolean("isPlayerRightHere");
            boolean isPlayerLeftHere=jsonObject.getBoolean("isPlayerLeftHere");
            JSONArray connectedSectors=jsonObject.getJSONArray("connectedSectors");
            addLocalVariablesToGlobalField(id, capacity, connectedSectors, isPlayerRightHere, isPlayerLeftHere);
        }
    }
}
