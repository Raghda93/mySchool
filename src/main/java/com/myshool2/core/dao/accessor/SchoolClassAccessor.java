package com.myshool2.core.dao.accessor;

import com.myshool2.model.types.SchoolClass;
import java.util.List;

public interface SchoolClassAccessor {

    List<SchoolClass> findAll();

    SchoolClass getBySchoolClassId(String schoolClassId);

    void createOrUpdateNewSchoolClass(SchoolClass schoolClass);

    void deleteSchoolClassById(String schoolClassId);

    SchoolClass addNewCourseToClass(String schoolClassId, String courseId);

    void incrementStudentId(String classId);
}
