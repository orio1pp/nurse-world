package nurse.world.model.Course;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
public class Images extends PanacheEntity {
    private String name;
    private String path;
    @Transactional
    public static void add(Images images){
        images.persist();
    }

    @Transactional
    public static boolean checkCourseExist(String courseTitle){
        try {
            Course course = Course.find("title", courseTitle).firstResult();
            if(course == null)
                return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public static Course getCoursesByTitle(String title){
        if(title == null){
            return null;
        }
        try{
            Course course = Course.find("title", title).firstResult();
            return course;
        }catch (Exception e){
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
