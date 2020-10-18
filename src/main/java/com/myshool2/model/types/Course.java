package com.myshool2.model.types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = Course.TABLE_NAME)
public class Course {

    public static final String TABLE_NAME = "courses";
    public static final String PRIMARY_KEY_NAME = "id";

    @Id
    @Column(name = PRIMARY_KEY_NAME)
    private String id;
    private String name;
    private String grade;

    public Course(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
