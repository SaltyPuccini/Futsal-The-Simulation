package Futsal_The_Simulation;

import java.util.ArrayList;
import java.util.Scanner;


public class UserInputReader {

    private ArrayList<Integer> simulationProperties = new ArrayList<>();
    private ArrayList<Integer> attackerStats = new ArrayList<>();
    private ArrayList<Integer> midfielderStats = new ArrayList<>();
    private ArrayList<Integer> defenderStats = new ArrayList<>();
    private ArrayList<Integer> goalkeeperStats = new ArrayList<>();


    public ArrayList<Integer> operateOnArrayListForSimulationProperties(int timeEstimated, int teamsSize) {
        simulationProperties.add(timeEstimated);
        simulationProperties.add(teamsSize);
        return simulationProperties;
    }

    public ArrayList<Integer> askQuestionsAboutSimulationProperties(Scanner scanFromUser) {
        System.out.println("Type in match duration");
        int timeEstimated;
        do {
            timeEstimated = scanFromUser.nextInt();
        } while (timeEstimated <= 0);
        System.out.println("Type in the number of players (except goalkeeper)");
        int teamsSize;
        do {
            teamsSize = scanFromUser.nextInt();
        } while (teamsSize < 2 || teamsSize > 7);
        return operateOnArrayListForSimulationProperties(timeEstimated, teamsSize);
    }

    public ArrayList<Integer> getSimulationProperties() {
        return simulationProperties;
    }

    public ArrayList<Integer> operateOnArrayListForAttackerStats(int shootingStat, int passingStat, int myNumber) {
        attackerStats.add(shootingStat);
        attackerStats.add(passingStat);
        attackerStats.add(myNumber);
        return attackerStats;
    }

    public ArrayList<Integer> askUserForAttackerSpecifications(Scanner scanFromUser) {
        int shootingStat, passingStat;
        do {System.out.println("Type in attacker's shooting stat: ");
            shootingStat = scanFromUser.nextInt();
            System.out.println("Type in attacker's passing stat:  ");
            passingStat = scanFromUser.nextInt(); }while(shootingStat<1 || shootingStat>100 || passingStat<=0 || passingStat>100);
        System.out.println("Type in attacker's number on the shirt:  ");
        int myNumber = scanFromUser.nextInt();
        return operateOnArrayListForAttackerStats(shootingStat, passingStat, myNumber);
    }

    public ArrayList<Integer> getAttackerStats() {
        return attackerStats;
    }

    public ArrayList<Integer> operateOnArrayListForMidfielderStats(int shootingStat, int passingStat, int defendingStat, int myNumber) {
        midfielderStats.add(shootingStat);
        midfielderStats.add(passingStat);
        midfielderStats.add(defendingStat);
        midfielderStats.add(myNumber);
        return midfielderStats;
    }

    public ArrayList<Integer> askUserForMidfielderSpecifications(Scanner scanFromUser) {
        int shootingStat, passingStat, defendingStat;
        do {System.out.println("Type in midfielder's shooting stat: ");
            shootingStat = scanFromUser.nextInt();
            System.out.println("Type in midfielder's passing stat: ");
            passingStat = scanFromUser.nextInt();
            System.out.println("Type in midfielder's defending stat: ");
            defendingStat = scanFromUser.nextInt(); }while(shootingStat<1 || shootingStat>100 || passingStat<1 || passingStat>100 || defendingStat<1 || defendingStat>100);
        System.out.println("Type in midfielder's number on the shirt:  ");
        int myNumber = scanFromUser.nextInt();
        return operateOnArrayListForMidfielderStats(shootingStat, passingStat, defendingStat, myNumber);
    }

    public ArrayList<Integer> getMidfielderStats() {
        return midfielderStats;
    }

    public ArrayList<Integer> operateOnArrayListForDefenderStats(int passingStat, int defendingStat, int myNumber) {
        defenderStats.add(passingStat);
        defenderStats.add(defendingStat);
        defenderStats.add(myNumber);
        return defenderStats;
    }

    public ArrayList<Integer> askUserForDefenderSpecifications(Scanner scanFromUser) {
        int passingStat, defendingStat;
        do {System.out.println("Type in defender's passing stat: ");
            passingStat = scanFromUser.nextInt();
            System.out.println("Type in defender's defending stat: ");
            defendingStat = scanFromUser.nextInt(); }while(passingStat<1 || passingStat>100 || defendingStat<1 || defendingStat>100);
        System.out.println("Type in defender's number on the shirt:  ");
        int myNumber = scanFromUser.nextInt();
        return operateOnArrayListForDefenderStats(passingStat, defendingStat, myNumber);
    }

    public ArrayList<Integer> getDefenderStats() {
        return defenderStats;
    }

    private int numberOfAttackers;

    public int getNumberOfAttackers() {
        return numberOfAttackers;
    }

    private int numberOfMidfielders;

    public int getNumberOfDefenders() {
        return numberOfDefenders;
    }

    private int numberOfDefenders;

    public int getNumberOfMidfielders() {
        return numberOfMidfielders;
    }

    public void rolesInTeam(Teams team, Scanner scanFromUser) {
        do {
            do {
                System.out.println("Type in number of attackers in team " + team);
                numberOfAttackers = scanFromUser.nextInt();
            } while (numberOfAttackers < 0);
            do {
                System.out.println("Type in number of midfielders in team " + team);
                numberOfMidfielders = scanFromUser.nextInt();
            } while (numberOfMidfielders < 0);
            do {
                System.out.println("Type in number of defenders in team " + team);
                numberOfDefenders = scanFromUser.nextInt();
            } while (numberOfDefenders < 0);
        } while ((numberOfAttackers + numberOfMidfielders + numberOfDefenders) != simulationProperties.get(1));
    }

    public ArrayList<Integer> operateOnArrayListForGoalkeeperStats(int passingStat, int goalkeepingStat) {
        goalkeeperStats.add(passingStat);
        goalkeeperStats.add(goalkeepingStat);
        return goalkeeperStats;
    }

    public ArrayList<Integer> askUserForGoalkeeperSpecifications(Scanner scanFromUser) {
        int passingStat, goalkeepingStat;
        do {System.out.println("Type in goalkeeper's passing stat: ");
            passingStat = scanFromUser.nextInt();
            System.out.println("Type in goalkeeper's goalkeeping stat: ");
            goalkeepingStat = scanFromUser.nextInt(); }while(passingStat<1 || passingStat>100 || goalkeepingStat<1 || goalkeepingStat>100);
        return operateOnArrayListForGoalkeeperStats(passingStat, goalkeepingStat);
    }

    public ArrayList<Integer> getGoalkeeperStats() {
        return goalkeeperStats;
    }
}
