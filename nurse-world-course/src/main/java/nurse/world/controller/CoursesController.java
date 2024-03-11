package nurse.world.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import nurse.world.model.Course.Content;
import nurse.world.model.Course.Course;
import nurse.world.service.CoursesService;
import nurse.world.utils.Exceptions.CourseAlreadyExistsException;
import nurse.world.utils.Exceptions.CourseNotExistException;
import nurse.world.utils.Exceptions.IncorrectCourseDataException;
import org.eclipse.microprofile.jwt.JsonWebToken;


import java.util.List;

@ApplicationScoped
@Path("/courses")
public class CoursesController {
    @Inject
    JsonWebToken jwt;
    @Inject
    CoursesService coursesService;

    @GET
    @Path("courses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourses(@QueryParam("page") int page){
        return coursesService.getAllCourses(page);
    }

    @POST
    @Path("courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setCourse(Course course){
        try {
            coursesService.addCourse(course);
            return Response.ok().build();
        }catch (IncorrectCourseDataException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }catch (CourseAlreadyExistsException e){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("courses/mainpage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourseMainPage(@QueryParam("courseName") String courseName, @Context SecurityContext ctx) {
        String name2 = jwt.getName();
        String name = ctx.getUserPrincipal().getName();
        try{
            Course course = coursesService.getCourseMainPage(courseName);
            return Response.ok(course).build();
        }catch (CourseNotExistException e){
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("course/content")
    public Response getContentCourseById(@QueryParam("id") double id) {
        try {
            List<Content> contentCourse = coursesService.getContentCourseById(id);
            return Response.ok(contentCourse).build();
        }catch (CourseNotExistException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("user")
    public Response getUserCourses(@Context SecurityContext ctx) {
        String name = ctx.getUserPrincipal().getName();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
