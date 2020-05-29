package Futsal_The_Simulation;

import java.util.Random;

public class Movement {

    private Random randomGenerator = new Random();
    private Sector sectorWeAreChecking;

    public Directions directionsBasedOnRandomNumber(int randomNumber) {
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


    public Directions directionsICanNotGo() {
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

    public Directions directionsWhenInCorner() {
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

    public Directions drawingPlayerDirection() {
        int randomNumber = randomGenerator.nextInt(4) + 1;
        return directionsBasedOnRandomNumber(randomNumber);
    }


    public Directions choosingFinalDestination(FieldGenerator field, Player player) {
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

    public void moveBallWithPlayer(Player player, Ball ball) {
        if (player.getAmIOnTheBall()) {
            ball.setSectorOfTheBall(player.getMySector());
            System.out.println("I took the ball with me since it belongs to me!");
        }
    }

    public boolean isThereAnyoneWhereIWantToGo (FieldGenerator field, Player player){
        return (field.getAllSectors().get(player.getMySector()-1).getIsPlayerLeftHere() || field.getAllSectors().get(player.getMySector()-1).getIsPlayerRightHere());
    }

    public void movingPlayerToHisFinalDestination(FieldGenerator field, Player player, Ball ball) {
        field.giveInformationPlayerLeaving(player);
        System.out.println("I guess i gotta go somewhere from sector number " + player.getMySector());
        int current=player.getMySector();
        do {
            player.setMySector(current);
            switch (choosingFinalDestination(field, player)) {
                case UP:
                    player.setMySector(player.getMySector() - 1);
                    break;
                case DOWN:
                    player.setMySector(player.getMySector() + 1);
                    break;
                case RIGHT:
                    player.setMySector(player.getMySector() + 5);
                    break;
                case LEFT:
                    player.setMySector(player.getMySector() - 5);
                    break;
            }
        } while (isThereAnyoneWhereIWantToGo(field, player));
        field.giveInformationPlayerAppearing(player);
        System.out.println("I reached sector number " + player.getMySector());
        moveBallWithPlayer(player, ball);

    }
}

