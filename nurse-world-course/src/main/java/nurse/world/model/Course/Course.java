package nurse.world.model.Course;

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
    public static boolean add(Course course){
        if(!checkValidDate(course.getDateCreation())){
            return false;
        }
        saveImages(course.getImages());
        course.setImages(course.getImages());

        saveCoursesTopic(course.getCourseTopic());
        course.setCourseTopic(course.getCourseTopic());

        LearningInfo.add(course.getLearningInfo());
        course.setLearningInfo(course.getLearningInfo());
        //comprobar si el speaker ja existia
        Speaker.add(course.getSpeaker());
        course.setSpeaker(course.getSpeaker());

        PlaceInfo.add(course.getPlaceInfo());
        course.setPlaceInfo(course.getPlaceInfo());

        saveContent(course.getContent());
        course.setContent(course.getContent());

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
    public static boolean checkCourseExistById(double id) {
        try {
            Course course = Course.find("id", id).firstResult();
            if(course == null)
                return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Transactional
    public static Course getCoursesByTitle(String title) {
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

    @Transactional
    public static Course getCourseById(double id) {
        try {
            Course course = Course.find("id", id).firstResult();
            return course;
        }catch (Exception e) { 
            return null;
        }
    }

    private static void saveImages(List<Images> images) {
        for(Images image : images){
            Images.add(image);
        }
    }
    private static void saveCoursesTopic(List<CourseTopic> coursesTopic) {
        for (CourseTopic courseTopic : coursesTopic){
            CourseTopic.add(courseTopic);
        }
    }

    private static void saveContent(List<Content> contents) {
        for (Content content : contents){
            Content.add(content);
        }
    }

    private static boolean checkValidDate(String date) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (Exception e){
            return false;
        }
        return true;
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
