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


    public boolean checkIfPlayerRightOnTheEdge(Player player, int sector)
    {
        switch (player.getMyRole())
        {
            case ATTACKER:
                if (sector>15)
                    return true;
            case DEFENDER:
                if (sector<16)
                    return true;
            case MIDFIELDER:
                if (sector<6 || sector>25)
                    return true;
        }
        return false;
    }

    public boolean checkIfPlayerLeftOnTheEdge(Player player, int sector)
    {
        switch (player.getMyRole())
        {
            case DEFENDER:
                if (sector>15)
                    return true;
            case ATTACKER:
                if (sector<16)
                    return true;
            case MIDFIELDER:
                if (sector<6 || sector>25)
                    return true;
        }
        return false;
    }

    //get my sektor jeœli jest <, badz > zwraca true inaczej false
    // petla ma sie robic dopoki jest true
    public boolean didICrossTheBorder(Player player, int sector) {
        if (player.getMyTeam() == Teams.AS_RIGHT)
            return checkIfPlayerRightOnTheEdge(player, sector);
        else
            return checkIfPlayerLeftOnTheEdge(player, sector);
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
            System.out.println("I take the ball with me.");
        }
    }

    public boolean isThereMyTeammateWhereIWantToGo(FieldGenerator field, Player player) {
        if (player.getMyTeam() == Teams.AS_RIGHT)
            return (field.getAllSectors().get(player.getMySector() - 1).getIsPlayerRightHere());
        else
            return (field.getAllSectors().get(player.getMySector() - 1).getIsPlayerLeftHere());
    }

    public void movingPlayerToHisFinalDestination(FieldGenerator field, Player player, Ball ball) {
        field.giveInformationPlayerLeaving(player);
        int current = player.getMySector();
        int howManyTimesIteration = 0;
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
            howManyTimesIteration++;
        } while (isThereMyTeammateWhereIWantToGo(field, player) && howManyTimesIteration < 6 && didICrossTheBorder(player, player.getMySector())); //unikamy  nieskoñczonej pêtli, jak 6 razy z niej nie wyjdzie - zostaje w miejscu
        if (howManyTimesIteration >= 6) {
            player.setMySector(current);
            System.out.print("Since I'm surrounded with my teammates, I didn't move at all.");
        }
        field.giveInformationPlayerAppearing(player);
        System.out.println("I moved to the connected sector number " + player.getMySector() + ".");
        moveBallWithPlayer(player, ball);

    }
}

