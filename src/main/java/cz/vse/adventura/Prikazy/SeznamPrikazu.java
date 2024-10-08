package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Prikazy.IPrikaz;

import java.util.*;


public class SeznamPrikazu {
    // mapa pro uložení přípustných příkazů
    private  Map<String, IPrikaz> mapaSPrikazy;
    
   

    public SeznamPrikazu() {
        mapaSPrikazy = new HashMap<>();
    }
    

    public void vlozPrikaz(IPrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(),prikaz);
    }
    

    public IPrikaz vratPrikaz(String retezec) {
        if (mapaSPrikazy.containsKey(retezec)) {
            return mapaSPrikazy.get(retezec);
        }
        else {
            return null;
        }
    }

    public boolean jePlatnyPrikaz(String retezec) {
        return mapaSPrikazy.containsKey(retezec);
    }

    public String vratNazvyPrikazu() {
        List<String> seznamPrikazu = new ArrayList<>(mapaSPrikazy.keySet());
        Collections.sort(seznamPrikazu);
        StringBuilder seznam = new StringBuilder();
        for (int i = 0; i < seznamPrikazu.size(); i++) {
            seznam.append(seznamPrikazu.get(i));
            if (i < seznamPrikazu.size() - 1) {
                seznam.append(", ");
            }
        }
        return seznam.toString();
    }

}

