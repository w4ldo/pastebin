package riskid.risk.game.sovelluslogiikka;

import riskid.risk.game.domain.*;
import riskid.risk.game.kayttoliittyma.GUI;

/**
 * JoukkojenLiikuttaja tekee joukkojen siirtämiseen vaadittavia toimenpiteitä.
 */
public class JoukkojenLiikuttaja {

    private Taistelusimulaattori ts;
    private GUI gui;

    /**
     * Luokan konstruktori.
     *
     * @param gui gui
     */
    public JoukkojenLiikuttaja(GUI gui) {
        this.ts = new Taistelusimulaattori();
        this.gui = gui;
    }

    /**
     * Metodi joukkojen sijoittamiselle tyhjään ruutuun. Palauttaa true jos
     * operaatio onnistuu.
     *
     * @param minne haluttu alue
     * @param kenen yksikön pelaaja
     * @return true/false
     */
    public boolean sijoitaAloitusJoukkoja(Alue minne, Pelaaja kenen) {
        if (minne.getYksikko() == null) {
            minne.setYksikko(new Yksikko(kenen));
            minne.setHallitsija(kenen);
            kenen.poistaReservista(1);
            return true;
        } else {
            gui.uusiAlert("Laiton siirto: sijoita joukkoja tyhjään ruutuun");
        }

        return false;
    }

    /**
     * Metodi lisäjoukkojen sijoittamiselle hallittuun ruutuun. Palauttaa true
     * jos operaatio onnistuu.
     *
     * @param minne haluttu alue
     * @param montako sijoitettava määrä
     * @param kenen yksikön pelaaja
     * @return true/false
     */
    public boolean sijoitaLisajoukkoja(Alue minne, int montako, Pelaaja kenen) {

        if (minne.getHallitsija() != null && minne.getHallitsija().equals(kenen)) {
            minne.getYksikko().setVahvuus(minne.getYksikonVahvuus() + montako);
            kenen.poistaReservista(montako);
            return true;
        } else {
            gui.uusiAlert("Laiton siirto: lisää joukkoja omille alueillesi");
        }
        return false;
    }

    /**
     * Metodi joukkojen siirtämiselle vihollisen hallitsemaan ruutuun.
     *
     * @param mista mistä hyökätään
     * @param mihin haluttu alue
     * @param montako hyökkääjien vahvuus
     *
     */
    public void hyokkaaTaiValtaa(Alue mista, Alue mihin, int montako) {
        if (mista.onkoViereinen(mihin)) {
            if (montako >= 1 && montako < mista.getYksikko().getVahvuus()) {
                if (!mista.getHallitsija().equals(mihin.getHallitsija())) {
                    //taistellaaaaaaaaaaaaaaaan :DDD
                    liikutaVihollisAlueelle(mista, mihin, montako);
                } else {
                    gui.uusiAlert("Laiton siirto: valtaa vihollisen alueita");
                }
            } else {
                gui.uusiAlert("Laiton siirto: liikuta vähintään yhtä ja jätä vähintään yksi taakse");
            }
        } else {
            gui.uusiAlert("Laitoin siirto: alueet eivä ole vierekkäin");
        }
    }

    /**
     * Metodi joukkojen sijoittamiselle omaan ruutuun. Palauttaa true jos
     * operaatio onnistuu.
     *
     * @param mista mistä siirretän
     * @param mihin mihin ruutuun
     * @param montako haluttu määrä
     * @return true/false
     */
    public boolean passiivinenLiike(Alue mista, Alue mihin, int montako) {
        if (mista.onkoViereinen(mihin)) {
            if (montako >= 1 && montako < mista.getYksikko().getVahvuus()) {
                if (mista.getHallitsija().equals(mihin.getHallitsija())) {
                    liikutaOmalleAlueelle(mista, mihin, montako);
                    return true;
                } else {
                    gui.uusiAlert("Laiton siirto: siirrä omalle alueelle");
                }
            } else {
                gui.uusiAlert("Laiton siirto: liikuta vähintään yhtä ja jätä vähintään yksi taakse");
            }
        } else {
            gui.uusiAlert("Laitoin siirto: alueet eivä ole vierekkäin");
        }
        return false;
    }

    private void liikutaOmalleAlueelle(Alue mista, Alue mihin, int montako) {
        mista.getYksikko().setVahvuus(mista.getYksikonVahvuus() - montako);
        mihin.getYksikko().setVahvuus(mihin.getYksikonVahvuus() + montako);
    }

    private void liikutaVihollisAlueelle(Alue mista, Alue mihin, int montako) {
        ts.taistele(mista, mihin, montako);
    }

}
