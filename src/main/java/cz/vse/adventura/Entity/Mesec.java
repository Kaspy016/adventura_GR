package cz.vse.adventura.Entity;

public class Mesec {
    private int zlataky;

    public Mesec() {
        this.zlataky = 0;
    }

    public void pridejZlataky(int pocet) {
        zlataky += pocet;
    }

    // Metoda pro výpočet součtu zlaťáků
    public int pocetZlataku() {
        return zlataky;
    }
}
