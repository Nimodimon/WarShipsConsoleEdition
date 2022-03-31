package warships.exceptions;

public class CellOutOfBoundException extends Exception{
    public CellOutOfBoundException(String message){
        super(message);
    }
}