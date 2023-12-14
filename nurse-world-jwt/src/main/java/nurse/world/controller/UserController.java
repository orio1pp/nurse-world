package nurse.world.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nurse.world.service.UserService;
import nurse.world.utils.dto.Exceptions.IncorrectUserDataException;
import nurse.world.utils.dto.Exceptions.UserAlreadyExistsException;
import nurse.world.utils.dto.UserDTO;
import org.springframework.web.bind.annotation.CrossOrigin;

@ApplicationScoped
@CrossOrigin
@Path("/user")
public class UserController {
    @Inject
    UserService userService;
    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logUser(@QueryParam("username") String username, @QueryParam("password") String password) {
        String jwt = userService.generateJWT(username, password);
        if (jwt == null) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.ok(jwt).build();
    }
    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(UserDTO user) {
        try{
            userService.registerUser(user);
        }catch (IncorrectUserDataException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }catch (UserAlreadyExistsException e){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.ok().build();
    }
}
