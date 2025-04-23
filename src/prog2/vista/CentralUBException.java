package prog2.vista;

public class CentralUBException extends RuntimeException {

    /**
     * Constructor que crea una nova excepció amb un missatge específic
     * @param message Missatge descriptiu de l'error
     */
    public CentralUBException(String message) {
        System.out.println(message);
    }
}
