package Banque;

public class CompteCourant extends CompteBancaire{
    private double decouvertAutorise;


    public CompteCourant(double decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }

    public CompteCourant(String numero, double solde, String nom, double decouvertAutorise) {
        super(numero, solde, nom);
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public void retirer(double montant) throws SoldeInsuffisantException {
        if(montant>getSolde()+decouvertAutorise){
            super.retirer(montant);
            System.out.println("Compte courant retirer");

        }
    }
}
