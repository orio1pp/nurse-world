package nurse.world.utils.dto.Exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
