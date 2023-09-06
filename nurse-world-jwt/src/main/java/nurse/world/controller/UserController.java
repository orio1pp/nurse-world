package nurse.world.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nurse.world.service.UserService;
import nurse.world.utils.dto.Exceptions.IncorrectUserDataException;
import nurse.world.utils.dto.Exceptions.UserAlreadyExistsException;
import nurse.world.utils.dto.UserDTO;

@ApplicationScoped
@Path("/user")
public class UserController {
    @Inject
    UserService userService;

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logUser(UserDTO user){
        String jwt = userService.generateJWT(user);
        return Response.ok(jwt).build();
    }
    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(UserDTO user){
        try{
            userService.registerUser(user);
        }catch (IncorrectUserDataException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }catch (UserAlreadyExistsException e){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.ok().build();
    }
}
