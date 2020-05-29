package Futsal_The_Simulation;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws JSONException, IOException {

        UserInputReader input = new UserInputReader();
        Scanner scan = new Scanner(System.in);
        Ball ball = new Ball(1, Teams.FC_LEFT);
        Pass pass = new Pass();
        Movement movement = new Movement();
        FieldGenerator field = new FieldGenerator();
        OtherPlayersOnThePitch search = new OtherPlayersOnThePitch();
        field.loadSectors();
        ArrayList<Player> footballTeam = new ArrayList<>();
        Attacker attacker1 = new Attacker(Teams.FC_LEFT, 1, false);
        Attacker attacker2 = new Attacker(Teams.FC_LEFT, 3, false);
        Attacker attacker3 = new Attacker(Teams.FC_LEFT, 17, false);
        Attacker attacker4 = new Attacker(Teams.FC_LEFT, 30, false);
        footballTeam.add(attacker1);
        footballTeam.add(attacker2);
        footballTeam.add(attacker3);
        footballTeam.add(attacker4);

        input.askQuestionsAboutSimulationProperties(scan);
        field.giveStartingSectorsInformationAboutPlayersPosition(footballTeam, input.getSimulationProperties().get(1));

        int i = 0;
        while (i <input.getSimulationProperties().get(0)) {
            attacker1.decideAmIOnTheBall(ball);
            movement.movingPlayerToHisFinalDestination(field, attacker1, ball);
            pass.passTheBallIfPossible(field, attacker1, ball);
            attacker2.decideAmIOnTheBall(ball);
            movement.movingPlayerToHisFinalDestination(field, attacker2, ball);
            pass.passTheBallIfPossible(field, attacker2, ball);
            attacker3.decideAmIOnTheBall(ball);
            movement.movingPlayerToHisFinalDestination(field, attacker3, ball);
            pass.passTheBallIfPossible(field, attacker3, ball);
            attacker4.decideAmIOnTheBall(ball);
            movement.movingPlayerToHisFinalDestination(field, attacker4, ball);
            pass.passTheBallIfPossible(field, attacker4, ball);
            i++;
        }





/*
        input.askUserForAttackerSpecifications(scan);
        TeamCreator team1 = new TeamCreator();
        team1.assignStatsToAttacker(attacker, input);
*/

    }


}

