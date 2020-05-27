package Futsal_The_Simulation;

import org.json.JSONArray;

public class Sectors {

    private int id;
    private int capacity;
    private JSONArray connectedSectors;
    private boolean isPlayerRightHere;
    private boolean isPlayerLeftHere;


    Sectors(int id, int capacity, JSONArray connectedSectors, boolean isPlayerRightHere, boolean isPlayerLeftHere) {
        this.isPlayerRightHere = isPlayerRightHere;
        this.isPlayerLeftHere = isPlayerLeftHere;
        this.id = id;
        this.capacity = capacity;
        this.connectedSectors = connectedSectors;
    }


}
