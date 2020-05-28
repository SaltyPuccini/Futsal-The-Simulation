package Futsal_The_Simulation;

import org.json.JSONException;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws JSONException, IOException {

        FieldGenerator field = new FieldGenerator();
        field.loadSectors();

        Attacker attacker = new Attacker(Teams.AS_RIGHT, 10);

        OtherPlayersAround search = new OtherPlayersAround();
        search.checkingForTeammates(field, attacker);


    }
}
