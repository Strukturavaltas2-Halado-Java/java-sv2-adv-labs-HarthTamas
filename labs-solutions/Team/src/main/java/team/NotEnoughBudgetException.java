package team;

public class NotEnoughBudgetException extends RuntimeException{

    public NotEnoughBudgetException(String message) {
        super(message);
    }
}
