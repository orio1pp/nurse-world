package nurse.world.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
@Path("/payment")
public class PaymentController {
    
    @POST
    @Path("/user/course")
    public Response userHasPaid(@Context SecurityContext ctx, @QueryParam("courseName") double courseId) { 
        String name = ctx.getUserPrincipal().getName();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
