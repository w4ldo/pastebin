package riskid.risk.game.domain;

import java.util.*;

/**
 * Manner koostuu useista alueesta. Hallitsemalla kaikkia alueita mantereella
 * saa mantereen bonuksen verran lisäjoukkoja
 */
public class Manner {

    private final Map<Integer, Alue> alueet;
    private final String nimi;
    private final int bonus;

    /**
     * Manner-luokan konstruktori. Saa parametreina mantereen bonuksen, nimi ja
     * alueet.
     *
     * @param bonus mannner-bonus
     * @param nimi nimi
     * @param alue alueet
     *
     */
    public Manner(int bonus, String nimi, Alue... alue) {
        this.nimi = nimi;
        this.bonus = bonus;
        this.alueet = new HashMap<>();
        for (Alue a : alue) {
            this.alueet.put(a.getId(), a);
        }
    }

    public Map<Integer, Alue> getAlueet() {
        return alueet;
    }

    /**
     * Metodi tarkistaa saako annettu pelaaja bonuksen tästä mantereesta. i.e.
     * hallitseeko pelaaja koko mannerta.
     *
     * @param pelaaja tarkistuksen kohde
     *
     * @return true/false
     */
    public boolean onkoBonus(Pelaaja pelaaja) {
        for (Alue a : this.getAlueet().values()) {
            if (a.getHallitsija() != pelaaja) {
                return false;
            }
        }
        return true;
    }

    /**
     * Paluttaa haetun alueen. Palauttaa null, jos aluetta ei löydy.
     *
     * @param i haetun alueen id
     * @return haettu alue
     */
    public Alue getAlue(int i) {
        if (this.alueet.containsKey(i)) {
            return this.alueet.get(i);
        }
        return null;
    }

    public String getNimi() {
        return nimi;
    }

    public int getBonus() {
        return bonus;
    }

}
