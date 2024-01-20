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
    private String dateCreation;
    private String description;
    private double price;
    private String motivation;
    private String modality;
    @OneToMany
    private List<Images> images;
    @OneToMany
    private List<CourseTopic> courseTopic;
    @OneToOne
    private LearningInfo learningInfo;
    @OneToOne
    private Speaker speaker;
    @OneToOne
    private PlaceInfo placeInfo;
    @OneToMany
    private List<Content> content;
    @Transactional
    public static boolean add(CreateCoursesDTO createCoursesDTO){
        if(!checkValidDate(createCoursesDTO.getDateCreation())){
            return false;
        }
        Course course = new Course();
        course.setDescription(createCoursesDTO.getDescription());
        course.setModality(createCoursesDTO.getModality());
        course.setTitle(createCoursesDTO.getTitle());
        course.setDateCreation(createCoursesDTO.getDateCreation());
        course.setPrice(createCoursesDTO.getPrice());
        course.setMotivation(createCoursesDTO.getMotivation());

        saveImages(createCoursesDTO.getImages());
        course.setImages(createCoursesDTO.getImages());

        saveCoursesTopic(createCoursesDTO.getCourseTopic());
        course.setCourseTopic(createCoursesDTO.getCourseTopic());

        LearningInfo.add(createCoursesDTO.getLearningInfo());
        course.setLearningInfo(createCoursesDTO.getLearningInfo());
        //comprobar si el speaker ja existia
        Speaker.add(createCoursesDTO.getSpeaker());
        course.setSpeaker(createCoursesDTO.getSpeaker());

        PlaceInfo.add(createCoursesDTO.getPlaceInfo());
        course.setPlaceInfo(createCoursesDTO.getPlaceInfo());

        saveContent(createCoursesDTO.getContent());
        course.setContent(createCoursesDTO.getContent());

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

    private static void saveContent(List<Content> contents){
        for (Content content : contents){
            Content.add(content);
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
                course.getDateCreation(),
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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
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

    public LearningInfo getLearningInfo() {
        return learningInfo;
    }

    public void setLearningInfo(LearningInfo learningInfo) {
        this.learningInfo = learningInfo;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public PlaceInfo getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(PlaceInfo placeInfo) {
        this.placeInfo = placeInfo;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }
}
