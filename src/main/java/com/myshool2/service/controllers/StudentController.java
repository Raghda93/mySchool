package com.myshool2.service.controllers;

import com.myshool2.core.manager.StudentManager;
import com.myshool2.model.types.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentManager studentManager;

    public StudentController(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void createStudent(@RequestBody Student request) {
        studentManager.createNewStudent(request);
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable(name = "studentId") String studentId) {
        return studentManager.getByStudentId(studentId);
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudent(@PathVariable(name = "studentId") String studentId) {
        studentManager.deleteStudentById(studentId);
    }

    @RequestMapping(value = "/students/{classId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudentsByClassId(@PathVariable(name = "classId") String classId) {
        return studentManager.getAllStudentsBySchoolClassId(classId);
    }

}
