package Futsal_The_Simulation;

import org.json.JSONException;

public class Pass {

    OtherPlayersOnThePitch otherPlayers = new OtherPlayersOnThePitch();

    public void decideWhetherPassFarOrNear(FieldGenerator field, Player player, Ball ball) throws JSONException {
        int positionOfSomeoneNearby = otherPlayers.checkingForNearbyTeammates(field, player);
        if (positionOfSomeoneNearby == 0 || positionOfSomeoneNearby<=player.getMySector()-1) {
            //ten if za³atwia podania tylko do przodu/boku
            passFar(field, player, ball);
            System.out.println("I passed the ball to my teammate, who is far away from me, in sector " + ball.getSectorOfTheBall() + ".");
        } else {
            passNear(positionOfSomeoneNearby, ball);
            System.out.println("I passed the ball to my teammate, who is very close to me, in sector " + ball.getSectorOfTheBall() + ".");
        }
    }

    public void passFar(FieldGenerator field, Player player, Ball ball) {
        int chosenPosition = otherPlayers.checkingFarTeammatesPosition(field, player);
        if (chosenPosition==0) {
            System.out.println("There's noone to pass on the whole field. I give up. The match is over. I'm kicking the ball out of the park");
            ball.setSectorOfTheBall(0);
        }
        ball.setSectorOfTheBall(chosenPosition);
    }

    public void passNear(int positionOfSomeoneNearby, Ball ball) {
        ball.setSectorOfTheBall(positionOfSomeoneNearby);
    }

    public void passTheBallIfPossible(FieldGenerator field, Player player, Ball ball) throws JSONException {
        if (player.getAmIOnTheBall()) {
            decideWhetherPassFarOrNear(field, player, ball);
            player.decideAmIOnTheBall(ball);
        }
    }


}