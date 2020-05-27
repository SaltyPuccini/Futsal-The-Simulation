package Futsal_The_Simulation;

import java.util.Random;

public class Player {

    boolean strzelaj(Random r) {
        int szansa = 0;
        if (getDruzyna()) {
            if (strefa <= 10)
                szansa = (strzal);
            if (strefa > 10 && strefa <= 20)
                szansa = (strzal) * (15);
            if ((strefa > 20 && strefa <= 26) || strefa == 30)
                szansa = (strzal) * (70);
            if (strefa > 26 && strefa <= 29)
                szansa = (strzal) * (90);
        } else {
            if (strefa >= 25)
                szansa = (strzal);
            if (strefa < 25 && szansa >= 11)
                szansa = (strzal) * (15);
            if ((strefa < 11 && strefa >= 5) || strefa == 1)
                szansa = (strzal) * (70);
            if (strefa < 5 && strefa >= 2)
                szansa = (strzal) * (90);
        }

        return szansa >= r.nextInt(10000);
    }

}
