package nurse.world.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nurse.world.utils.dto.CourseMainPageDTO;
import nurse.world.utils.dto.CreateCoursesDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
public class Course extends PanacheEntity {
    private String title;
    private String creator;
    private String dateCreation;
    private double duration;
    private String stages;
    private String description;

    private double price;

    private String motivation;
    @OneToMany
    private List<Images> images;

    @OneToMany
    private List<CourseTopic> courseTopic;


    @Transactional
    public static boolean add(CreateCoursesDTO createCoursesDTO){
        if(!checkValidDate(createCoursesDTO.getDateCreation())){
            return false;
        }
        Course course = new Course();
        course.setCreator(createCoursesDTO.getCreator());
        course.setDescription(createCoursesDTO.getDescription());
        course.setDuration(createCoursesDTO.getDuration());
        course.setStages(createCoursesDTO.getStages());
        course.setTitle(createCoursesDTO.getTitle());
        course.setDateCreation(createCoursesDTO.getDateCreation());
        course.setPrice(createCoursesDTO.getPrice());
        course.setMotivation(createCoursesDTO.getMotivation());

        saveImages(createCoursesDTO.getImages());
        course.setImages(createCoursesDTO.getImages());

        saveCoursesTopic(createCoursesDTO.getCourseTopic());
        course.setCourseTopic(createCoursesDTO.getCourseTopic());
        course.persist();
        return true;
    }
    @Transactional
    public static boolean checkCourseExist(String courseTitle){
        try {
            Course course = Course.find("title", courseTitle).firstResult();
            if(course == null)
                return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public static Course getCoursesByTitle(String title){
        if(title == null){
            return null;
        }
        try{
            Course course = Course.find("title", title).firstResult();
            return course;
        }catch (Exception e){
            return null;
        }
    }

    private static void saveImages(List<Images> images){
        for(Images image : images){
            Images.add(image);
        }
    }
    private static void saveCoursesTopic(List<CourseTopic> coursesTopic){
        for (CourseTopic courseTopic : coursesTopic){
            CourseTopic.add(courseTopic);
        }
    }
    private static boolean checkValidDate(String date){
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public CourseMainPageDTO toCourseMainPageDTO(Course course){
        return new CourseMainPageDTO(
                course.getTitle(),
                course.getCreator(),
                course.getDateCreation(),
                course.getDuration(),
                course.getStages(),
                course.getDescription(),
                course.getPrice(),
                course.getMotivation(),
                course.getImages(),
                course.getCourseTopic()
        );
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

    public String getTitle() {
        return title;
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
