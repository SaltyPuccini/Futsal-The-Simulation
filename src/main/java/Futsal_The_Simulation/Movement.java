package Futsal_The_Simulation;

import java.util.Random;

/**
 * Movement is responsible for movement of players.
 */
public class Movement {

    private final Random randomGenerator = new Random();
    private Sector sectorWeAreChecking;

    /**
     * Depending on random number, returns direction of movement.
     * @param randomNumber
     * @return
     */
    private Directions directionsBasedOnRandomNumber(int randomNumber) {
        switch (randomNumber) {
            case 1:
                return Directions.UP;
            case 2:
                return Directions.LEFT;
            case 3:
                return Directions.DOWN;
            case 4:
                return Directions.RIGHT;
        }
        return Directions.UP;
    }

    /**
     * If there is a wall next to player's sector this method returns direction in which player is not allowed to move.
     * @return
     */
    private Directions directionsICanNotGo() {
        String isWallNextToMe = sectorWeAreChecking.getIsWallNextToMe();
        switch (isWallNextToMe) {
            case "none":
                return Directions.NONE;
            case "up":
                return Directions.UP;
            case "down":
                return Directions.DOWN;
            case "right":
                return Directions.RIGHT;
            case "left":
                return Directions.LEFT;
        }
        return Directions.NONE;
    }

    /**
     * Checks if AS_Right's player is about to leave his dedicated area of the field.
     * @param player - player who is leaving the dedicated area.
     * @param sector - sector of player.
     * @return true if is on border, false if isn't.
     */
    private boolean checkIfPlayerRightOnTheEdge(Player player, int sector) {
        switch (player.getMyRole()) {
            case ATTACKER:
                if (sector > 15) {
                    System.out.println("I tried to cross > 15 ");
                    return true;
                }
                break;
            case DEFENDER:

                if (sector < 16) {
                    System.out.println("I tried to cross < 16 ");
                    return true;
                }
                break;
            case MIDFIELDER:
                if (sector < 6 || sector > 25) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Checks if FC_Left's player is about to leave his dedicated area of the field.
     * @param player - player who is leaving the dedicated area.
     * @param sector - sector of player.
     * @return true if is on border, false if isn't.
     */
    private boolean checkIfPlayerLeftOnTheEdge(Player player, int sector) {
        switch (player.getMyRole()) {
            case DEFENDER:
                if (sector > 15)
                    return true;
                break;
            case ATTACKER:
                if (sector < 16)
                    return true;
                break;
            case MIDFIELDER:
                if (sector < 6 || sector > 25)
                    return true;
                break;
        }
        return false;
    }


    /**
     * Depending on the team - checks if the player is about to leave destinated area.
     * @param player - player who we are checking.
     * @param sector - player's sector after his move.
     * @return true if player leaves, false if not.
     */
    private boolean didICrossTheBorder(Player player, int sector) {
        if (player.getMyTeam() == Teams.AS_RIGHT)
            return checkIfPlayerRightOnTheEdge(player, sector);
        if (player.getMyTeam() == Teams.FC_LEFT)
            return checkIfPlayerLeftOnTheEdge(player, sector);

        return false;
    }

    /**
     * Tells player where to go when he's in corner.
     * @return direction of movement.
     */
    private Directions directionsWhenInCorner() {
        String isWallNextToMe = sectorWeAreChecking.getIsWallNextToMe();
        switch (isWallNextToMe) {
            case "left_up_corner":
            case "right_up_corner":
                return Directions.DOWN;
            case "left_down_corner":
            case "right_down_corner":
                return Directions.UP;
        }
        return Directions.NONE;
    }

    /**
     * Chooses direction for player based on random number.
     * @return direction where player goes.
     */
    private Directions drawingPlayerDirection() {
        int randomNumber = randomGenerator.nextInt(4) + 1;
        return directionsBasedOnRandomNumber(randomNumber);
    }


    private Directions choosingFinalDestination(FieldGenerator field, Player player) {
        int playerSector = player.getMySector();
        sectorWeAreChecking = field.getAllSectors().get(playerSector - 1);
        Directions direction;
        Directions cornerMovement = directionsWhenInCorner();
        if (cornerMovement != Directions.NONE) {
            return cornerMovement;
        }
        do direction = drawingPlayerDirection(); while (direction == directionsICanNotGo());
        return direction;
    }

    private void moveBallWithPlayer(Player player, Ball ball) {
        if (player.getAmIOnTheBall()) {
            ball.setSectorOfTheBall(player.getMySector());
            System.out.println("He takes the ball with him.");
        }
    }

    private boolean isThereMyTeammateWhereIWantToGo(FieldGenerator field, Player player) {
        if (player.getMyTeam() == Teams.AS_RIGHT)
            return (field.getAllSectors().get(player.getMySector() - 1).getIsPlayerRightHere());
        if (player.getMyTeam() == Teams.FC_LEFT)
            return (field.getAllSectors().get(player.getMySector() - 1).getIsPlayerLeftHere());
        return false;
    }

    public void movingPlayerToHisFinalDestination(FieldGenerator field, Player player, Ball ball) {
        field.giveInformationPlayerLeaving(player);
        int current = player.getMySector();
        int howManyTimesIteration = 0;
        int howManyTimesIteration2 = 0;
        do {
            do {
                player.setMySector(current);
                switch (choosingFinalDestination(field, player)) {
                    case UP:
                        player.setMySector(current - 1);
                        break;
                    case DOWN:
                        player.setMySector(current + 1);
                        break;
                    case RIGHT:
                        player.setMySector(current + 5);
                        break;
                    case LEFT:
                        player.setMySector(current - 5);
                        break;

                }
                howManyTimesIteration++;
            } while (isThereMyTeammateWhereIWantToGo(field, player) && howManyTimesIteration < 6);
            howManyTimesIteration2++;
        }while(didICrossTheBorder(player, player.getMySector()) && howManyTimesIteration2 < 6);
        boolean flagToShowMessage = false;
        if (howManyTimesIteration >= 6 ||howManyTimesIteration2>=6) {
            player.setMySector(current);
            System.out.println("Player number " + player.getMyNumber() + " from " + player.getMyTeam() + " decides to stay in sector " + player.getMySector() + ", since he's surrounded with teammates.");
            flagToShowMessage = true;
        }
        field.giveInformationPlayerAppearing(player);
        if (!flagToShowMessage)
            System.out.println("Player number " + player.getMyNumber() + " from " + player.getMyTeam() + " moves to sector " + player.getMySector() + ".");
        moveBallWithPlayer(player, ball);

    }

}