package Futsal_The_Simulation;

import org.json.JSONArray;

public class Sector {

    private final int id;
    private final int capacity;
    private final JSONArray connectedSectors;
    private boolean isPlayerRightHere;
    private boolean isPlayerLeftHere;
    private final String isWallNextToMe;


    Sector(int id, int capacity, JSONArray connectedSectors, boolean isPlayerRightHere, boolean isPlayerLeftHere, String isWallNextToMe) {
        this.isPlayerRightHere = isPlayerRightHere;
        this.isPlayerLeftHere = isPlayerLeftHere;
        this.id = id;
        this.capacity = capacity;
        this.connectedSectors = connectedSectors;
        this.isWallNextToMe = isWallNextToMe;
    }

    public JSONArray getConnectedSectors() {
        return connectedSectors;
    }

    public boolean getIsPlayerLeftHere() {
        return isPlayerLeftHere;
    }

    public boolean getIsPlayerRightHere() {
        return isPlayerRightHere;
    }

    public String getIsWallNextToMe() {
        return isWallNextToMe;
    }

    public int getId() {
        return id;
    }

    public void setPlayerLeftHere(boolean playerLeftHere) {
        isPlayerLeftHere = playerLeftHere;
    }

    public void setPlayerRightHere(boolean playerRightHere) {
        isPlayerRightHere = playerRightHere;
    }

}
