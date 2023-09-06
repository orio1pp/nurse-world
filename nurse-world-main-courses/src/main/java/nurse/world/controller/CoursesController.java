package nurse.world.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nurse.world.model.Course;
import nurse.world.service.CoursesService;
import nurse.world.utils.Exceptions.CourseAlreadyExistsException;
import nurse.world.utils.Exceptions.CourseNotExistException;
import nurse.world.utils.Exceptions.IncorrectCourseDataException;
import nurse.world.utils.dto.CourseMainPageDTO;
import nurse.world.utils.dto.CreateCoursesDTO;


import java.util.List;

@ApplicationScoped
@Path("/courses")
public class CoursesController {

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
    public Response setCourse(CreateCoursesDTO courses){
        try {
            coursesService.addCourse(courses);
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
    public Response getCourseMainPage(@QueryParam("courseName") String courseName){
        try{
            CourseMainPageDTO courseMainPageDTO = coursesService.getCourseMainPage(courseName);
            return Response.ok(courseMainPageDTO).build();
        }catch (CourseNotExistException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
