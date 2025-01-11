package Banque;

public class CompteEpargne extends CompteBancaire{
    private double TauxInteret;

    public CompteEpargne(double tauxInteret) {
        TauxInteret = tauxInteret;
    }

    public CompteEpargne(String numero, double solde, String nom, double tauxInteret) {
        super(numero, solde, nom);
        this.TauxInteret = tauxInteret;
    }

    public void genererInterets() {
        double interet=getSolde()*TauxInteret/100;
        System.out.println("interet: "+interet);
    }
}
