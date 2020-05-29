package Futsal_The_Simulation;

import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws JSONException, IOException {

        UserInputReader input;
        Scanner scan;
        scan = new Scanner(System.in);

        FieldGenerator field = new FieldGenerator();
        field.loadSectors();

        Attacker attacker = new Attacker(Teams.FC_LEFT, 27);
        Midfielder midfielder = new Midfielder(Teams.AS_RIGHT, 14);
        Defender defender = new Defender(Teams.FC_LEFT, 8);

        Movement movement = new Movement();
        movement.movingPlayerToHisFinalDestination(field, attacker);

        OtherPlayersAround search = new OtherPlayersAround();
        search.checkingForTeammates(field, attacker);

        input = new UserInputReader();
        input.askQuestionsAboutSimulationProperties(scan);

        System.out.println(input.getSimulationProperties());
        int i=input.getSimulationProperties().get(0);
        System.out.println(i);

        input.askUserForAttackerSpecifications(scan);
        TeamCreator team1 = new TeamCreator();
        team1.assignStatsToAttacker(attacker, input);
        team1.assignStatsToMidfielder(midfielder, input);
        team1.assignStatsToDefender(defender, input);


    }


}

