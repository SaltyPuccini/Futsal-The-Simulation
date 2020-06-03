package Futsal_The_Simulation;

import java.util.ArrayList;
import java.util.Scanner;


public class UserInputReader {

    private final ArrayList<Integer> simulationProperties = new ArrayList<>();
    private final ArrayList<Integer> attackerStats = new ArrayList<>();
    private final ArrayList<Integer> midfielderStats = new ArrayList<>();
    private final ArrayList<Integer> defenderStats = new ArrayList<>();
    private int goalkeeperStat;


    private void operateOnArrayListForSimulationProperties(int timeEstimated, int teamsSize) {
        simulationProperties.add(timeEstimated);
        simulationProperties.add(teamsSize);
    }

    public void askQuestionsAboutSimulationProperties(Scanner scanFromUser) {
        System.out.println("Type in match duration, and number of players (except goalkeeper)");
        int timeEstimated, teamsSize;
        do {
            timeEstimated = scanFromUser.nextInt();
            teamsSize = scanFromUser.nextInt();
        } while (timeEstimated <= 0 || teamsSize < 2 || teamsSize > 7);
        operateOnArrayListForSimulationProperties(timeEstimated, teamsSize);
    }

    public ArrayList<Integer> getSimulationProperties() {
        return simulationProperties;
    }

    public void whoStarts(Scanner scanFromUser, Ball ball, Player playerLeft, Player playerRight){
        int whoStarts;
        do{System.out.println("Which team should start the match? (1)FC_LEFT, or (2)AS_RIGHT?");
        whoStarts = scanFromUser.nextInt();}while (whoStarts>2||whoStarts<1);

        switch (whoStarts){
            case 1:
                ball.setTeamOfTheBall(Teams.FC_LEFT);
                ball.setSectorOfTheBall(playerLeft.getMySector());
                break;
            case 2:
                ball.setTeamOfTheBall(Teams.AS_RIGHT);
                ball.setSectorOfTheBall(playerRight.getMySector());
                break;
        }

    }

    private void operateOnArrayListForAttackerStats(int shootingStat, int passingStat) {
        attackerStats.add(shootingStat);
        attackerStats.add(passingStat);
    }

    public void askUserForAttackerSpecifications(Scanner scanFromUser) {
        int shootingStat, passingStat;
        do {System.out.println("Type in attacker's shooting and passing stats: ");
            shootingStat = scanFromUser.nextInt();
            passingStat = scanFromUser.nextInt(); }while(shootingStat<1 || shootingStat>100 || passingStat<=0 || passingStat>100);
        operateOnArrayListForAttackerStats(shootingStat, passingStat);
    }

    public ArrayList<Integer> getAttackerStats() {
        return attackerStats;
    }

    private void operateOnArrayListForMidfielderStats(int shootingStat, int passingStat, int defendingStat) {
        midfielderStats.add(shootingStat);
        midfielderStats.add(passingStat);
        midfielderStats.add(defendingStat);
    }

    public void askUserForMidfielderSpecifications(Scanner scanFromUser) {
        int shootingStat, passingStat, defendingStat;
        do {System.out.println("Type in midfielder's shooting, passing and defending stats: ");
            shootingStat = scanFromUser.nextInt();
            passingStat = scanFromUser.nextInt();
            defendingStat = scanFromUser.nextInt(); }while(shootingStat<1 || shootingStat>100 || passingStat<1 || passingStat>100 || defendingStat<1 || defendingStat>100);
        operateOnArrayListForMidfielderStats(shootingStat, passingStat, defendingStat);
    }

    public ArrayList<Integer> getMidfielderStats() {
        return midfielderStats;
    }

    private void operateOnArrayListForDefenderStats(int passingStat, int defendingStat) {
        defenderStats.add(passingStat);
        defenderStats.add(defendingStat);
    }

    public void askUserForDefenderSpecifications(Scanner scanFromUser) {
        int passingStat, defendingStat;
        do {System.out.println("Type in defender's passing and defending stats: ");
            passingStat = scanFromUser.nextInt();
            defendingStat = scanFromUser.nextInt(); }while(passingStat<1 || passingStat>100 || defendingStat<1 || defendingStat>100);
        operateOnArrayListForDefenderStats(passingStat, defendingStat);
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

    private void operateOnArrayListForGoalkeeperStats(int goalkeepingStat) {
        this.goalkeeperStat=goalkeepingStat;
    }

    public void askUserForGoalkeeperSpecifications(Scanner scanFromUser) {
        int goalkeepingStat;
        do {System.out.println("Type in goalkeeper's goalkeeping stat: ");
            goalkeepingStat = scanFromUser.nextInt(); }while(goalkeepingStat<1 || goalkeepingStat>100);
        operateOnArrayListForGoalkeeperStats(goalkeepingStat);
    }

    public int getGoalkeeperStat() {
        return goalkeeperStat;
    }
}
