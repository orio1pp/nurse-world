package nurse.world.model.Course;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
public class Question extends PanacheEntity {

    private String question;
    List<Option> options;
    private String correctAnswer;

    @Transactional
    public static void add(Question question){
        presistOptions(question.getOptions());
        question.persist();
    }
    
    public static void presistOptions(List<Option> options) {
        for (Option option : options){
            Option.add(option);
        }
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() { 
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
