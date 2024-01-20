package nurse.world.model;
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
public class Commentaries extends PanacheEntity {
}
