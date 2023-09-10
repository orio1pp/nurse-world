package nurse.world.utils.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nurse.world.model.CourseTopic;
import nurse.world.model.Images;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
public class CreateCoursesDTO {

    private String title;
    private String creator;
    private String dateCreation;
    private double duration;
    private String stages;
    private String description;
    private double price;

    private String motivation;

    private List<Images> images;

    private List<CourseTopic> courseTopic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getStages() {
        return stages;
    }

    public void setStages(String stages) {
        this.stages = stages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<CourseTopic> getCourseTopic() {
        return courseTopic;
    }

    public void setCourseTopic(List<CourseTopic> courseTopic) {
        this.courseTopic = courseTopic;
    }
}
