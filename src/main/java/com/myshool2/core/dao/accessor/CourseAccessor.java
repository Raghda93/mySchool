package com.myshool2.core.dao.accessor;

import com.myshool2.model.types.Course;
import java.util.List;

public interface CourseAccessor {

    List<Course> getAllCourses();

    Course getByCourseId(String courseId);

    void createNewCourse(Course course);

    void deleteCourseById(String courseId);

}
