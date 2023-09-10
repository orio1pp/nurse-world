package nurse.world.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity extends PanacheEntity {

    private String title;

    @OneToMany
    private List<ImagesEntity> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<ImagesEntity> getImages() {
        return images;
    }

    public void setImages(List<ImagesEntity> images) {
        this.images = images;
    }

    @Transactional
    public static boolean checkCourseExist(String courseTitle){
        try {
            CourseEntity courseEntity = CourseEntity.find("title", courseTitle).firstResult();
            if(courseEntity == null)
                return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public static CourseEntity getCoursesByTitle(String title){
        if(title == null){
            return null;
        }
        try{
            CourseEntity courseEntity = CourseEntity.find("title", title).firstResult();
            return courseEntity;
        }catch (Exception e){
            return null;
        }
    }
}
