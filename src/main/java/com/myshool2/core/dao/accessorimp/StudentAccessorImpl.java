package com.myshool2.core.dao.accessorimp;

import com.myshool2.core.dao.accessor.SchoolClassAccessor;
import com.myshool2.core.dao.accessor.StudentAccessor;
import com.myshool2.model.types.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import java.util.List;

@ManagedBean
public class StudentAccessorImpl implements StudentAccessor {

    private final EntityManager entityManager;
    private final SchoolClassAccessor schoolClassAccessor;

    /**
     * Constructor.
     *
     * @param entityManager entityManager
     * @param schoolClassAccessor schoolClassAccessor
     */
    public StudentAccessorImpl(EntityManager entityManager, SchoolClassAccessor schoolClassAccessor) {
        this.entityManager = entityManager;
        this.schoolClassAccessor = schoolClassAccessor;
    }

    @Override
    public List<Student> getAllStudents() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Student> query =
                currentSession.createQuery("select * from " + Student.TABLE_NAME, Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> getAllStudentsBySchoolClassId(String schoolClassId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Student> query =
                currentSession.createQuery("select * from " + Student.TABLE_NAME + "where class_id=:schoolClassId",
                        Student.class);
        query.setParameter("schoolClassId", schoolClassId);
        return query.getResultList();
    }

    @Override
    public Student getByStudentId(String studentId) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Student.class, studentId);
    }

    @Override
    public void createNewStudent(Student student) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(student);
    }

    @Override
    public void deleteStudentById(String studentId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from " + Student.TABLE_NAME + " where "
                + Student.PRIMARY_KEY_NAME + "=:studentId");
        theQuery.setParameter("studentId", studentId);
        theQuery.executeUpdate();
    }

    @Override
    public void updateStudentClass(String studentId, String classId) {
        if (StringUtils.isEmpty(classId)) {
            return;
        }
        Student student = getByStudentId(studentId);
        if (!classId.equals(student.getClassId())) {
            student.setClassId(classId);
            Session currentSession = entityManager.unwrap(Session.class);
            currentSession.saveOrUpdate(student);
            schoolClassAccessor.incrementStudentId(classId);
        }
    }

    @Override
    public void updateStudent(Student student) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(student);
    }
}
