package Futsal_The_Simulation;

import org.json.JSONException;

import java.util.ArrayList;

public class Match {

    public void runSimulation(UserInputReader input, Ball ball, ArrayList<Player> teamFCLeft, ArrayList<Player> teamASRight, Pass pass, Movement movement, FieldGenerator field, Goalkeeper goalkeeperASRight, Goalkeeper goalkeeperFCLeft, Scoreboard scoreboard) throws JSONException {
        for (int time = 0; time < input.getSimulationProperties().get(0) && ball.getSectorOfTheBall() != 0; time++) {
            for (int i = 0; i < input.getSimulationProperties().get(1); i++) {

                teamFCLeft.get(i).decideAmIOnTheBall(ball); // other decideAmIOnTheBall operations are taking place in classes not in main loop.
                if (teamFCLeft.get(i).preparingToShoot(field, ball, goalkeeperASRight)) {
                    goalkeeperASRight.save(ball, field, scoreboard);
                    break;
                }
                teamFCLeft.get(i).interception(ball);
                pass.passTheBallIfPossible(field, teamFCLeft.get(i), ball, teamASRight, input);
                movement.movingPlayerToHisFinalDestination(field, teamFCLeft.get(i), ball);
                teamFCLeft.get(i).interception(ball);
                pass.passTheBallIfPossible(field, teamFCLeft.get(i), ball, teamASRight, input);
            }
            for (int k = 0; k < input.getSimulationProperties().get(1); k++) {

                teamASRight.get(k).decideAmIOnTheBall(ball);
                if (teamASRight.get(k).preparingToShoot(field, ball, goalkeeperFCLeft)) {
                    goalkeeperFCLeft.save(ball, field, scoreboard);
                    break;
                }
                teamFCLeft.get(k).interception(ball);
                pass.passTheBallIfPossible(field, teamASRight.get(k), ball, teamFCLeft, input);
                movement.movingPlayerToHisFinalDestination(field, teamASRight.get(k), ball);
                teamASRight.get(k).interception(ball);
                pass.passTheBallIfPossible(field, teamASRight.get(k), ball, teamFCLeft, input);
            }
        }
        System.out.println("Final results: FC_Left " + scoreboard.getGoalsLeft() + " - " + scoreboard.getGoalsRight() + " AS_Right");
    }
}
