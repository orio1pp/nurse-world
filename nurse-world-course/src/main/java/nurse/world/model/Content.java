package nurse.world.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "content")
@AllArgsConstructor
@NoArgsConstructor
public class Content extends PanacheEntity {
    private String title;
    private String information;

    private String numberVideos;

    private String numberReadings;

    private String numberQuizzs;

    @OneToMany
    private List<CourseClass> courseClasses;

    @Transactional
    public static void add(Content content){
        content.persist();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getNumberVideos() {
        return numberVideos;
    }

    public void setNumberVideos(String numberVideos) {
        this.numberVideos = numberVideos;
    }

    public String getNumberReadings() {
        return numberReadings;
    }

    public void setNumberReadings(String numberReadings) {
        this.numberReadings = numberReadings;
    }

    public String getNumberQuizzs() {
        return numberQuizzs;
    }

    public void setNumberQuizzs(String numberQuizzs) {
        this.numberQuizzs = numberQuizzs;
    }

    public List<CourseClass> getCourseClasses() {
        return courseClasses;
    }

    public void setCourseClasses(List<CourseClass> courseClasses) {
        this.courseClasses = courseClasses;
    }
}

