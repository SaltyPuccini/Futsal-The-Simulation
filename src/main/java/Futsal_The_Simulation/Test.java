package Futsal_The_Simulation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws JSONException, IOException {

        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/sektory.json")));
        JSONArray arr = new JSONArray(content);
        JSONObject obj = arr.getJSONObject(0);
        int id = obj.getInt("id");
        System.out.println(id);
    }
}
