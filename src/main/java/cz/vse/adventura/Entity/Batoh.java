package cz.vse.adventura.Entity;

import cz.vse.adventura.Entity.Veci.Klic;
import cz.vse.adventura.Entity.Veci.Lano;
import cz.vse.adventura.Entity.Veci.Loucen;
import cz.vse.adventura.Entity.Veci.Vec;

import java.util.*;

public class Batoh
{
    private int kapacita = 3;
    private Set<Vec> veci = new HashSet<>();
    private Set<Klic> seznamKlicu;

    public Batoh(int kapacita) {
        this.kapacita = kapacita;
        this.seznamKlicu = new HashSet<>();
    }

    public boolean pridejVec(Vec vec) {
        if(veci.size() < kapacita) {
            veci.add(vec);
            return true;
        }
        return false;
    }

    public Vec odeberVec(String nazevVeci) {
        Vec nalezenaVec = null;
        for (Vec vec : veci) {
            if (vec.getNazev().equalsIgnoreCase(nazevVeci)) {
                nalezenaVec = vec;
                break;
            }
        }
        if (nalezenaVec != null) {
            veci.remove(nalezenaVec);
        }
        return nalezenaVec;
    }

    public String obsahBatohu() {
        if (veci.isEmpty()) {
            return "Batoh je prázdný.";
        } else {
            // Převést věci do seznamu
            List<Vec> veciSeznam = new ArrayList<>(veci);
            // Seřadit věci abecedně
            veciSeznam.sort(Comparator.comparing(Vec::getNazev));

            StringBuilder obsahBatohu = new StringBuilder("Obsah batohu:");
            for (int i = 0; i < veciSeznam.size(); i++) {
                obsahBatohu.append(" ").append(veciSeznam.get(i).getNazev());
                if (i < veciSeznam.size() - 1) {
                    obsahBatohu.append(",");
                }
            }
            return obsahBatohu.toString();
        }
    }



    public Vec najdiVec(String nazevVeci) {
        for (Vec vec : veci) {
            if (vec.getNazev().equalsIgnoreCase(nazevVeci)) {
                return vec;
            }
        }
        return null; // Pokud věc není nalezena, vrátí null
    }

    // Metoda pro přidání a odebrání klíče z batohu
    public void pridatKlic(Klic klic) {
        veci.add(klic);
    }

    public boolean obsahujeKlic(Klic klic) {
        return veci.contains(klic);
    }

    public void odeberKlic(Klic klic) {
        veci.remove(klic);
    }

    public boolean obsahujeLoucen() {
        for (Vec vec : veci) {
            if (vec instanceof Loucen) {
                return true;
            }
        }
        return false;
    }

    public boolean obsahujeLano() {
        for (Vec vec : veci) {
            if (vec instanceof Lano) {
                return true;
            }
        }
        return false;
    }
}
