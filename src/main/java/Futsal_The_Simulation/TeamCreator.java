package Futsal_The_Simulation;

import java.util.ArrayList;
import java.util.Scanner;

public class TeamCreator {

    private int iterator;
    public ArrayList<Player> createATeam(UserInputReader input, Teams team, Scanner scan) {
        int looper =0;
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        for (int k = 0; k < input.getNumberOfDefenders(); k++) {
            Defender defender = new Defender(team, 0, false, 0, 0, 0, 0, Role.DEFENDER);
            input.askUserForDefenderSpecifications(scan);
            defender.setStats(input.getDefenderStats().get(0), input.getDefenderStats().get(1), looper);
            listOfPlayers.add(defender);
            looper++;
        }
        for (int k = 0; k < input.getNumberOfMidfielders(); k++) {
            Midfielder midfielder = new Midfielder(team, 0, false, 0, 0, 0, 0, Role.MIDFIELDER);
            input.askUserForMidfielderSpecifications(scan);
            midfielder.setStats(input.getMidfielderStats().get(0), input.getMidfielderStats().get(1), input.getMidfielderStats().get(2), looper);
            listOfPlayers.add(midfielder);
            looper++;
        }
        for (int k = 0; k < input.getNumberOfAttackers(); k++) {
            Attacker attacker = new Attacker(team, 0, false, 0, 0, 0, 0, Role.ATTACKER);
            input.askUserForAttackerSpecifications(scan);
            attacker.setStats(input.getAttackerStats().get(0), input.getAttackerStats().get(1), looper);
            listOfPlayers.add(attacker);
            looper++;
        }
        return listOfPlayers;
    }
    //porozbijaæ na mniejsze metody, zrobiæ ustawiacz sektorów, dodaæ zapis do pliku.


    public Goalkeeper createAGoalkeeperLeft(UserInputReader input, Scanner scan) {
        input.askUserForGoalkeeperSpecifications(scan);
        return new Goalkeeper(Teams.FC_LEFT, 3, input.getGoalkeeperStat());
    }

    public Goalkeeper createAGoalkeeperRight(UserInputReader input, Scanner scan) {
        input.askUserForGoalkeeperSpecifications(scan);
        return new Goalkeeper(Teams.AS_RIGHT, 28, input.getGoalkeeperStat());
    }

    public void giveSectorsToDefenders(UserInputReader input, ArrayList<Player> listOfPLayers) {
        int i;
        for (i = 0; i < input.getNumberOfDefenders(); i++) {
            switch (listOfPLayers.get(i).getMyTeam()) {
                case FC_LEFT:
                    listOfPLayers.get(i).setMySector(2 + 2 * i);
                    break;
                case AS_RIGHT:
                    listOfPLayers.get(i).setMySector(29 - 2 * i);
                    break;
            }
            iterator = iterator + 1;
        }
    }

    public void giveSectorsToMidfielders(UserInputReader input, ArrayList<Player> listOfPLayers) {
        int i;
        for (i = 0; i < input.getNumberOfMidfielders(); i++) {
            switch (listOfPLayers.get(iterator).getMyTeam()) {
                case FC_LEFT:
                    listOfPLayers.get(iterator).setMySector(13 + 2 * i);
                    break;
                case AS_RIGHT:
                    listOfPLayers.get(iterator).setMySector(18 - 2 * i);
                    break;
            }
            iterator = iterator + 1;
        }
    }

    public void giveSectorsToAttackers(UserInputReader input, ArrayList<Player> listOfPLayers) {
        int i;
        for (i = 0; i < input.getNumberOfAttackers(); i++) {
            switch (listOfPLayers.get(iterator).getMyTeam()) {
                case FC_LEFT:
                    listOfPLayers.get(iterator).setMySector(18 + (2 * i));
                    break;
                case AS_RIGHT:
                    listOfPLayers.get(iterator).setMySector(13 - 2 * i);
                    break;
            }
            iterator = iterator + 1;
        }
    }

    public void giveSectorsToPlayers(UserInputReader input, ArrayList<Player> listOfPlayers) {
        iterator = 0;
        giveSectorsToDefenders(input, listOfPlayers);
        giveSectorsToMidfielders(input, listOfPlayers);
        giveSectorsToAttackers(input, listOfPlayers);

    }

}
