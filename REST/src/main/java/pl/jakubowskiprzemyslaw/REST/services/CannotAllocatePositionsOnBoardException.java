package pl.jakubowskiprzemyslaw.REST.services;

class CannotAllocatePositionsOnBoardException extends Exception {
    private static final long serialVersionUID = 4168864884664039291L;

    CannotAllocatePositionsOnBoardException(String message) {
        super(message);
    }
}
