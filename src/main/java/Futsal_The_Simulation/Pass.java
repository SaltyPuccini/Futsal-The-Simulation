package Futsal_The_Simulation;

import org.json.JSONException;

public class Pass {

    private OtherPlayersOnThePitch otherPlayers = new OtherPlayersOnThePitch();

    public void decideWhetherPassFarOrNear(FieldGenerator field, Player player, Ball ball) throws JSONException {
        System.out.println("Ball is in sector " + ball.getSectorOfTheBall());
        int positionOfSomeoneNearby = otherPlayers.checkingForNearbyTeammates(field, player);
        if (positionOfSomeoneNearby == 0 || positionOfSomeoneNearby<=player.getMySector()-1) {
            //ten if za³atwia podania tylko do przodu/boku
            passFar(field, player, ball);
            System.out.println("I passed Far, now ball is in sector " + ball.getSectorOfTheBall());
        } else {
            passNear(positionOfSomeoneNearby, ball);
            System.out.println("I passed Nearby, now ball is in sector " + ball.getSectorOfTheBall());
        }
    }

    public void passFar(FieldGenerator field, Player player, Ball ball) {
        ball.setSectorOfTheBall(otherPlayers.checkingFarTeammatesPosition(field, player));
    }

    public void passNear(int positionOfSomeoneNearby, Ball ball) {
        ball.setSectorOfTheBall(positionOfSomeoneNearby);
    }

    public void passTheBallIfPossible(FieldGenerator field, Player player, Ball ball) throws JSONException {
        if (player.getAmIOnTheBall()) {
            System.out.println("yay i can pass!");
            decideWhetherPassFarOrNear(field, player, ball);
        }
    }


}