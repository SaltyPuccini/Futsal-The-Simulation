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
        Attacker attacker1 = new Attacker(Teams.FC_LEFT, 1, false, 0, 0, 0);
        Attacker attacker2 = new Attacker(Teams.FC_LEFT, 25, false, 0, 0, 0);
        Attacker attacker3 = new Attacker(Teams.AS_RIGHT, 2, false, 0, 0, 100);
        Attacker attacker4 = new Attacker(Teams.AS_RIGHT, 24, false, 0, 0, 100);
        footballTeam.add(attacker1);
        footballTeam.add(attacker2);
        footballTeam.add(attacker3);
        footballTeam.add(attacker4);

        input.askQuestionsAboutSimulationProperties(scan);
        field.giveStartingSectorsInformationAboutPlayersPosition(footballTeam, input.getSimulationProperties().get(1));

        for (int i = 0; i<input.getSimulationProperties().get(0);i++) {
            attacker1.decideAmIOnTheBall(ball);
            movement.movingPlayerToHisFinalDestination(field, attacker1, ball);
            attacker3.interception(field, ball);
            attacker3.decideAmIOnTheBall(ball);
            movement.movingPlayerToHisFinalDestination(field, attacker3, ball);
            pass.passTheBallIfPossible(field, attacker3, ball);
            attacker2.decideAmIOnTheBall(ball);
            movement.movingPlayerToHisFinalDestination(field, attacker2, ball);
            pass.passTheBallIfPossible(field, attacker2, ball);
            attacker2.interception(field, ball);
            attacker4.decideAmIOnTheBall(ball);
            movement.movingPlayerToHisFinalDestination(field, attacker4, ball);
            pass.passTheBallIfPossible(field, attacker4, ball);
            attacker4.interception(field, ball);
        }





/*
        input.askUserForAttackerSpecifications(scan);
        TeamCreator team1 = new TeamCreator();
        team1.assignStatsToAttacker(attacker, input);
*/

    }


}

