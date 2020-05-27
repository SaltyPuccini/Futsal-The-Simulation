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

    void loadSectors() throws IOException, JSONException {
        String path = new String(Files.readAllBytes(Paths.get("src/main/resources/sektory.json")));
        JSONArray arr = new JSONArray(path);

        for(int i=0; i<arr.length(); i++)
        {
            JSONObject obj = arr.getJSONObject(i);
            int id = obj.getInt("id");
            int capacity=obj.getInt("capacity");
            JSONArray connectedSectors=obj.getJSONArray("connectedSectors");
            Sectors sector = new Sectors(id, capacity, connectedSectors);
            allSectors.add(sector);
        }
    }

}
