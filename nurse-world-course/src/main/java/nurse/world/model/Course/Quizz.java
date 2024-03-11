package nurse.world.model.Course;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import nurse.world.model.Course.Question;

@Entity
@Table(name = "quizz")
@AllArgsConstructor
@NoArgsConstructor
public class Quizz extends PanacheEntity {
    private List<Question> questions;

    @Transactional
    public static void add(Quizz quizz) {
        addQuestions(quizz.getQuestions());
        quizz.persist();
    }

    private static void addQuestions(List<Question> questions) {
        for (Question question : questions){
            Question.add(question);
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
