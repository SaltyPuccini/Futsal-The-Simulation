package Futsal_The_Simulation;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws JSONException, IOException {

        UserInputReader input = new UserInputReader();
        Scanner scan = new Scanner(System.in);
        Ball ball = new Ball(2, Teams.FC_LEFT, false);
        Pass pass = new Pass();
        Movement movement = new Movement();
        FieldGenerator field = new FieldGenerator();
        OtherPlayersOnThePitch search = new OtherPlayersOnThePitch();
        Match match = new Match(0, 0);
        field.loadSectors();
        input.askQuestionsAboutSimulationProperties(scan);
        /*
        input.askUserForAttackerSpecifications(scan);
        TeamCreator team1 = new TeamCreator();
        team1.assignStatsToAttacker(attacker, input);
        */


        ArrayList<Player> teamFCLEFT = new ArrayList<>();
        Attacker attacker1Left = new Attacker(Teams.FC_LEFT, 23, false, 90, 40, 0, 9);
        Midfielder midfielder1Left = new Midfielder(Teams.FC_LEFT, 14, false, 50, 60, 80, 6);
        Midfielder midfielder2Left = new Midfielder(Teams.FC_LEFT, 17, false, 80, 80, 20, 10);
        Defender defender1Left = new Defender(Teams.FC_LEFT, 2, false, 0, 40, 90, 5);
        Defender defender2Left = new Defender(Teams.FC_LEFT, 9, false, 0, 30, 100, 4);
        teamFCLEFT.add(defender1Left);
        teamFCLEFT.add(defender2Left);
        teamFCLEFT.add(midfielder1Left);
        teamFCLEFT.add(midfielder2Left);
        teamFCLEFT.add(attacker1Left);
        Goalkeeper goalkeeperFCLeft = new Goalkeeper(Teams.FC_LEFT, 28, 60, 65);

        field.giveStartingSectorsInformationLeft(teamFCLEFT, input.getSimulationProperties().get(1));

        ArrayList<Player> teamASRIGHT = new ArrayList<>();
        Attacker attacker1Right = new Attacker(Teams.AS_RIGHT, 7, false, 110, 40, 0, 9);
        Midfielder midfielder1Right = new Midfielder(Teams.AS_RIGHT, 17, false, 50, 60, 80, 6);
        Midfielder midfielder2Right = new Midfielder(Teams.AS_RIGHT, 15, false, 80, 80, 20, 10);
        Defender defender1Right = new Defender(Teams.AS_RIGHT, 27, false, 0, 40, 90, 5);
        Defender defender2Right = new Defender(Teams.AS_RIGHT, 24, false, 0, 30, 100, 4);
        teamASRIGHT.add(defender1Right);
        teamASRIGHT.add(defender2Right);
        teamASRIGHT.add(midfielder1Right);
        teamASRIGHT.add(midfielder2Right);
        teamASRIGHT.add(attacker1Right);
        Goalkeeper goalkeeperASRight = new Goalkeeper(Teams.AS_RIGHT, 28, 60, 80);

        field.giveStartingSectorsInformationRight(teamASRIGHT, input.getSimulationProperties().get(1));

        for (int time = 0; time < input.getSimulationProperties().get(0) &&ball.getSectorOfTheBall()!=0; time++) {
            for (int i = 0; i < input.getSimulationProperties().get(1); i++) {
                System.out.println("It is time for player number " + teamFCLEFT.get(i).getMyNumber() + " from team " + teamFCLEFT.get(i).getMyTeam() + " who is in sector number " + teamFCLEFT.get(i).getMySector());
                teamFCLEFT.get(i).decideAmIOnTheBall(ball); // other decideAmIOnTheBall operations are taking place in classes not in main loop.
                if (teamFCLEFT.get(i).preparingToShoot(field, ball, goalkeeperASRight)) {
                    goalkeeperASRight.save(ball, match, field);
                    break;
                }
                teamFCLEFT.get(i).interception(field, ball);
                pass.passTheBallIfPossible(field, teamFCLEFT.get(i), ball);
                movement.movingPlayerToHisFinalDestination(field, teamFCLEFT.get(i), ball);
                teamFCLEFT.get(i).interception(field, ball);
                pass.passTheBallIfPossible(field, teamFCLEFT.get(i), ball);
            }
            for (int k = 0; k < input.getSimulationProperties().get(1); k++) {
                System.out.println("It is time for player number " + teamASRIGHT.get(k).getMyNumber() + " from team " + teamASRIGHT.get(k).getMyTeam()
                        + " who is in sector number " + teamASRIGHT.get(k).getMySector());
                teamASRIGHT.get(k).decideAmIOnTheBall(ball);
                if (teamASRIGHT.get(k).preparingToShoot(field, ball, goalkeeperFCLeft)) {
                    goalkeeperFCLeft.save(ball, match, field);
                    break;
                }
                teamFCLEFT.get(k).interception(field, ball);
                pass.passTheBallIfPossible(field, teamASRIGHT.get(k), ball);
                movement.movingPlayerToHisFinalDestination(field, teamASRIGHT.get(k), ball);
                teamASRIGHT.get(k).interception(field, ball);
                pass.passTheBallIfPossible(field, teamASRIGHT.get(k), ball);
            }
        }
        System.out.println("Final results: FC_Left " + match.getGoalsLeft() + " - " + match.getGoalsRight() + " AS_Right");


    }
}

