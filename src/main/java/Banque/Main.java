package Banque;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<CompteBancaire> comptes=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        boolean continuer=true ;

        comptes.add(new CompteCourant("98765",2000,"HAJAR",400));
        comptes.add(new CompteEpargne("98765",5000,"AMAL",8));

        while (continuer) {
            System.out.println("\nMenu:");
            System.out.println("1. Afficher les comptes");
            System.out.println("2. Ajouter un compte");
            System.out.println("3. Supprimer un compte");
            System.out.println("4. Effectuer un dépôt");
            System.out.println("5. Effectuer un retrait");
            System.out.println("6. Transférer de l'argent");
            System.out.println("7. Quitter");
            System.out.print("Choisissez une option: ");

            int choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    System.out.println("\nListe des comptes:");
                    for (CompteBancaire compte : comptes) {
                        System.out.println("Numéro: " + compte.getNumero() + ", Titulaire: " + compte.getNom() + ", Solde: " + compte.getSolde() + " MAD");
                    }
                    break;

                case 2:
                    System.out.print("Entrez le numéro du compte: ");
                    String numero = sc.nextLine();
                    System.out.print("Entrez le nom du titulaire: ");
                    String titulaire = sc.nextLine();
                    System.out.print("Entrez le solde initial: ");
                    double solde = sc.nextDouble();
                    System.out.print("Type de compte (1. Courant, 2. Epargne): ");
                    int type = sc.nextInt();

                    if (type == 1) {
                        System.out.print("Entrez le découvert autorisé: ");
                        double decouvert = sc.nextDouble();
                        comptes.add(new CompteCourant(numero, solde, titulaire, decouvert));
                    } else if (type == 2) {
                        System.out.print("Entrez le taux d'intérêt: ");
                        double taux = sc.nextDouble();
                        comptes.add(new CompteEpargne(numero, solde, titulaire, taux));
                    } else {
                        System.out.println("Type de compte invalide.");
                    }
                    break;

                case 3:
                    System.out.print("Entrez le numéro du compte à supprimer: ");
                    String numeroSupprimer = sc.nextLine();
                    boolean compteSupprime = comptes.removeIf(compte -> compte.getNumero().equals(numeroSupprimer));
                    if (compteSupprime) {
                        System.out.println("Compte supprimé avec succès.");
                    } else {
                        System.out.println("Aucun compte trouvé avec ce numéro.");
                    }
                    break;

                case 4:
                    System.out.print("Entrez le numéro du compte pour le verser: ");
                    String numeroDepot = sc.nextLine();
                    System.out.print("Entrez le montant à verser: ");
                    double montantDepot = sc.nextDouble();
                    for (CompteBancaire compte : comptes) {
                        if (compte.getNumero().equals(numeroDepot)) {
                            compte.verser(montantDepot);
                            System.out.println("Dépôt effectué.");
                            break;
                        }
                    }
                    break;

                case 5:
                    System.out.print("Entrez le numéro du compte pour le retrait: ");
                    String numeroRetrait = sc.nextLine();
                    System.out.print("Entrez le montant à retirer: ");
                    double montantRetrait = sc.nextDouble();
                    try {
                        for (CompteBancaire compte : comptes) {
                            if (compte.getNumero().equals(numeroRetrait)) {
                                compte.retirer(montantRetrait);
                                System.out.println("Retrait effectué.");
                                break;
                            }
                        }
                    } catch (SoldeInsuffisantException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Entrez le numéro du compte source: ");
                    String numeroSource = sc.nextLine();
                    System.out.print("Entrez le numéro du compte destinataire: ");
                    String numeroDestinataire = sc.nextLine();
                    System.out.print("Entrez le montant à transférer: ");
                    double montantTransfert = sc.nextDouble();

                    try {
                        CompteBancaire source = null;
                        CompteBancaire destinataire = null;
                        for (CompteBancaire compte : comptes) {
                            if (compte.getNumero().equals(numeroSource)) {
                                source = compte;
                            }
                            if (compte.getNumero().equals(numeroDestinataire)) {
                                destinataire = compte;
                            }
                        }
                        if (source != null && destinataire != null) {
                            source.Transfert(destinataire, montantTransfert);
                            System.out.println("Transfert effectué.");
                        } else {
                            throw new CompteInexistantException("Un des comptes spécifiés est inexistant.");
                        }
                    } catch (SoldeInsuffisantException | CompteInexistantException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 7:
                    continuer = false;
                    break;

                default:
                    System.out.println("Option invalide.");
            }
        }

        sc.close();
    }
}









