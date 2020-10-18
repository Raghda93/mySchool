package com.myshool2.service.controllers;

import com.myshool2.core.manager.SchoolClassManager;
import com.myshool2.model.types.SchoolClass;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class SchoolClassController {

    private final SchoolClassManager schoolClassManager;

    public SchoolClassController(SchoolClassManager schoolClassManager) {
        this.schoolClassManager = schoolClassManager;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void createNewSchoolClass(@RequestBody SchoolClass request) {
        schoolClassManager.createNewSchoolClass(request);
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SchoolClass getSchoolClass(@PathVariable(name = "classId") String classId) {
        return schoolClassManager.getBySchoolClassId(classId);
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSchoolclass(@PathVariable(name = "classId") String classId) {
        schoolClassManager.deleteSchoolClassById(classId);
    }

    @RequestMapping(value = "/{classId}/add-course", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SchoolClass addNewCourseToClass(@PathVariable(name = "classId") String classId,
                                              @RequestParam String courseId) {
        return schoolClassManager.addNewCourseToClass(classId, courseId);
    }

}
