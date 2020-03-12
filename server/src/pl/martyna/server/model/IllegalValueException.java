package pl.martyna.server.model;

/**
 * Signals an inappropriate value of settings
 * @author Martyna Drabinska
 * @version 1.0
 */
public class IllegalValueException extends Exception {

    public IllegalValueException(){
        super();
    }

    @Override
    public String getMessage(){
        return "Illegal value of settings";
    }
}
