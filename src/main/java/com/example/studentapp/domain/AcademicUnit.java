package com.example.studentapp.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academic_unit")
public class AcademicUnit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "unity_type")
    private UnityType unityType;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parent_id", referencedColumnName = "id",nullable = true)
    private AcademicUnit parentUnit;
    @OneToMany(mappedBy = "unit")
    @JsonManagedReference("academicUnitRegistrationReference")
    private List<StudentRegistration> students;

    @OneToMany(mappedBy = "academic")
    @JsonManagedReference("academicCourseReference")
    private List<Course> courses = new ArrayList<>();

    public AcademicUnit() {
    }

    public AcademicUnit(int id) {
        this.id = id;
    }

    public AcademicUnit(int id, String code, String name, UnityType unityType, AcademicUnit parentUnit, List<StudentRegistration> students, List<Course> courses) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.unityType = unityType;
        this.parentUnit = parentUnit;
        this.students = students;
        this.courses = courses;
    }

    public AcademicUnit(String code, String name, UnityType unityType, AcademicUnit parentUnit) {
        this.code = code;
        this.name = name;
        this.unityType = unityType;
        this.parentUnit = parentUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnityType getUnityType() {
        return unityType;
    }

    public void setUnityType(UnityType unityType) {
        this.unityType = unityType;
    }

    public AcademicUnit getParentUnit() {
        return parentUnit;
    }

    public void setParentUnit(AcademicUnit parentUnit) {
        this.parentUnit = parentUnit;
    }

    public List<StudentRegistration> getStudents() {
        return students;
    }

    public void setStudents(List<StudentRegistration> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "AcademicUnit{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", unityType=" + unityType +
                ", parentUnit=" + parentUnit +
                ", students=" + students +
                ", courses=" + courses +
                '}';
    }

    public enum UnityType {
        PROGRAMME,
        FACULTY,
        DEPARTMENT
    }
}
