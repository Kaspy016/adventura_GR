package cz.vse.adventura.Prikazy;



public class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "nápověda";
    private SeznamPrikazu platnePrikazy;
    
    

    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    

    @Override
    public String provedPrikaz(String... parametry) {
        return "Tvým úkolem je najít 100 zlaťáků, najít klíč k bráně a utéct.\n"
                + "\n"
                + "V této hře neexistují velká písmena, všechno pište malými písmeny.\n"
                + "\n"
                + "Můžeš zadat tyto příkazy:\n"
                + platnePrikazy.vratNazvyPrikazu();
    }
    

    @Override
      public String getNazev() {
        return NAZEV;
     }

}
