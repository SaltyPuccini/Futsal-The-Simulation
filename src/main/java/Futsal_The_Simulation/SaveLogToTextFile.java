package Futsal_The_Simulation;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveLogToTextFile {
    public void goalkeepersInterventionsToFile(PrintWriter save, Goalkeeper goalie) {
        save.println(goalie.getMyTeam() + " goalkeeper");
        save.println("Interventions made: " + goalie.getIntervention());
        save.println("Successful interventions made: " + goalie.getSuccessfulIntervention());
        save.println();
    }

    private int allInterceptionsLeft = 0;
    private int allInterceptionsRight = 0;
    private int successfulInterceptionLeft = 0;
    private int successfulInterceptionRight = 0;
    private int allShotsLeft = 0;
    private int allShotsRight = 0;
    private int shotsOnTargetLeft = 0;
    private int shotsOnTargetRight = 0;
    private int allPassesRight =0;
    private int unsuccessfulPassesRight =0;
    private int allPassesLeft =0;
    private int unsuccessfulPassesLeft =0;


    public void countInterceptions(Player player) {
        switch (player.getMyTeam()) {
            case FC_LEFT:
                allInterceptionsLeft = allInterceptionsLeft + player.getInterception();
                successfulInterceptionLeft = successfulInterceptionLeft + player.getInterceptionSuccessful();
                break;
            case AS_RIGHT:
                allInterceptionsRight = allInterceptionsRight + player.getInterception();
                successfulInterceptionRight = successfulInterceptionRight + player.getInterceptionSuccessful();
                break;
        }
    }

    public void countShots(Player player) {
        switch (player.getMyTeam()) {
            case FC_LEFT:
                allShotsLeft = allShotsLeft + player.getShot();
                shotsOnTargetLeft = shotsOnTargetLeft + player.getShotOnTarget();
                break;
            case AS_RIGHT:
                allShotsRight = allShotsRight + player.getShot();
                shotsOnTargetRight = shotsOnTargetRight + player.getShotOnTarget();
                break;
        }
    }

    public void countPasses(Player player, Pass pass) {
        switch (player.getMyTeam()) {
            case FC_LEFT:
                allPassesLeft = allPassesLeft + pass.getPass();
                unsuccessfulPassesLeft = unsuccessfulPassesLeft + pass.getUnsuccessfulPass();
                break;
            case AS_RIGHT:
                allPassesRight = allPassesRight + pass.getPass();
                unsuccessfulPassesRight = unsuccessfulPassesRight + pass.getUnsuccessfulPass();
                break;
        }
    }

    public void SaveStatsToFile(PrintWriter save, Scoreboard scoreboard, UserInputReader input) {
        save.println ("Final results: FC_Left " + scoreboard.getGoalsLeft() + " - " + scoreboard.getGoalsRight() + " AS_Right");

        save.println ("Match lasted "+ input.getSimulationProperties().get(0) + " units of time.");

        save.println ("Team size: "+ input.getSimulationProperties().get(1));
        save.println();

        save.println ("Final statistics:");

        save.println("Team FCLeft shots total: " + allShotsLeft);
        save.println("Team FCLeft shots on target: " + shotsOnTargetLeft);
        save.println("Team FCLeft interceptions total: " + allInterceptionsLeft);
        save.println("Team FCLeft interceptions successful: " + successfulInterceptionLeft);
        save.println("Team FCLeft passes: " + allPassesLeft);
        save.println("Team FCLeft unsuccessful passes: " + unsuccessfulPassesLeft);
        save.println();
        save.println("Team ASRight shots total: " + allShotsRight);
        save.println("Team ASRight shots on target: " + shotsOnTargetRight);
        save.println("Team ASRight interceptions total: " + allInterceptionsRight);
        save.println("Team ASRight interceptions successful: " + successfulInterceptionRight);
        save.println("Team ASRight passes: " + allPassesRight);
        save.println("Team ASRight unsuccessful passes: " + unsuccessfulPassesRight);
        save.println();
    }


    public void finalSaver (PrintWriter save, Scoreboard scoreboard, UserInputReader input, ArrayList<Player> teamFCLeft, ArrayList<Player> teamASRight, Pass pass){
        for (int i = 0; i < input.getSimulationProperties().get(1); i++) {
            countInterceptions(teamFCLeft.get(i));
            countShots(teamFCLeft.get(i));
            countPasses(teamFCLeft.get(i), pass);
        }

        for (int i = 0; i < input.getSimulationProperties().get(1); i++) {
            countInterceptions(teamASRight.get(i));
            countShots(teamASRight.get(i));
            countPasses(teamASRight.get(i),pass);
        }
        SaveStatsToFile(save, scoreboard, input);

    }

}