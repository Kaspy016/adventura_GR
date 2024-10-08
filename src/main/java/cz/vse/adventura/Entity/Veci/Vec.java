package cz.vse.adventura.Entity.Veci;

public class Vec
{
    private String nazev;
    private boolean prenositelna;
    private String text;

    public Vec(String nazev, boolean prenositelna, String text) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.text = text;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public boolean isPrenositelna() {
        return prenositelna;
    }

    public void setPrenositelna(String prenositelna) {
        this.prenositelna = true;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
