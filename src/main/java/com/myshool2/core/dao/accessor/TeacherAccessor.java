package com.myshool2.core.dao.accessor;

import com.myshool2.model.types.Teacher;
import java.util.List;

public interface TeacherAccessor {

    List<Teacher> getAllTeachers();

    Teacher getByTeacherId(String teacherId);

    void createNewTeacher(Teacher teacher);

    void deleteTeacherById(String teacherId);

    Teacher addCourseToTeacher(String teacherId, String courseId);

    Teacher addCourseToTeacherAndCreateIfNotExist(String teacherId, String courseId, String courseName, String courseGrade);
}
