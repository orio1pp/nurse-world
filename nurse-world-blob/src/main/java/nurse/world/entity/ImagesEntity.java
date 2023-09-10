package nurse.world.entity;

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
public class ImagesEntity extends PanacheEntity {

    private String name;
    private String path;
    @Transactional
    public static void add(ImagesEntity imagesEntity){
        imagesEntity.persist();
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

    public Long getId() {
        return id;
    }

}
