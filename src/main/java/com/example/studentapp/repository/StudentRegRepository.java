package com.example.studentapp.repository;

import com.example.studentapp.domain.Course;
import com.example.studentapp.domain.StudentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRegRepository extends JpaRepository<StudentRegistration,Integer> {
    @Query("SELECT sr FROM StudentRegistration sr WHERE sr.sem.semid = :semesterId")
    List<StudentRegistration> findStudentsBySemester(@Param("semesterId") int semesterId);

    @Query("SELECT sr FROM StudentRegistration sr WHERE sr.sem.semid = :semesterId AND sr.unit.id = :academicUnitId")
    List<StudentRegistration> findStudentsBySemesterAndAcademicUnit(@Param("semesterId") int semesterId, @Param("academicUnitId") int academicUnitId);
    @Query("SELECT sr FROM StudentRegistration sr " +
            "JOIN sr.courses c " +
            "WHERE sr.sem.semid = :semesterId AND c.courseid = :courseId")
    List<StudentRegistration> findStudentsBySemesterAndCourse(@Param("semesterId") int semesterId, @Param("courseId") int courseId);


}
