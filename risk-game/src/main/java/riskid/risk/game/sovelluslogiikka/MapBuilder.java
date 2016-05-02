package riskid.risk.game.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import riskid.risk.game.domain.*;

/**
 * Luokka joka rakentaa pelilaudan.
 */
public class MapBuilder {

    /**
     * Konstruktori.
     */
    public MapBuilder() {
        //tämä luokka rakentaa pelilaudan. Lauta on staattinen 42 aluetta, 6 mannerta
        //tästä syystä esim alueiden viereisyydet ja mantereiden rakenteet on kovakoodattu metodiin
    }

    /**
     * Rakentaa ennalta määritellyn kartan.
     *
     * @return valmiin kartan
     */
    public Kartta buildmap() {
        List<Alue> alueet = this.buildAlueet();
        this.asetaViereiset(alueet);
        List<Manner> mantereet = this.buildMantereet(alueet);
        Kartta map = this.buildKartta(mantereet);
        return map;
    }

    private List<Alue> buildAlueet() {
        List<Alue> lista = new ArrayList<>();
        for (int i = 1; i <= 42; i++) {
            lista.add(new Alue(i));
        }
        return lista;
    }

    private List<Manner> buildMantereet(List<Alue> lista) {
        List<Manner> mantereet = new ArrayList<>();
        mantereet.add(new Manner(5, "P-Amerikka",
                lista.get(0), lista.get(1), lista.get(2), lista.get(3), lista.get(4),
                lista.get(5), lista.get(6), lista.get(7), lista.get(8)));
        mantereet.add(new Manner(2, "E-Amerikka",
                lista.get(9), lista.get(10), lista.get(11), lista.get(12)));
        mantereet.add(new Manner(5, "Eurooppa",
                lista.get(13), lista.get(14), lista.get(15), lista.get(16), lista.get(17),
                lista.get(18), lista.get(19)));
        mantereet.add(new Manner(3, "Afrikka",
                lista.get(20), lista.get(21), lista.get(22), lista.get(23), lista.get(24),
                lista.get(25)));
        mantereet.add(new Manner(7, "Aasia",
                lista.get(26), lista.get(27), lista.get(28), lista.get(29), lista.get(30),
                lista.get(31), lista.get(32), lista.get(33), lista.get(34), lista.get(35),
                lista.get(36), lista.get(37)));
        mantereet.add(new Manner(2, "Australia",
                lista.get(38), lista.get(39), lista.get(40), lista.get(41)));
        return mantereet;
    }

    private Kartta buildKartta(List<Manner> lista) {
        Kartta map = new Kartta(lista.get(0), lista.get(1), lista.get(2),
                lista.get(3), lista.get(4), lista.get(5));
        return map;
    }

    private void asetaViereiset(List<Alue> lista) {
        lista.get(0).setViereiset(lista.get(29), lista.get(1), lista.get(3));
        lista.get(1).setViereiset(lista.get(0), lista.get(2), lista.get(3), lista.get(4));
        lista.get(2).setViereiset(lista.get(1), lista.get(4), lista.get(5), lista.get(13));
        lista.get(3).setViereiset(lista.get(0), lista.get(1), lista.get(4), lista.get(6));
        lista.get(4).setViereiset(lista.get(1), lista.get(2), lista.get(3), lista.get(5), lista.get(6), lista.get(7));
        lista.get(5).setViereiset(lista.get(2), lista.get(4), lista.get(7));
        lista.get(6).setViereiset(lista.get(3), lista.get(4), lista.get(7), lista.get(8));
        lista.get(7).setViereiset(lista.get(4), lista.get(5), lista.get(6), lista.get(8));
        lista.get(8).setViereiset(lista.get(6), lista.get(7), lista.get(9));

        lista.get(9).setViereiset(lista.get(8), lista.get(10), lista.get(11));
        lista.get(10).setViereiset(lista.get(9), lista.get(11), lista.get(12));
        lista.get(11).setViereiset(lista.get(9), lista.get(10), lista.get(12), lista.get(20));
        lista.get(12).setViereiset(lista.get(10), lista.get(11));

        lista.get(13).setViereiset(lista.get(2), lista.get(14), lista.get(16));
        lista.get(14).setViereiset(lista.get(13), lista.get(15), lista.get(16), lista.get(17));
        lista.get(15).setViereiset(lista.get(14), lista.get(17), lista.get(19), lista.get(35), lista.get(31), lista.get(26));
        lista.get(16).setViereiset(lista.get(13), lista.get(14), lista.get(17), lista.get(18));
        lista.get(17).setViereiset(lista.get(14), lista.get(15), lista.get(16), lista.get(18), lista.get(19));
        lista.get(18).setViereiset(lista.get(16), lista.get(17), lista.get(19), lista.get(20));
        lista.get(19).setViereiset(lista.get(15), lista.get(17), lista.get(18), lista.get(20), lista.get(21), lista.get(35));

        lista.get(20).setViereiset(lista.get(11), lista.get(18), lista.get(21), lista.get(19), lista.get(23), lista.get(22));
        lista.get(21).setViereiset(lista.get(20), lista.get(19), lista.get(35), lista.get(23));
        lista.get(22).setViereiset(lista.get(20), lista.get(23), lista.get(24));
        lista.get(23).setViereiset(lista.get(20), lista.get(21), lista.get(22), lista.get(24), lista.get(25), lista.get(35));
        lista.get(24).setViereiset(lista.get(22), lista.get(23), lista.get(25));
        lista.get(25).setViereiset(lista.get(23), lista.get(24));

        lista.get(26).setViereiset(lista.get(15), lista.get(27), lista.get(31), lista.get(32));
        lista.get(27).setViereiset(lista.get(26), lista.get(28), lista.get(30), lista.get(32), lista.get(33));
        lista.get(28).setViereiset(lista.get(27), lista.get(30), lista.get(29));
        lista.get(29).setViereiset(lista.get(28), lista.get(30), lista.get(33), lista.get(34), lista.get(0));
        lista.get(30).setViereiset(lista.get(27), lista.get(28), lista.get(29), lista.get(33));
        lista.get(31).setViereiset(lista.get(15), lista.get(26), lista.get(32), lista.get(35), lista.get(36));
        lista.get(32).setViereiset(lista.get(26), lista.get(27), lista.get(31), lista.get(33), lista.get(36), lista.get(37));
        lista.get(33).setViereiset(lista.get(27), lista.get(29), lista.get(30), lista.get(32), lista.get(34));
        lista.get(34).setViereiset(lista.get(33), lista.get(29));
        lista.get(35).setViereiset(lista.get(15), lista.get(19), lista.get(21), lista.get(23), lista.get(31), lista.get(36));
        lista.get(36).setViereiset(lista.get(31), lista.get(32), lista.get(35), lista.get(37));
        lista.get(37).setViereiset(lista.get(32), lista.get(36), lista.get(38));

        lista.get(38).setViereiset(lista.get(37), lista.get(39), lista.get(40));
        lista.get(39).setViereiset(lista.get(38), lista.get(40), lista.get(41));
        lista.get(40).setViereiset(lista.get(38), lista.get(39), lista.get(41));
        lista.get(41).setViereiset(lista.get(40), lista.get(39));
    }

}
