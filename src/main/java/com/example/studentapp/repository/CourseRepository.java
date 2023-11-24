package com.example.studentapp.repository;

import com.example.studentapp.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    @Query("SELECT c FROM Course c WHERE c.sems.semid = :semesterId AND c.academic.id = :academicUnitId")
    List<Course> findCoursesBySemesterAndAcademicUnit(@Param("semesterId") int semesterId, @Param("academicUnitId") int academicUnitId);

    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.student.id = :studentId")
    List<Course> findCoursesByStudent(@Param("studentId") int studentId);

    @Query("SELECT c FROM Course c WHERE c.sems.semid = :semesterId")
    List<Course> findCoursesBySemester(@Param("semesterId") int semesterId);
}
