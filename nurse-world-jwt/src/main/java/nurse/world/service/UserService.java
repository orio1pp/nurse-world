package nurse.world.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nurse.world.model.User;
import nurse.world.utils.dto.Exceptions.IncorrectUserDataException;
import nurse.world.utils.dto.Exceptions.UserAlreadyExistsException;
import nurse.world.utils.dto.UserDTO;

@ApplicationScoped
public class UserService {
    @Inject
    private JwtService jwtService;
    public String generateJWT(String username, String password){
        if (!User.existsByUsernameAndPassword(username, password)){
            return null;
        }
        return jwtService.generateJwt("user", username);
    }
    public void registerUser(UserDTO user) throws UserAlreadyExistsException, IncorrectUserDataException  {
        if(User.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsException("User Already Exists");
        }
        if (!User.add(user)){
            throw new IncorrectUserDataException("Not valid user data");
        }
    }
}
