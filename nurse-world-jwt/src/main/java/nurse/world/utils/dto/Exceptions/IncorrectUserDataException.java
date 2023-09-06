package nurse.world.utils.dto.Exceptions;

public class IncorrectUserDataException extends Exception{
    public IncorrectUserDataException(String errorMessage) {
        super(errorMessage);
    }
}
