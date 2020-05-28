package Futsal_The_Simulation;
import java.util.ArrayList;
import java.util.Scanner;


public class UserInputReader {

    private ArrayList<Integer> simulationProperties = new ArrayList<>();
    private int timeEstimated;
    private int teamsSize;
    private ArrayList<Integer> attackerStats = new ArrayList<>();
    private ArrayList<Integer> midfielderStats = new ArrayList<>();
    private ArrayList<Integer> defenderStats = new ArrayList<>();
    private int shootingStat;
    private int passingStat;
    private int defendingStat;

    public ArrayList<Integer> operateOnArrayListForSimulationProperties() {
        simulationProperties.add(timeEstimated);
        simulationProperties.add(teamsSize);
        return simulationProperties;
    }

    public ArrayList<Integer> askQuestionsAboutSimulationPropertiesThenOperateOnArrayList(Scanner scanFromUser) {
        System.out.println("Ile minut ma trwaæ mecz?");
        timeEstimated = scanFromUser.nextInt();
        System.out.println("Ilu pi³karzy (nie licz¹c bramkarza) powinna liczyæ dru¿yna?");
        teamsSize = scanFromUser.nextInt();
        return operateOnArrayListForSimulationProperties();
    }

    public ArrayList<Integer> operateOnArrayListForAttackerStats(){
        attackerStats.add(shootingStat);
        attackerStats.add(passingStat);
        return attackerStats;
    }

    public ArrayList<Integer> operateOnArrayListForMidfielderStats(){
        midfielderStats.add(shootingStat);
        midfielderStats.add(passingStat);
        midfielderStats.add(defendingStat);
        return midfielderStats;
    }

    public ArrayList<Integer> operateOnArrayListForDefenderStats(){
        defenderStats.add(passingStat);
        defenderStats.add(defendingStat);
        return defenderStats;
    }

    public ArrayList<Integer> askUserForAttackerSpecifications(Scanner scanFromUser) {
        System.out.println("Podaj statystyke strzelania napastnika: ");
        shootingStat = scanFromUser.nextInt();
        System.out.println("Podaj statystyke podania napastnika: ");
        passingStat = scanFromUser.nextInt();
        return operateOnArrayListForAttackerStats();
    }

    public ArrayList<Integer> askUserForMidfielderSpecifications(Scanner scanFromUser) {
        System.out.println("Podaj statystyke strzelania pomocnika: ");
        shootingStat = scanFromUser.nextInt();
        System.out.println("Podaj statystyke podania pomocnika: ");
        passingStat = scanFromUser.nextInt();
        System.out.println("Podaj statystyke obrony pomocnika: ");
        defendingStat = scanFromUser.nextInt();
        return operateOnArrayListForMidfielderStats();
    }

    public ArrayList<Integer> askUserForDefenderSpecifications(Scanner scanFromUser) {
        System.out.println("Podaj statystyke podania obroncy: ");
        passingStat = scanFromUser.nextInt();
        System.out.println("Podaj statystyke obrony obroncy: ");
        defendingStat = scanFromUser.nextInt();
        return operateOnArrayListForDefenderStats();
    }
}
