package com.myshool2.model.types;

import com.sun.istack.internal.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = Student.TABLE_NAME)
public class Student {
    public static final String TABLE_NAME = "students";
    public static final String PRIMARY_KEY_NAME = "id";

    @Id
    @Column(name = PRIMARY_KEY_NAME)
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "class_id")
    private String classId;
    private int age;
    @Nullable
    private final Double average;

    public Student(String id, String firstName, String lastName, String classId, int age, double average) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
        this.age = age;
        this.average = average;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getClassId() {
        return classId;
    }

    public int getAge() {
        return age;
    }

    public double getAverage() {
        return average;
    }

}
