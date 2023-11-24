package com.example.studentapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codes;
    private String names;
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "courseid")
    @JsonBackReference("teacherCourseReference")
    private Course courses;

    public Teacher() {
    }

    public Teacher(int id) {
        this.id = id;
    }

    public Teacher(String codes, String names, Qualification qualification, Role role, Course courses) {
        this.codes = codes;
        this.names = names;
        this.qualification = qualification;
        this.role = role;
        this.courses = courses;
    }

    public Teacher(int id, String codes, String names, Qualification qualification, Role role, Course courses) {
        this.id = id;
        this.codes = codes;
        this.names = names;
        this.qualification = qualification;
        this.role = role;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", codes='" + codes + '\'' +
                ", names='" + names + '\'' +
                ", qualification=" + qualification +
                ", role=" + role +
                ", courses=" + courses +
                '}';
    }

    public enum Qualification {
        Masters,
        PHD,
        PROFESSOR
    }
    public enum Role{
        Lecturer,
        Assistant
    }

}
