package Futsal_The_Simulation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Sectors {

    int index;
    ArrayList<Integer> arrayOfSectorsID = new ArrayList();
    void loadSectors() throws IOException, JSONException {

        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/sektory.json")));
        JSONArray arr = new JSONArray(content);
        JSONObject obj = arr.getJSONObject(0);
        int id = obj.getInt("id");
        System.out.println(id);

        for(int i=0; i<arr.length(); i++)
        {
            this.arrayOfSectorsID.add(id);
        }
    }
}
