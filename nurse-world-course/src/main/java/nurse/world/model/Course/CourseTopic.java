package nurse.world.model.Course;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coursetopic")
@AllArgsConstructor
@NoArgsConstructor
public class CourseTopic extends PanacheEntity {
    private String name;
    private String methodology;

    @Transactional
    public static void add(CourseTopic courseTopic){
        courseTopic.persist();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }
}
