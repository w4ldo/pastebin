package riskid.risk.game.sovelluslogiikka;

import riskid.risk.game.domain.*;
import riskid.risk.game.kayttoliittyma.GUI;

/**
 * Luokka joka toteuttaa pelin kulun.
 */
public class Peli {

    private MapBuilder mb;
    private JoukkojenLiikuttaja jl;
    private LuvunKysyja lk;
    private Kartta map;
    private Pelaaja pelaajaA;
    private Pelaaja pelaajaB;
    private Pelaaja pelaajaC;
    private int pelaajaAVuoro;
    private boolean onkoVoittajaa;
    private GUI gui;

    /**
     * Peli-luokan konstruktori.
     *
     * @param gui gui
     */
    public Peli(GUI gui) {
        this.gui = gui;
        this.mb = new MapBuilder();
        this.jl = new JoukkojenLiikuttaja(gui);
        this.map = mb.buildmap();
        this.pelaajaA = new Pelaaja("Alpha");
        this.pelaajaB = new Pelaaja("Bravo");
        this.pelaajaC = new Pelaaja("Charlie");
        this.pelaajaAVuoro = 1;
        this.onkoVoittajaa = false;
        this.lk = new LuvunKysyja(gui);
    }

    /**
     * Metodi joka käynnistää pelin.
     */
    public void run() {
        gui.setVisible(true);
        gui.teeLisaOsatNakymattomiksi();
        pelinAlustus(38);
        while (!onkoVoittajaa) {
            if (pelaajaAVuoro == 1) {
                uusiVuoro(pelaajaA);
            } else if (pelaajaAVuoro == 2) {
                uusiVuoro(pelaajaB);
            } else if (pelaajaAVuoro == 3) {
                uusiVuoro(pelaajaC);
            }
        }
        kukaVoitti();
    }

    private void pelinAlustus(int joukot) {
        pelaajaA.lisaaReserviin(joukot);
        pelaajaB.lisaaReserviin(joukot);
        pelaajaC.lisaaReserviin(joukot);
        aloitusjoukkojenSijoitus();
        aloitusjoukkojenVahvistus();
    }

    private void aloitusjoukkojenSijoitus() {
        gui.uusiIlmoitus("Aloitusjoukkojen sijoitus");
        while (map.onkoTyhjiaAlueita()) {
            if (gui.onkoTekstia()) {
                int luku = lk.kysyLukua();
                if (!lk.onkoLukuSallittu(luku)) {
                    continue;
                }
                if (pelaajaAVuoro == 1) {
                    if (!jl.sijoitaAloitusJoukkoja(map.getAlue(luku), pelaajaA)) {
                        continue;
                    }
                } else if (pelaajaAVuoro == 2) {
                    if (!jl.sijoitaAloitusJoukkoja(map.getAlue(luku), pelaajaB)) {
                        continue;
                    }
                } else if (!jl.sijoitaAloitusJoukkoja(map.getAlue(luku), pelaajaC)) {
                    continue;
                }
                gui.uusiAlert("");
                gui.uusiIlmoitus("");
                pelaajaAVuoro++;
                if (pelaajaAVuoro == 4) {
                    pelaajaAVuoro = 1;
                }
            }
            gui.paivitaGui(map.tulostaKartta(), pelaajaAVuoro, pelaajaA.getReservi(), pelaajaB.getReservi(), pelaajaC.getReservi(),
                    map.laskePelaajanLisajoukot(pelaajaA), map.laskePelaajanLisajoukot(pelaajaB), map.laskePelaajanLisajoukot(pelaajaC));
        }
    }

    private void aloitusjoukkojenVahvistus() {
        gui.uusiIlmoitus("Aloitusjoukkojen vahvistus. Lisää 2x2 yksikköä");
        while (pelaajaA.getReservi() > 0 || pelaajaB.getReservi() > 0 || pelaajaC.getReservi() > 0) {
            if (gui.onkoTekstia()) {
                if (this.pelaajaAVuoro == 1) {
                    lisajoukkojenSijoitus(pelaajaA, 2, 2);
                } else if (this.pelaajaAVuoro == 2) {
                    lisajoukkojenSijoitus(pelaajaB, 2, 2);
                } else {
                    lisajoukkojenSijoitus(pelaajaC, 2, 2);
                }
                gui.uusiAlert("");
                gui.uusiIlmoitus("");
                pelaajaAVuoro++;
                if (this.pelaajaAVuoro == 4) {
                    this.pelaajaAVuoro = 1;
                }
            }
            gui.paivitaGui(map.tulostaKartta(), pelaajaAVuoro, pelaajaA.getReservi(), pelaajaB.getReservi(), pelaajaC.getReservi(),
                    map.laskePelaajanLisajoukot(pelaajaA), map.laskePelaajanLisajoukot(pelaajaB), map.laskePelaajanLisajoukot(pelaajaC));
        }
    }

