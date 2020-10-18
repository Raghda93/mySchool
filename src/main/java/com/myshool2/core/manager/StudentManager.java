package com.myshool2.core.manager;

import com.myshool2.core.dao.accessor.StudentAccessor;
import com.myshool2.model.types.Student;

import javax.annotation.ManagedBean;
import javax.transaction.Transactional;
import java.util.List;

@ManagedBean
public class StudentManager {

    private final StudentAccessor studentAccessor;

    public StudentManager(StudentAccessor studentAccessor) {
        this.studentAccessor = studentAccessor;
    }

    @Transactional
    public List<Student> getAllStudents() {
        return studentAccessor.getAllStudents();
    }

    @Transactional
    public List<Student> getAllStudentsBySchoolClassId(String schoolClassId) {
        return studentAccessor.getAllStudentsBySchoolClassId(schoolClassId);
    }

    @Transactional
    public Student getByStudentId(String studentId) {
        return studentAccessor.getByStudentId(studentId);
    }

    @Transactional
    public void createNewStudent(Student student) {
        studentAccessor.createNewStudent(student);
    }

    @Transactional
    public void deleteStudentById(String studentId) {
        studentAccessor.deleteStudentById(studentId);
    }

    @Transactional
    public void updateStudentClass(String studentId, String classId) {
        studentAccessor.updateStudentClass(studentId, classId);
    }

    @Transactional
    public void updateStudent(Student student) {
        studentAccessor.updateStudent(student);
    }
}
