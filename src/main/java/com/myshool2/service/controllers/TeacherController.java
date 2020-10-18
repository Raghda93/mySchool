package com.myshool2.service.controllers;

import com.myshool2.core.manager.TeacherManager;
import com.myshool2.model.types.Teacher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherManager teacherManager;

    public TeacherController(TeacherManager teacherManager) {
        this.teacherManager = teacherManager;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void createTeacher(@RequestBody Teacher request) {
        teacherManager.createNewTeacher(request);
    }

    @RequestMapping(value = "/{teacherId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Teacher getTeacher(@PathVariable(name = "teacherId") String teacherId) {
        return teacherManager.getByTeacherId(teacherId);
    }


    @RequestMapping(value = "/{teacherId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTeacher(@PathVariable(name = "teacherId") String teacherId) {
        teacherManager.deleteTeacherById(teacherId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Teacher> getAllTeachers() {
        return teacherManager.getAllTeachers();
    }

    @RequestMapping(value = "/{teacherId}/add_course", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Teacher addCourseToTeacher(@PathVariable(name = "teacherId") String teacherId,
                                      @RequestParam String courseId) {
        return teacherManager.addCourseToTeacher(teacherId, courseId);
    }

    @RequestMapping(value = "/{teacherId}/add_course", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Teacher addCourseToTeacher(@PathVariable(name = "teacherId") String teacherId,
                                      @RequestParam String courseId,
                                      @RequestParam String courseName,
                                      @RequestParam String courseGrade) {
        return teacherManager.addCourseToTeacherAndCreateIfNotExist(teacherId, courseId, courseName, courseGrade);
    }

}
