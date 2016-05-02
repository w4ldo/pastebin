package riskid.risk.game.sovelluslogiikka;

import riskid.risk.game.kayttoliittyma.GUI;

/**
 * LuvunKysyja lukee käyttäjän syötteet.
 */
public class LuvunKysyja {

    private GUI gui;

    /**
     * Luokan konstruktori.
     *
     * @param gui gui
     */
    public LuvunKysyja(GUI gui) {
        this.gui = gui;
    }

    /**
     * Lukee syötteen ensimmäisestä tekstikentästä.
     *
     * @return int luku
     */
    public int kysyLukua() {
        int luku = 0;
        try {
            luku = Integer.parseInt(gui.getTekstiKentta(1));
        } catch (Exception e) {
            gui.uusiAlert("Ei kirjaimii senki hessu vaan numeroita.");
            String nada = gui.getTekstiKentta(1);
        }
        return luku;
    }

    /**
     * Palauttaa true jos luku on sallittu.
     *
     * @param luku kysytty luku
     * @return true/false
     */
    public boolean onkoLukuSallittu(int luku) {
        if (luku > 0 && luku < 43) {
            return true;
        } else if (luku == 999) {
            return false;
        }
        gui.uusiIlmoitus("Valitse alue 1-42");
        return false;
    }

    /**
     * Lukee syötteen toisesta tekstikentästä.
     *
     * @return int luku
     */
    public int mihinSiirretaan() {
        int mihin = 0;
        try {
            mihin = Integer.parseInt(gui.getTekstiKentta(2));
        } catch (Exception e) {
            gui.uusiAlert("Ei kirjaimii senki hessu vaan numeroita.");
            String nada = gui.getTekstiKentta(2);
        }
        return mihin;
    }

    /**
     * Lukee syötteen kolmannesta tekstikentästä.
     *
     * @return int luku
     */
    public int montakoSiirretaan() {
        int monellako = 0;
        try {
            monellako = Integer.parseInt(gui.getTekstiKentta(3));
        } catch (Exception e) {
            gui.uusiAlert("Ei kirjaimii senki hessu vaan numeroita.");
            String nada = gui.getTekstiKentta(3);
        }
        return monellako;
    }

}
