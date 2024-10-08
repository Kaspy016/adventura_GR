package cz.vse.adventura.Prikazy;



public class PrikazHadanka implements IPrikaz {

    private static final String NAZEV = "hádanka";
    private SeznamPrikazu platnePrikazy;



    public PrikazHadanka(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    

    @Override
    public String provedPrikaz(String... parametry) {
        return "Když budeš odpovídat na hádanku, napiš: \n"
        + "odpověď (odpověď na hádanku) (mázev místnosti, ke které se hádanka vztahuje).\n"
        + "Například ´odpověď slunce tvrz´ (´slunce´ je odpověď na hádanku, ´tvrz´ je mísnost, která je zamčená) \n";
    }
    

    @Override
      public String getNazev() {
        return NAZEV;
     }

}
