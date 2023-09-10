package nurse.world.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import nurse.world.service.ImageService;
import nurse.world.utils.Exceptions.CourseNotExistException;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/image")
@ApplicationScoped
public class ImageController {
    @Inject
    ImageService imageService;

    @POST
    public Response uploadBlob(@QueryParam("blobName") String blobName, @QueryParam("courseName") String courseName, @RequestParam("imageName") InputStream blob) throws IOException {
        boolean result = imageService.uploadBlob(blob, blobName, courseName);
        if(result){
            return Response.status(CREATED).build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    public Response downloadBlob(@QueryParam("blobName") String blobName) {
        try {
            InputStream blob = imageService.downloadBlob(blobName);
            return Response.ok(blob).build();
        }catch (CourseNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}


