package com.myshool2.core.dao.accessorimp;

import com.myshool2.core.dao.accessor.CourseAccessor;
import com.myshool2.core.dao.accessor.TeacherAccessor;
import com.myshool2.model.types.Course;
import com.myshool2.model.types.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@ManagedBean
public class TeacherAccessorImpl implements TeacherAccessor {

    private final EntityManager entityManager;
    private final CourseAccessor courseAccessor;

    public TeacherAccessorImpl(EntityManager entityManager,
                               CourseAccessor courseAccessor) {
        this.entityManager = entityManager;
        this.courseAccessor = courseAccessor;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        Session currentSession = getEntityManagerSession();
        Query<Teacher> query =
                currentSession.createQuery("select * from " + Teacher.TABLE_NAME, Teacher.class);
        return query.getResultList();
    }

    @Override
    public Teacher getByTeacherId(String teacherId) {
        Session currentSession = getEntityManagerSession();
        return currentSession.get(Teacher.class, teacherId);
    }

    @Override
    public void createNewTeacher(Teacher teacher) {
        Session currentSession = getEntityManagerSession();
        currentSession.saveOrUpdate(teacher);
    }

    @Override
    public void deleteTeacherById(String teacherId) {
        Session currentSession = getEntityManagerSession();

        Query theQuery =
                currentSession.createQuery("delete from " + Teacher.TABLE_NAME + " where "
                        + Teacher.PRIMARY_KEY_NAME + "=:teacherId");
        theQuery.setParameter("teacherId", teacherId);
        theQuery.executeUpdate();
    }

    @Override
    public Teacher addCourseToTeacher(String teacherId, String courseId) {
        Course course = getCourseById(courseId, false);
        return addCourseToTeacher(teacherId, course);
    }

    @Override
    public Teacher addCourseToTeacherAndCreateIfNotExist(String teacherId, String courseId,
                                                         String courseName, String courseGrade) {
        Course course = Optional.ofNullable(getCourseById(courseId, true))
                .orElseGet(() -> createNewCourse(courseId, courseName, courseGrade));
        return addCourseToTeacher(teacherId, course);
    }

    private Teacher addCourseToTeacher(String teacherId, Course course) {
        Teacher teacher = getByTeacherId(teacherId);
        if (teacher == null) {
            throw new IllegalArgumentException("Failed to add course [" + course.getId()
                    + "] to teacher with Id [" + teacherId
                    + ", teacher does not exist.");
        }

        teacher.getCourses().add(course);
        Session currentSession = getEntityManagerSession();
        currentSession.saveOrUpdate(teacher);
        return teacher;
    }

    private Session getEntityManagerSession() {
        return entityManager.unwrap(Session.class);
    }

    private Course getCourseById(String courseId, boolean skipNotFound) {
        Course course = courseAccessor.getByCourseId(courseId);
        if (course == null && !skipNotFound) {
            throw new IllegalArgumentException("Failed to add course with Id [" + courseId
                    + ", course does not exist.");
        }
        return course;
    }

    private Course createNewCourse(String courseId, String courseName, String courseGrade) {
        Course newCourse = new Course(courseId, courseName, courseGrade);
        courseAccessor.createNewCourse(newCourse);
        return newCourse;
    }
}
