package Futsal_The_Simulation;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, JSONException {
        UserInputReader input = new UserInputReader();
        Scanner scan = new Scanner(System.in);
        Ball ball = new Ball(2, Teams.FC_LEFT, false);
        Pass pass = new Pass();
        Movement movement = new Movement();
        FieldGenerator field = new FieldGenerator();
        OtherPlayersOnThePitch search = new OtherPlayersOnThePitch();
        Match match = new Match();
        TeamCreator create = new TeamCreator();
        ArrayList<Player> teamFCLeft;
        ArrayList<Player> teamASRight;
        Scoreboard scoreboard = new Scoreboard(0, 0);

        field.loadSectors();
        input.askQuestionsAboutSimulationProperties(scan);

        input.rolesInTeam(Teams.FC_LEFT, scan);
        teamFCLeft = create.createATeam(input, Teams.FC_LEFT, scan);
        Goalkeeper goalkeeperFCLeft=create.createAGoalkeeperLeft(input, scan);

        input.rolesInTeam(Teams.AS_RIGHT, scan);
        teamASRight = create.createATeam(input, Teams.AS_RIGHT, scan);
        Goalkeeper goalkeeperASRight=create.createAGoalkeeperRight(input, scan);

        field.giveStartingSectorsInformationLeft(teamFCLeft, input.getSimulationProperties().get(1));
        field.giveStartingSectorsInformationRight(teamASRight, input.getSimulationProperties().get(1));

        match.runSimulation(input, ball, teamFCLeft, teamASRight, pass, movement, field, goalkeeperASRight, goalkeeperFCLeft, scoreboard);



    }

}
