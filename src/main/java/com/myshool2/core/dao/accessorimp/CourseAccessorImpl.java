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
public class CourseAccessorImpl implements CourseAccessor {
    private final EntityManager entityManager;

    public CourseAccessorImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Course> getAllCourses() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Course> query =
                currentSession.createQuery("select * from " + Course.TABLE_NAME, Course.class);
        return query.getResultList();
    }

    @Override
    public Course getByCourseId(String courseId) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Course.class, courseId);
    }

    @Override
    public void createNewCourse(Course course) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(course);
    }

    @Override
    public void deleteCourseById(String courseId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery =
                currentSession.createQuery("delete from " + Course.TABLE_NAME +" where "
                                + Course.PRIMARY_KEY_NAME + "=:courseId");
        theQuery.setParameter("courseId", courseId);
        theQuery.executeUpdate();
    }
}
