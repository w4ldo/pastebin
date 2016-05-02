package riskid.risk.game.domain;

import java.util.*;

/**
 * Pelaaja-luokka.
 */
public class Pelaaja {

    private String nimi;
    private int reservi;

    /**
     * Pelaaja luokan konstruktori.
     *
     * @param nimi pelaaja nimi
     */
    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.reservi = 0;
    }

    public String getNimi() {
        return nimi;
    }

    /**
     * Reservillä tarkoitetaan pelaajan laudalle sijoittamattomien joukkujen
     * määrää. i.e joukot jotka saadaan pelin alussa ja vuorojen alkaessa.
     *
     * @param maara reserviin lisättävät joukot
     */
    public void lisaaReserviin(int maara) {
//        reservillä tarkoitetaan pelaajan laudalle sijoittamattomien joukkujen määrää
//        i.e joukot jotka saadaan pelin alussa ja vuorojen alkaessa
        this.reservi += maara;
    }

    /**
     * Poistaa reservistä halutun määrän.
     *
     * @see riskid.risk.game.domain.Pelaaja#lisaaReserviin(int)
     *
     * @param maara reserviin lisättävät joukot
     */
    public void poistaReservista(int maara) {
        this.reservi -= maara;
    }

    public int getReservi() {
        return reservi;
    }

    @Override
    public String toString() {
        return this.getNimi();
    }

}
