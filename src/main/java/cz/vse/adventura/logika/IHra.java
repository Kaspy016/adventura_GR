/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.adventura.logika;


import cz.vse.adventura.PredmetPozorovani;

public interface IHra extends PredmetPozorovani
{

    public String vratUvitani();
    

    public String vratEpilog();
    

     public boolean konecHry();
     

     public String zpracujPrikaz(String radek);
   
    

     public HerniPlan getHerniPlan();

}
