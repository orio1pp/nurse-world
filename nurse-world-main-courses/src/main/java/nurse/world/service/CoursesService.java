package nurse.world.service;

import io.quarkus.panache.common.Page;
import jakarta.inject.Singleton;
import nurse.world.model.Course;
import nurse.world.utils.Exceptions.CourseAlreadyExistsException;
import nurse.world.utils.Exceptions.CourseNotExistException;
import nurse.world.utils.Exceptions.IncorrectCourseDataException;
import nurse.world.utils.dto.CourseMainPageDTO;
import nurse.world.utils.dto.CreateCoursesDTO;

import java.util.List;

@Singleton
public class CoursesService {
    public List<Course> getAllCourses(int page) {
        if(page < 0){
            return null;
        }
        return Course.find("")
                .page(Page.of(page, 10))
                .list();
    }

    public void addCourse(CreateCoursesDTO courses) throws IncorrectCourseDataException, CourseAlreadyExistsException {
        if(Course.checkCourseExist(courses.getTitle())){
            throw new CourseAlreadyExistsException("Course already exists");
        }
        if(!Course.add(courses)){
            throw new IncorrectCourseDataException("Incorrect Course data");
        };
    }

    public CourseMainPageDTO getCourseMainPage(String title) throws CourseNotExistException {
        if(!Course.checkCourseExist(title)){
            throw new CourseNotExistException("Course does not exists");
        }
        Course course = Course.getCoursesByTitle(title);
        return course.toCourseMainPageDTO(course);
    }
}
