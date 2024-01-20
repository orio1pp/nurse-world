package nurse.world.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "learningInfo")
@AllArgsConstructor
@NoArgsConstructor
public class LearningInfo extends PanacheEntity {
    private int numberArticles;
    private int numberClasses;
    private int contentHours;

    @Transactional
    public static void add(LearningInfo learningInfo){
        learningInfo.persist();
    }

    public int getNumberArticles() {
        return numberArticles;
    }

    public void setNumberArticles(int numberArticles) {
        this.numberArticles = numberArticles;
    }

    public int getNumberClasses() {
        return numberClasses;
    }

    public void setNumberClasses(int numberClasses) {
        this.numberClasses = numberClasses;
    }

    public int getContentHours() {
        return contentHours;
    }

    public void setContentHours(int contentHours) {
        this.contentHours = contentHours;
    }
}
