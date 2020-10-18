package com.myshool2.model.types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = SchoolClass.TABLE_NAME)
public class SchoolClass {

    public static final String TABLE_NAME = "school_classes";
    public static final String PRIMARY_KEY_NAME = "id";

    @Id
    @Column(name = PRIMARY_KEY_NAME)
    private String classId;
    @Column(name = "number_of_students")
    private int numberOfStudents;
    private String grade;
    @ManyToMany(mappedBy = "teacher")
    private List<Course> courses;

    public SchoolClass(String classId, int numberOfStudents, String grade, List<Course> courses) {
        this.classId = classId;
        this.numberOfStudents = numberOfStudents;
        this.grade = grade;
        this.courses = courses;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
