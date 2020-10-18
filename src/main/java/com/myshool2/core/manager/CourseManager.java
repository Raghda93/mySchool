package com.myshool2.core.manager;

import com.myshool2.core.dao.accessor.CourseAccessor;
import com.myshool2.model.types.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.annotation.ManagedBean;
import javax.transaction.Transactional;
import java.util.List;

@ManagedBean
public class CourseManager {

    private final CourseAccessor courseAccessor;

    public CourseManager(CourseAccessor courseAccessor) {
        this.courseAccessor = courseAccessor;
    }

    @Transactional
    public List<Course> getAllCourses() {
        return courseAccessor.getAllCourses();
    }

    @Transactional
    public Course getByCourseId(String courseId) {
        return courseAccessor.getByCourseId(courseId);
    }

    @Transactional
    public void createNewCourse(Course course) {
        courseAccessor.createNewCourse(course);
    }

    @Transactional
    public void deleteCourseById(String courseId) {
        courseAccessor.deleteCourseById(courseId);
    }
}
