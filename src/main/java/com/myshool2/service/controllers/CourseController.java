package com.myshool2.service.controllers;

import com.myshool2.core.manager.CourseManager;
import com.myshool2.model.types.Course;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseManager courseManager;

    public CourseController(CourseManager courseManager) {
        this.courseManager = courseManager;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void createCourse(@RequestBody Course request) {
        courseManager.createNewCourse(request);
    }

    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Course getCourse(@PathVariable(name = "courseId") String courseId) {
        return courseManager.getByCourseId(courseId);
    }


    @RequestMapping(value = "/{courseId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCourse(@PathVariable(name = "courseId") String courseId) {
        courseManager.deleteCourseById(courseId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCoursessByClassId() {
        return courseManager.getAllCourses();
    }

}
