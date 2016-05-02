package riskid.risk.game.domain;

import java.util.*;

/**
 * Alue kuvaa pelilaudan yksittäistä ruutua.
 */
public class Alue {

    private Pelaaja hallitsija;
    private final int id;
    private Map<Integer, Alue> viereiset;
    private Yksikko yksikko;

    /**
     * Alueen konstruktori. Saa parametrina alueen id.
     *
     * @param id alueen id
     *
     */
    public Alue(int id) {
        this.viereiset = new HashMap<>();
        this.id = id;
    }

    public void setHallitsija(Pelaaja hallitsija) {
        this.hallitsija = hallitsija;
    }

    public void setYksikko(Yksikko unit) {
        this.yksikko = unit;
    }

    /**
     * Asettaa alueelle viereiset alueet.
     *
     * @param alue viereiset alueet
     *
     */
    public void setViereiset(Alue... alue) {
        for (Alue a : alue) {
            this.viereiset.put(a.getId(), a);
        }
    }

    public Yksikko getYksikko() {
        return yksikko;
    }

    public int getYksikonVahvuus() {
        return this.yksikko.getVahvuus();
    }

    public Pelaaja getHallitsija() {
        return hallitsija;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Alue> getViereiset() {
        return viereiset;
    }

    /**
     * Paluttaa true jos parametrina saatu alue on tämän alueen vieressä.
     *
     * @param alue tarkastuksen kohde
     * @return alueet toString
     */
    public boolean onkoViereinen(Alue alue) {
        try {
            if (this.getViereiset().containsKey(alue.getId())) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public String toString() {
        if (this.hallitsija == null) {
            return "";
        }
        return this.hallitsija.getNimi() + ":" + this.yksikko.getVahvuus();
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Alue alue = (Alue) o;
        if (alue.getId() != this.getId()) {
            return false;
        }
        return true;
    }
}
