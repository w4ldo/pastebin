package riskid.risk.game.sovelluslogiikka;

import java.util.*;
import riskid.risk.game.domain.*;

/**
 * Luokka joka arpoo taistelun lopputuloksen.
 */
public class Taistelusimulaattori {

    private Random random;

    /**
     * Luokan konstruktori.
     */
    public Taistelusimulaattori() {
        this.random = new Random();
    }

    /**
     * Hyökkääjä heittää 1-3 nopppaa, puolustaja 1-2 noppaa. Puolustaja voittaa
     * tasapelit, noppia heitetään kunnes toinen armeija on tuhottu
     *
     * @param mista mista hyökätään
     * @param mihin mihin hyökätään
     * @param montako montako hyökkääjää
     */
    public void taistele(Alue mista, Alue mihin, int montako) {
        int hyokkaaja = montako;
        int puolustaja = mihin.getYksikonVahvuus();
        while (hyokkaaja > 0 && puolustaja > 0) {
            List<Integer> hyokkaajanHeitot = new ArrayList<>();
            List<Integer> puolustajanHeitot = new ArrayList<>();
            if (hyokkaaja >= 3) {
                hyokkaajanHeitot.add(this.random.nextInt(6));
                hyokkaajanHeitot.add(this.random.nextInt(6));
                hyokkaajanHeitot.add(this.random.nextInt(6));
            } else if (hyokkaaja == 2) {
                hyokkaajanHeitot.add(this.random.nextInt(6));
                hyokkaajanHeitot.add(this.random.nextInt(6));
            } else if (hyokkaaja == 1) {
                hyokkaajanHeitot.add(this.random.nextInt(6));
            }
            if (puolustaja >= 2) {
                puolustajanHeitot.add(this.random.nextInt(6));
                puolustajanHeitot.add(this.random.nextInt(6));
            } else if (puolustaja == 1) {
                puolustajanHeitot.add(this.random.nextInt(6));
            }
            hyokkaajanHeitot.sort(null);
            puolustajanHeitot.sort(null);
            if (hyokkaaja > 1 && puolustaja > 1) {
                if (hyokkaajanHeitot.get(0) < puolustajanHeitot.get(0)) {
                    puolustaja--;
                } else {
                    hyokkaaja--;
                }
                if (hyokkaajanHeitot.get(1) < puolustajanHeitot.get(1)) {
                    puolustaja--;
                } else {
                    hyokkaaja--;
                }
            } else if (hyokkaajanHeitot.get(0) < puolustajanHeitot.get(0)) {
                puolustaja--;
            } else {
                hyokkaaja--;
            }
        }
        if (hyokkaaja > 0) {
            this.hyokkaajaVoittaa(mista, mihin, montako, hyokkaaja);
        } else {
            this.puolustajaVoittaa(mista, mihin, montako, puolustaja);
        }
    }

    private void hyokkaajaVoittaa(Alue mista, Alue mihin, int montako, int hyokkaajat) {
        mista.getYksikko().setVahvuus(mista.getYksikonVahvuus() - montako);
        mihin.setYksikko(new Yksikko(mista.getHallitsija()));
        mihin.getYksikko().setVahvuus(hyokkaajat);
        mihin.setHallitsija(mista.getHallitsija());
    }

    private void puolustajaVoittaa(Alue mista, Alue mihin, int montako, int puolustajat) {
        mista.getYksikko().setVahvuus(mista.getYksikonVahvuus() - montako);
        mihin.getYksikko().setVahvuus(puolustajat);
    }

}
