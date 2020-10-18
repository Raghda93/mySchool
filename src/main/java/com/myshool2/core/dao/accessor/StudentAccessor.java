package com.myshool2.core.dao.accessor;

import com.myshool2.model.types.Student;
import java.util.List;

public interface StudentAccessor {
    List<Student> getAllStudents();

    List<Student> getAllStudentsBySchoolClassId(String schoolClassId);

    Student getByStudentId(String studentId);

    void createNewStudent(Student student);

    void deleteStudentById(String studentId);

    void updateStudentClass(String studentId, String classId);

    void updateStudent(Student student);
}
