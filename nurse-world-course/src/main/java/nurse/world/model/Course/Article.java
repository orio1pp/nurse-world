package nurse.world.model.Course;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article")
@AllArgsConstructor
@NoArgsConstructor
public class Article extends PanacheEntity {
    private String title;

    private String description;

    private Speaker contentCreator;

    private String updateDate;

    private String image;

}
