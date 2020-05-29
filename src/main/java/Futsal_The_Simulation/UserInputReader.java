package Futsal_The_Simulation;

import java.util.ArrayList;
import java.util.Scanner;


public class UserInputReader {

    private ArrayList<Integer> simulationProperties = new ArrayList<>();
    private ArrayList<Integer> attackerStats = new ArrayList<>();
    private ArrayList<Integer> midfielderStats = new ArrayList<>();
    private ArrayList<Integer> defenderStats = new ArrayList<>();

    public ArrayList<Integer> operateOnArrayListForSimulationProperties(int timeEstimated, int teamsSize) {
        simulationProperties.add(timeEstimated);
        simulationProperties.add(teamsSize);
        return simulationProperties;
    }

    public ArrayList<Integer> askQuestionsAboutSimulationProperties(Scanner scanFromUser) {
        System.out.println("Type in match duration");
        int timeEstimated = scanFromUser.nextInt();
        System.out.println("Type in the number of players (except goalkeeper)");
        int teamsSize = scanFromUser.nextInt();
        return operateOnArrayListForSimulationProperties(timeEstimated, teamsSize);
    }

    public ArrayList<Integer> getSimulationProperties() {
        return simulationProperties;
    }

    public ArrayList<Integer> operateOnArrayListForAttackerStats(int shootingStat, int passingStat) {
        attackerStats.add(shootingStat);
        attackerStats.add(passingStat);
        return attackerStats;
    }

    public ArrayList<Integer> askUserForAttackerSpecifications(Scanner scanFromUser) {
        System.out.println("Type in attacker shooting stat: ");
        int shootingStat = scanFromUser.nextInt();
        System.out.println("Type in attacker passing stat:  ");
        int passingStat = scanFromUser.nextInt();
        return operateOnArrayListForAttackerStats(shootingStat, passingStat);
    }

    public ArrayList<Integer> getAttackerStats() {
        return attackerStats;
    }

    public ArrayList<Integer> operateOnArrayListForMidfielderStats(int shootingStat, int passingStat, int defendingStat) {
        midfielderStats.add(shootingStat);
        midfielderStats.add(passingStat);
        midfielderStats.add(defendingStat);
        return midfielderStats;
    }

    public ArrayList<Integer> askUserForMidfielderSpecifications(Scanner scanFromUser) {
        System.out.println("Podaj statystyke strzelania pomocnika: ");
        int shootingStat = scanFromUser.nextInt();
        System.out.println("Podaj statystyke podania pomocnika: ");
        int passingStat = scanFromUser.nextInt();
        System.out.println("Podaj statystyke obrony pomocnika: ");
        int defendingStat = scanFromUser.nextInt();
        return operateOnArrayListForMidfielderStats(shootingStat, passingStat, defendingStat);
    }

    public ArrayList<Integer> getMidfielderStats() {
        return midfielderStats;
    }

    public ArrayList<Integer> operateOnArrayListForDefenderStats(int passingStat, int defendingStat) {
        defenderStats.add(passingStat);
        defenderStats.add(defendingStat);
        return defenderStats;
    }

    public ArrayList<Integer> askUserForDefenderSpecifications(Scanner scanFromUser) {
        System.out.println("Podaj statystyke podania obroncy: ");
        int passingStat = scanFromUser.nextInt();
        System.out.println("Podaj statystyke obrony obroncy: ");
        int defendingStat = scanFromUser.nextInt();
        return operateOnArrayListForDefenderStats(passingStat, defendingStat);
    }

    public ArrayList<Integer> getDefenderStats() {
        return defenderStats;
    }
}