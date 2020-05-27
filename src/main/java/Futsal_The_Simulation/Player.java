package Futsal_The_Simulation;

public abstract class Player {
    public char move(PlayerSpecifications statistics, FieldGenerator){
        statistics.getWhichSectorAmIIn();
        /* make a move */
        return moveDirection;
    }

    public boolean pass(){
        return true;
    }

    public boolean reception(){
        return true;
    }



}
