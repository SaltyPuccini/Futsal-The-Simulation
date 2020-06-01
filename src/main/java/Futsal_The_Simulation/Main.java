package Futsal_The_Simulation;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, JSONException {
        Date nowDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String fileName = format.format(nowDate) + ".txt";
        File file = new File(fileName);

        if (file.createNewFile()) {
            System.out.println("file.txt File Created in Project root directory");
        } else System.out.println("File file.txt already exists in the project root directory");

        PrintWriter save = new PrintWriter(fileName);

        SaveLogToTextFile saver = new SaveLogToTextFile();
        UserInputReader input = new UserInputReader();
        Scanner scan = new Scanner(System.in);
        Ball ball = new Ball(2, Teams.FC_LEFT, false);
        Pass pass = new Pass();
        Movement movement = new Movement();
        FieldGenerator field = new FieldGenerator();
        Match match = new Match();
        TeamCreator create = new TeamCreator();
        ArrayList<Player> teamFCLeft;
        ArrayList<Player> teamASRight;
        Scoreboard scoreboard = new Scoreboard(0, 0);

        field.loadSectors();
        input.askQuestionsAboutSimulationProperties(scan);

        input.rolesInTeam(Teams.FC_LEFT, scan);
        teamFCLeft = create.createATeam(input, Teams.FC_LEFT, scan);
        create.giveSectorsToPlayers(input, teamFCLeft);
        field.giveStartingSectorsInformationLeft(teamFCLeft, input.getSimulationProperties().get(1));
        Goalkeeper goalkeeperFCLeft = create.createAGoalkeeperLeft(input, scan);

        input.rolesInTeam(Teams.AS_RIGHT, scan);
        teamASRight = create.createATeam(input, Teams.AS_RIGHT, scan);
        create.giveSectorsToPlayers(input, teamASRight);
        field.giveStartingSectorsInformationRight(teamASRight, input.getSimulationProperties().get(1));
        Goalkeeper goalkeeperASRight = create.createAGoalkeeperRight(input, scan);

        input.whoStarts(scan, ball, teamFCLeft.get(0), teamASRight.get(0));

        match.runSimulation(input, ball, teamFCLeft, teamASRight, pass, movement, field, goalkeeperASRight, goalkeeperFCLeft, scoreboard);

        saver.finalSaver(save, scoreboard, input, teamFCLeft, teamASRight, pass);
        saver.goalkeepersInterventionsToFile(save, goalkeeperFCLeft);
        saver.goalkeepersInterventionsToFile(save, goalkeeperASRight);
        save.close();

    }

}
