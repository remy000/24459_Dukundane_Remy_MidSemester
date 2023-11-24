package com.example.studentapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseid;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    @JsonBackReference(value = "courseDefinitionReference")
    private CourseDefinition coursd;
    @OneToMany(mappedBy = "courses")
    @JsonManagedReference(value = "teacherCourseReference")
    private List<Teacher> teacher;
    @ManyToOne
    @JoinColumn(name="semid")
    @JsonBackReference(value = "semesterCourseReference")
    private Semester sems;
    @ManyToOne
    @JoinColumn(name="acaid")
    @JsonBackReference(value = "academicCourseReference")
    private AcademicUnit academic;
    @ManyToMany(mappedBy = "courses")
    private List<StudentRegistration> students=new ArrayList<>();

    public Course() {
    }

    public Course(int courseid) {
        this.courseid = courseid;
    }

    public Course(int courseid, CourseDefinition coursd, List<Teacher> teacher, Semester sems, AcademicUnit academic, List<StudentRegistration> students) {
        this.courseid = courseid;
        this.coursd = coursd;
        this.teacher = teacher;
        this.sems = sems;
        this.academic = academic;
        this.students = students;
    }

    public Course(CourseDefinition coursd, List<Teacher> teacher, Semester sems, AcademicUnit academic, List<StudentRegistration> students) {
        this.coursd = coursd;
        this.teacher = teacher;
        this.sems = sems;
        this.academic = academic;
        this.students = students;
    }



    public Course(CourseDefinition coursd, Semester sems, AcademicUnit academic) {
        this.coursd = coursd;
        this.sems = sems;
        this.academic = academic;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public CourseDefinition getCoursd() {
        return coursd;
    }

    public void setCoursd(CourseDefinition coursd) {
        this.coursd = coursd;
    }

    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }

    public Semester getSems() {
        return sems;
    }

    public void setSems(Semester sems) {
        this.sems = sems;
    }

    public AcademicUnit getAcademic() {
        return academic;
    }

    public void setAcademic(AcademicUnit academic) {
        this.academic = academic;
    }

    public List<StudentRegistration> getStudents() {
        return students;
    }

    public void setStudents(List<StudentRegistration> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseid=" + courseid +
                ", coursd=" + coursd +
                ", teacher=" + teacher +
                ", sems=" + sems +
                ", academic=" + academic +
                ", students=" + students +
                '}';
    }
}
