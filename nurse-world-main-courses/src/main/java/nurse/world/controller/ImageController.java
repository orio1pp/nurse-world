package nurse.world.controller;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/quarkus-azure-storage-blob")
@ApplicationScoped
public class ImageController {
    @Inject
    BlobServiceClient blobServiceClient;

    @POST
    public Response uploadBlob(@RequestParam("productImage") InputStream image, @RequestParam("imageName") String imageName) throws IOException {
        String fullBlobName = "nurseworld/".concat(imageName);
        BlobClient blobClient = blobServiceClient.getBlobContainerClient("nurse-world-back").getBlobClient("nurseworld");
        blobClient.upload(image);
        return Response.status(CREATED).build();
    }

    @GET
    public InputStream downloadBlob() {
        BlobClient blobClient = blobServiceClient.getBlobContainerClient("nurse-world-back").getBlobClient("images.jpg");
        return blobClient.downloadContent().toStream();
    }


}
