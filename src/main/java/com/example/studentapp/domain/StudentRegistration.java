package com.example.studentapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_registration")
@JsonIgnoreProperties({"student", "sem", "unit", "courses"})
public class StudentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regid;
    @Column(name="registration_date")
    private LocalDate regDate;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @JsonBackReference("registrationStudentReference")
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="semid")
    @JsonBackReference("semesterRegistrationReference")
    private Semester sem;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="acaid")
    @JsonBackReference("academicUnitRegistrationReference")
    private AcademicUnit unit;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_course",joinColumns = @JoinColumn(name="id")
            ,inverseJoinColumns =@JoinColumn(name="courseid"))


    private List<Course> courses=new ArrayList<>();

    public StudentRegistration() {
    }

    public StudentRegistration(int regid) {
        this.regid = regid;
    }

    public StudentRegistration(int regid, LocalDate regDate, Student student, Semester sem, AcademicUnit unit, List<Course> courses) {
        this.regid = regid;
        this.regDate = regDate;
        this.student = student;
        this.sem = sem;
        this.unit = unit;
        this.courses = courses;
    }

    public StudentRegistration(LocalDate regDate, Student student, Semester sem, AcademicUnit unit, List<Course> courses) {
        this.regDate = regDate;
        this.student = student;
        this.sem = sem;
        this.unit = unit;
        this.courses = courses;
    }

    public int getRegid() {
        return regid;
    }

    public void setRegid(int regid) {
        this.regid = regid;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSem() {
        return sem;
    }

    public void setSem(Semester sem) {
        this.sem = sem;
    }

    public AcademicUnit getUnit() {
        return unit;
    }

    public void setUnit(AcademicUnit unit) {
        this.unit = unit;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "StudentRegistration{" +
                "regid=" + regid +
                ", regDate=" + regDate +
                ", student=" + student +
                ", sem=" + sem +
                ", unit=" + unit +
                ", courses=" + courses +
                '}';
    }
}
