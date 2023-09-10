package nurse.world.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import nurse.world.entity.CourseEntity;
import nurse.world.entity.ImagesEntity;
import nurse.world.utils.Exceptions.CourseNotExistException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Singleton
public class ImageService {

    @Inject
    BlobServiceClient blobServiceClient;

    public boolean uploadBlob(InputStream image, String imageName, String courseName) throws IOException {
        boolean courseExist = CourseEntity.checkCourseExist(courseName);
        if(!courseExist){
            return false;
        }
        associateToCourse(imageName, courseName);
        BlobClient blobClient = blobServiceClient.getBlobContainerClient("nurse-world-back").getBlobClient(imageName);
        blobClient.upload(image);
        return true;
    }

    public InputStream downloadBlob(String blobName) throws CourseNotExistException {
        try {
            BlobClient blobClient = blobServiceClient.getBlobContainerClient("nurse-world-back").getBlobClient(blobName);
            return blobClient.downloadContent().toStream();
        }catch (Exception e){
            throw new CourseNotExistException("Blob not exists");
        }
    }

    private boolean associateToCourse(String imageName, String courseName){
        CourseEntity courseEntity = CourseEntity.getCoursesByTitle(courseName);
        if(courseEntity == null){
            return false;
        }
        List<ImagesEntity> images = courseEntity.getImages();
        ImagesEntity.add(new ImagesEntity(imageName, "nurse-world-back/".concat(imageName)));
        courseEntity.setImages(images);
        return true;
    }
}
