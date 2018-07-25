package pl.jakubowskiprzemyslaw.REST.services;

public class CannotAllocatePositionsOnBoardException extends Exception {
    private static final long serialVersionUID = 4168864884664039291L;

    public CannotAllocatePositionsOnBoardException(String message) {
        super(message);
    }
}
