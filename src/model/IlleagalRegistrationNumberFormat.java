package model;

public class IlleagalRegistrationNumberFormat extends Exception {

    public IlleagalRegistrationNumberFormat() {
        super();
    }

    public IlleagalRegistrationNumberFormat(String message) {
        super(message);
    }

    public IlleagalRegistrationNumberFormat(String message, Exception exception) {
        super(message, exception);
    }
}
