package Futsal_The_Simulation;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Pass enables player to the pass.
 */
public class Pass {

    private final OtherPlayersOnThePitch otherPlayers = new OtherPlayersOnThePitch();

    private void decideWhetherPassFarOrNear(FieldGenerator field, Player player, Ball ball, ArrayList<Player> listOfEnemies, UserInputReader input) throws JSONException {
        int positionOfSomeoneNearby = otherPlayers.checkingForNearbyTeammates(field, player);
        if (positionOfSomeoneNearby == 0 || positionOfSomeoneNearby <= player.getMySector() - 1) {
            //ten if za³atwia podania tylko do przodu/boku
            passFar(field, player, ball, listOfEnemies, input);
        } else {
            passNear(positionOfSomeoneNearby, ball);
            System.out.println("Player number " + player.getMyNumber() + " from " + player.getMyTeam() + " passes the ball to his teammate, who's next to him in sector " + ball.getSectorOfTheBall() + ".");
        }
    }

    private void passFar(FieldGenerator field, Player player, Ball ball, ArrayList<Player> listOfEnemies, UserInputReader input) {
        int chosenPosition = otherPlayers.checkingFarTeammatesPosition(field, player);
        if (chosenPosition == 0) {
            System.out.println("There's no one to pass on the whole field. I give up. The match is over. I'm kicking the ball out of the park");
            ball.setSectorOfTheBall(0);
        }
        Random randomGenerator = new Random();
        int willISucceed = randomGenerator.nextInt(100) + 1;
        if (willISucceed < player.getPassingStat()) {
            ball.setSectorOfTheBall(chosenPosition);
            System.out.println("Player number " + player.getMyNumber() + " from " + player.getMyTeam() + " passes the ball to his teammate, who's far from him in sector " + ball.getSectorOfTheBall() + ".");
        } else {
            int positionOfRandomEnemy = otherPlayers.positionOfRandomEnemy(listOfEnemies, input);
            ball.setSectorOfTheBall(positionOfRandomEnemy);
            ball.setTeamOfTheBall(listOfEnemies.get(1).getMyTeam());
            unsuccessfulPass++;
            for (int i = 0; i < input.getSimulationProperties().get(1); i++) {
                if(listOfEnemies.get(i).getMySector()==positionOfRandomEnemy)
                        listOfEnemies.get(i).setAmIOnTheBall(true);
            }
            System.out.println("Player number " + player.getMyNumber() + " from " + player.getMyTeam() + " passes really inaccurate and now the ball in sector " + ball.getSectorOfTheBall() + ".");
        }

    }

    private void passNear(int positionOfSomeoneNearby, Ball ball) {
        ball.setSectorOfTheBall(positionOfSomeoneNearby);
    }

    public void passTheBallIfPossible(FieldGenerator field, Player player, Ball ball, ArrayList<Player> listOfEnemies, UserInputReader input) throws JSONException {
        if (player.getAmIOnTheBall()) {
            pass++;
            decideWhetherPassFarOrNear(field, player, ball, listOfEnemies, input);
            player.decideAmIOnTheBall(ball);
        }
    }

    int pass = 0;
    int unsuccessfulPass = 0;

    public int getPass() {
        return pass;
    }

    public int getUnsuccessfulPass() {
        return unsuccessfulPass;
    }
}
