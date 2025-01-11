package Banque;

public class SoldeInsuffisantException extends Exception {
    public SoldeInsuffisantException(String msg) {
        super(msg);
    }
}
