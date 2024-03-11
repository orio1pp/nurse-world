package nurse.world.model.Course;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nurse.world.model.Course.Quizz;

@Entity
@Table(name = "courseclass")
@AllArgsConstructor
@NoArgsConstructor
public class CourseClass extends PanacheEntity {
    private String title;
    private String urlVideo;
    private String text;
    private int duration; //minutes
    private String type;

    @OneToOne
    private Quizz quizz;

    @Transactional
    public static void add(CourseClass courseClass){
        
        if (courseClass.getType().equals("question")) {
            Quizz quizz = courseClass.getQuizz();  
            Quizz.add(quizz);
        }

        courseClass.persist();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public void setQuizz(Quizz quizz) {
        this.quizz = quizz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
