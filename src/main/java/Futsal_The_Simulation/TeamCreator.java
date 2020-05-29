package Futsal_The_Simulation;
import java.util.ArrayList;

public class TeamCreator {
   private ArrayList<Player> footballTeam = new ArrayList<>();

   public void assignStatsToAttacker (Attacker attacker, UserInputReader input)
   {
      attacker.setStats(input.getAttackerStats().get(0), input.getAttackerStats().get(1));
   }

   public void assignStatsToMidfielder (Midfielder midfielder, UserInputReader input)
   {
      midfielder.setStats(input.getMidfielderStats().get(0), input.getMidfielderStats().get(1), input.getMidfielderStats().get(2));
   }

   public void assignStatsToDefender (Defender defender, UserInputReader input)
   {
      defender.setStats(input.getDefenderStats().get(0), input.getDefenderStats().get(1));
   }
}
