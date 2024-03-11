package nurse.world.model.Course;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "option")
@AllArgsConstructor
@NoArgsConstructor
public class Option extends PanacheEntity {
    private String option;
    
    @Transactional
    public static void add(Option option){
        option.persist();
    }

    public String getOption() { 
        return option;
    }

    public void setOption(String option){
        this.option = option;
    }
}
