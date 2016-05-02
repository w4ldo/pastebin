package riskid.risk.game;

import riskid.risk.game.kayttoliittyma.GUI;
import riskid.risk.game.sovelluslogiikka.*;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI();
        Peli risk = new Peli(gui);

        risk.run();

    }

}
