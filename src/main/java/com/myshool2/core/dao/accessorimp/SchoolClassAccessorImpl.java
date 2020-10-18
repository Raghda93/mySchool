package com.myshool2.core.dao.accessorimp;

import com.myshool2.core.dao.accessor.CourseAccessor;
import com.myshool2.core.dao.accessor.SchoolClassAccessor;
import com.myshool2.model.types.Course;
import com.myshool2.model.types.SchoolClass;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import java.util.List;

@ManagedBean
public class SchoolClassAccessorImpl implements SchoolClassAccessor {

    private final EntityManager entityManager;
    private final CourseAccessor courseAccessor;

    /**
     * Constructor.
     *
     * @param entityManager entityManager
     */
    public SchoolClassAccessorImpl(EntityManager entityManager,
                                   CourseAccessor courseAccessor) {
        this.entityManager = entityManager;
        this.courseAccessor = courseAccessor;
    }

    @Override
    public List<SchoolClass> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<SchoolClass> query =
                currentSession.createQuery("select * from " + SchoolClass.TABLE_NAME, SchoolClass.class);
        return query.getResultList();
    }

    @Override
    public SchoolClass getBySchoolClassId(String schoolClassId) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(SchoolClass.class, schoolClassId);
    }

    @Override
    public void createOrUpdateNewSchoolClass(SchoolClass schoolClass) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(schoolClass);
    }

    @Override
    public void deleteSchoolClassById(String schoolClassId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery =
                currentSession.createQuery(
                        "delete from " + SchoolClass.TABLE_NAME +" where "
                                + SchoolClass.PRIMARY_KEY_NAME + "=:schoolClassId");
        theQuery.setParameter("schoolClassId", schoolClassId);

        theQuery.executeUpdate();
    }

    @Override
    public SchoolClass addNewCourseToClass(String schoolClassId, String courseId) {
        Course course = getCourseById(courseId, false);

        SchoolClass schoolClass = getBySchoolClassId(schoolClassId);
        if (schoolClass == null) {
            throw new IllegalArgumentException("Failed to add schoolClass with Id [" + schoolClassId
                    + ", schoolClass does not exist.");
        }
        schoolClass.getCourses().add(course);
        createOrUpdateNewSchoolClass(schoolClass);
        return schoolClass;
    }

    @Override
    public void incrementStudentId(String classId) {
        SchoolClass schoolClass = getBySchoolClassId(classId);
        schoolClass.setNumberOfStudents(schoolClass.getNumberOfStudents() + 1);
        createOrUpdateNewSchoolClass(schoolClass);
    }

    private Course getCourseById(String courseId, boolean skipNotFound) {
        Course course = courseAccessor.getByCourseId(courseId);
        if (course == null && !skipNotFound) {
            throw new IllegalArgumentException("Failed to add course with Id [" + courseId
                    + ", course does not exist.");
        }
        return course;
    }
}
