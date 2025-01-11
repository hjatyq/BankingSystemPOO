package Banque;

public class CompteBancaire {
    private String numero;
    private double solde;
    private String nom;

    public CompteBancaire(){}

    public CompteBancaire(String numero, double solde, String nom) {
        this.numero = numero;
        this.solde = solde;
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public double getSolde() {
        return solde;
    }

    public String getNom() {
        return nom;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void verser(double montant){
        if (montant>0){
            solde +=montant;
            System.out.println("le dépot effectué avec succés :" +getSolde());
        }

    }
    public void retirer(double montant) throws  SoldeInsuffisantException{
       if (montant > solde)throw new SoldeInsuffisantException("Solde insuffisant pour effectuer un retrait !!!");
        solde = solde - montant;
        System.out.println("Retrait effectué avec succès");
    }


    public void afficherSolde(){
        System.out.println("Numero de compte: " + getNumero() + " de solde: " + getSolde());
    }

    public void Transfert(CompteBancaire NumCompte ,double montant) throws SoldeInsuffisantException,CompteInexistantException{
        if(NumCompte==null){
            throw new CompteInexistantException("Compte inexistant");
        }
        retirer(montant);
        NumCompte.verser(montant);
        System.out.println("Transfert du solde " +montant+"effectuer avec succes vers le compte "+NumCompte);
    }





}
