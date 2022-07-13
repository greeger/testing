package myExceptions;

public class PositionOutOfBoundsException extends RuntimeException {
    public PositionOutOfBoundsException() {
        super();
    }
    public PositionOutOfBoundsException(String s) {
        super(s);
    }
    public PositionOutOfBoundsException(int index) {
        super("position " + index+" is kal");
    }
}
