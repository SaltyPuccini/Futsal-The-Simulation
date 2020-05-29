package Futsal_The_Simulation;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws JSONException, IOException {

        UserInputReader input = new UserInputReader();
        Scanner scan = new Scanner(System.in);
        FieldGenerator field = new FieldGenerator();
        field.loadSectors();
        ArrayList<Player> footballTeam = new ArrayList<>();
        Attacker attacker1 = new Attacker(Teams.FC_LEFT, 1);
        Attacker attacker2 = new Attacker(Teams.FC_LEFT, 17);
        Attacker attacker3 = new Attacker(Teams.AS_RIGHT, 8);
        Attacker attacker4 = new Attacker(Teams.AS_RIGHT, 30);
        footballTeam.add(attacker1);
        footballTeam.add(attacker2);
        footballTeam.add(attacker3);
        footballTeam.add(attacker4);
        input.askQuestionsAboutSimulationProperties(scan);
        field.giveStartingSectorsInformationAboutPlayersPosition(footballTeam, input.getSimulationProperties().get(1));
/*
        Midfielder midfielder = new Midfielder(Teams.AS_RIGHT, 14);
        Defender defender = new Defender(Teams.FC_LEFT, 8);
        Movement movement = new Movement();
        movement.movingPlayerToHisFinalDestination(field, attacker);
*/

        OtherPlayersOnThePitch search = new OtherPlayersOnThePitch();
        search.checkingForNearbyTeammates(field, attacker3);

/*
        System.out.println(input.getSimulationProperties());

        input.askUserForAttackerSpecifications(scan);
        TeamCreator team1 = new TeamCreator();
        team1.assignStatsToAttacker(attacker, input);
*/

        search.checkingFarTeammatesPosition(field, attacker3);


    }


}

