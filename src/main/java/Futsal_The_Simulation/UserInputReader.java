package Futsal_The_Simulation;
import java.util.ArrayList;
import java.util.Scanner;


public class UserInputReader {

    ArrayList<Integer> simulationProperties = new ArrayList<>();
    private int timeEstimated;
    private int teamsSize;

    public ArrayList<Integer> operateOnArrayList() {
        simulationProperties.add(timeEstimated);
        simulationProperties.add(teamsSize);
        return simulationProperties;
    }

    public ArrayList<Integer> askQuestionsAboutSimulationPropertiesThenOperateOnArrayList(Scanner scanFromUser) {
        System.out.println("Ile minut ma trwaæ mecz?");
        timeEstimated = scanFromUser.nextInt();
        System.out.println("Ilu pi³karzy (nie licz¹c bramkarza) powinna liczyæ dru¿yna?");
        teamsSize = scanFromUser.nextInt();
        return operateOnArrayList();
    }


    public int askUserForPlayerSpecifications() {


        return 0;
    }
}
