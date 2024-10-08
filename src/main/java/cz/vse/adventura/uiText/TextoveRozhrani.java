package cz.vse.adventura.uiText;


import java.util.Scanner;

import cz.vse.adventura.logika.IHra;



public class TextoveRozhrani {
    private IHra hra;

    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }


    public void hraj() {
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }

    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