    private void lisajoukkojenSijoitus(Pelaaja pelaaja, int montakoKertaa, int montakoKerralla) {
        int i = 0;
        gui.uusiIlmoitus("Lisäjoukkojen sijoitus");
        while (i < montakoKertaa) {
            if (gui.onkoTekstia()) {
                int luku = lk.kysyLukua();
                if (!lk.onkoLukuSallittu(luku)) {
                    continue;
                }
                if (!jl.sijoitaLisajoukkoja(map.getAlue(luku), montakoKerralla, pelaaja)) {
                    continue;
                }
                i++;
            }
            gui.paivitaGui(map.tulostaKartta(), pelaajaAVuoro, pelaajaA.getReservi(), pelaajaB.getReservi(), pelaajaC.getReservi(),
                    map.laskePelaajanLisajoukot(pelaajaA), map.laskePelaajanLisajoukot(pelaajaB), map.laskePelaajanLisajoukot(pelaajaC));
        }
    }

    private void uusiVuoro(Pelaaja pelaaja) {
        gui.uusiAlert("Sijoita 3 lisäjoukkoja kerrallaan");
        pelaaja.lisaaReserviin(map.laskePelaajanLisajoukot(pelaaja));
        lisajoukkojenSijoitus(pelaaja, map.laskePelaajanLisajoukot(pelaaja) / 3, 3);
        gui.teeLisaOsatNakyviksi();
        hyokkaysVaihe(pelaaja);
        if (onkoVoittajaa) {
            return;
        }
        vahvistusVaihe(pelaaja);
        gui.teeLisaOsatNakymattomiksi();
        pelaajaAVuoro++;
        if (pelaajaAVuoro == 4) {
            pelaajaAVuoro = 1;
        }
    }

    private void hyokkaysVaihe(Pelaaja pelaaja) {
        gui.uusiAlert("Hyökkaysvaihe");
        while (true) {
            if (gui.onkoTekstia()) {
                int mista = lk.kysyLukua();
                int mihin = lk.mihinSiirretaan();
                int monellako = lk.montakoSiirretaan();
                if (lk.onkoLukuSallittu(mista)) {
                    jl.hyokkaaTaiValtaa(map.getAlue(mista), map.getAlue(mihin), monellako);
                    if (map.voittaako(pelaaja)) {
                        onkoVoittajaa = true;
                        break;
                    }
                } else if (mista == 999) {
                    break;
                }
            }
            gui.paivitaGui(map.tulostaKartta(), pelaajaAVuoro, pelaajaA.getReservi(), pelaajaB.getReservi(), pelaajaC.getReservi(),
                    map.laskePelaajanLisajoukot(pelaajaA), map.laskePelaajanLisajoukot(pelaajaB), map.laskePelaajanLisajoukot(pelaajaC));
        }
    }

    private void vahvistusVaihe(Pelaaja pelaaja) {
        gui.uusiIlmoitus("Vahvistusvaihe, siirrä enintään kolmea omaa joukkoa");
        int siirtoja = 0;
        while (siirtoja < 3) {
            if (gui.onkoTekstia()) {
                int mista = lk.kysyLukua();
                int mihin = lk.mihinSiirretaan();
                int montako = lk.montakoSiirretaan();
                if (lk.onkoLukuSallittu(mista)) {
                    if (jl.passiivinenLiike(map.getAlue(mista), map.getAlue(mihin), montako)) {
                        siirtoja++;
                    } else {
                        gui.uusiAlert("Siirrä vain viereiselle alueelle.");
                        gui.uusiIlmoitus("Liikuta vähintään yhtä ja jätä vähintään yksi taakse");
                        continue;
                    }
                } else if (mista == 999) {
                    break;
                } else {
                    continue;
                }
            }
            gui.paivitaGui(map.tulostaKartta(), pelaajaAVuoro, pelaajaA.getReservi(), pelaajaB.getReservi(), pelaajaC.getReservi(),
                    map.laskePelaajanLisajoukot(pelaajaA), map.laskePelaajanLisajoukot(pelaajaB), map.laskePelaajanLisajoukot(pelaajaC));
        }
    }

    private void kukaVoitti() {
        gui.paivitaGui(map.tulostaKartta(), pelaajaAVuoro, pelaajaA.getReservi(), pelaajaB.getReservi(), pelaajaC.getReservi(),
                    map.laskePelaajanLisajoukot(pelaajaA), map.laskePelaajanLisajoukot(pelaajaB), map.laskePelaajanLisajoukot(pelaajaC));
        if (pelaajaAVuoro == 1) {
            gui.uusiAlert("peli päättyi :DD");
            gui.uusiIlmoitus("gg, Alpha voitti");
        } else if (pelaajaAVuoro == 2) {
            gui.uusiAlert("peli päättyi :DD");
            gui.uusiIlmoitus("gg, Bravo voitti");
        } else {
            gui.uusiAlert("peli päättyi :DD");
            gui.uusiIlmoitus("gg, Charlie voitti");
        }
    }

}
