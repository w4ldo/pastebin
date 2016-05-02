package riskid.risk.game.domain;

import java.util.*;

/**
 * Kartta koostuu useista Mantereista. Kartta kuvastaa pelilautaa.
 */
public class Kartta {

    private final Map<String, Manner> mantereet;

    /**
     * Kartan konstruktori saa parametreina mantereet.
     *
     * @param manner kartan mantereet
     */
    public Kartta(Manner... manner) {
        this.mantereet = new HashMap<>();
        for (Manner m : manner) {
            this.mantereet.put(m.getNimi(), m);
        }
    }

    public Map<String, Manner> getMantereet() {
        return mantereet;
    }

    /**
     * Paluttaa haetun alueen. Palauttaa null, jos aluetta ei löydy.
     *
     * @param i haetun alueen id
     * @return haettu alue
     */
    public Alue getAlue(int i) {
        if (i >= 1 && i <= 9) {
            return this.mantereet.get("P-Amerikka").getAlue(i);
        } else if (i >= 10 && i <= 13) {
            return this.mantereet.get("E-Amerikka").getAlue(i);
        } else if (i >= 14 && i <= 20) {
            return this.mantereet.get("Eurooppa").getAlue(i);
        } else if (i >= 21 && i <= 26) {
            return this.mantereet.get("Afrikka").getAlue(i);
        } else if (i >= 27 && i <= 38) {
            return this.mantereet.get("Aasia").getAlue(i);
        } else if (i >= 39 && i <= 42) {
            return this.mantereet.get("Australia").getAlue(i);
        } else {
            return null;
        }
    }

    /**
     * Metodi tarkistaa onko kartalla alueita, joille ei ole asetettu joukkoja.
     *
     * @return true/false
     */
    public boolean onkoTyhjiaAlueita() {
        int i = 0;
        for (Manner m : this.mantereet.values()) {
            for (Alue a : m.getAlueet().values()) {
                i++;
            }
        }
        for (int a = 1; a <= i; a++) {
            if (this.getAlue(a).getYksikko() == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi tarkistaa paljonko pelaaja saa lisäjoukkoja vuoron alussa.
     * tarkistukseen kuuluu pelaajan hallitsemat alueet ja mannerbonukset.
     *
     *
     * @param pelaaja tarkistuksen kohde
     * @see domain.Manner#onkoBonus(Pelaaja)
     * @see
     * riskid.risk.game.domain.Manner#onkoBonus(riskid.risk.game.domain.Pelaaja)
     * @see
     * riskid.risk.game.domain.Kartta#laskePelaajanAlueet(riskid.risk.game.domain.Pelaaja)
     * @return saatavat lisäjoukot
     */
    public int laskePelaajanLisajoukot(Pelaaja pelaaja) {
        int joukot = 0;
        joukot += laskePelaajanAlueet(pelaaja) / 3;
        for (Manner m : this.getMantereet().values()) {
            if (m.onkoBonus(pelaaja)) {
                joukot += m.getBonus();
            }
        }
        joukot = joukot / 3;
        joukot = joukot * 3;
        return joukot;
    }

    /**
     * Metodi tarkistaa voittaako kyseinen pelaaja. i.e. hallitseeko kaikkia
     * alueita kartalla.
     *
     * @param pelaaja tarkistuksen kohde
     * @return true/false
     */
    public boolean voittaako(Pelaaja pelaaja) {
        for (Manner m : this.mantereet.values()) {
            if (!m.onkoBonus(pelaaja)) {
                return false;
            }
        }
        return true;
    }

    private int laskePelaajanAlueet(Pelaaja pelaaja) {
        int i = 0;
        for (Manner m : this.mantereet.values()) {
            for (Alue a : m.getAlueet().values()) {
                if (a.getHallitsija() == pelaaja) {
                    i++;
                }
            }
        }
        return i;
    }

    /**
     * Palauttaa listan joka sisältää kaikkien alueiden toString().
     *
     * @return alueet toString
     */
    public List<String> tulostaKartta() {
        List<String> lista = new ArrayList<>();
        for (int i = 1; i <= 42; i++) {
            lista.add(getAlue(i).toString());
        }
        return lista;
    }

}
