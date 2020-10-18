package com.myshool2.core.manager;

import com.myshool2.core.dao.accessor.SchoolClassAccessor;
import com.myshool2.model.types.SchoolClass;

import javax.annotation.ManagedBean;
import javax.transaction.Transactional;

@ManagedBean
public class SchoolClassManager {

    private final SchoolClassAccessor schoolClassAccessor;

    public SchoolClassManager(SchoolClassAccessor schoolClassAccessor) {
        this.schoolClassAccessor = schoolClassAccessor;
    }

    @Transactional
    public SchoolClass addNewCourseToClass(String schoolClassId, String courseId) {
        return schoolClassAccessor.addNewCourseToClass(schoolClassId, courseId);
    }

    @Transactional
    public SchoolClass getBySchoolClassId(String schoolClassId) {
        return schoolClassAccessor.getBySchoolClassId(schoolClassId);
    }

    @Transactional
    public void createNewSchoolClass(SchoolClass schoolClass) {
        schoolClassAccessor.createOrUpdateNewSchoolClass(schoolClass);
    }

    @Transactional
    public void deleteSchoolClassById(String schoolClassId) {
        schoolClassAccessor.deleteSchoolClassById(schoolClassId);
    }
}
