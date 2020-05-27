package Futsal_The_Simulation;

import org.json.JSONArray;

public class Sectors {

    int id;
    int capacity;
    JSONArray connectedSectors;

    Sectors(int id, int capacity, JSONArray connectedSectors){
        this.id=id;
        this.capacity=capacity;
        this.connectedSectors=connectedSectors;
    }


}
