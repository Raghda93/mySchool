package com.myshool2.core.manager;

import com.myshool2.core.dao.accessor.TeacherAccessor;
import com.myshool2.model.types.Teacher;

import javax.transaction.Transactional;
import java.util.List;

public class TeacherManager {

    private final TeacherAccessor teacherAccessor;

    public TeacherManager(TeacherAccessor teacherAccessor) {
        this.teacherAccessor = teacherAccessor;
    }

    @Transactional
    public List<Teacher> getAllTeachers() {
        return teacherAccessor.getAllTeachers();
    }

    @Transactional
    public Teacher getByTeacherId(String teacherId) {
        return teacherAccessor.getByTeacherId(teacherId);
    }

    @Transactional
    public void createNewTeacher(Teacher teacher) {
        teacherAccessor.createNewTeacher(teacher);
    }

    @Transactional
    public void deleteTeacherById(String teacherId) {
        teacherAccessor.deleteTeacherById(teacherId);
    }

    @Transactional
    public Teacher addCourseToTeacher(String teacherId, String courseId) {
        return teacherAccessor.addCourseToTeacher(teacherId, courseId);
    }

    @Transactional
    public Teacher addCourseToTeacherAndCreateIfNotExist(String teacherId, String courseId,
                                                         String courseName, String courseGrade) {
        return teacherAccessor.addCourseToTeacherAndCreateIfNotExist(teacherId, courseId, courseName, courseGrade);
    }
}
