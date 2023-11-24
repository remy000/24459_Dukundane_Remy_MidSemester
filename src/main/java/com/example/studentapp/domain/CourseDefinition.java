package com.example.studentapp.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "course_definition")
public class CourseDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String name;
    private String descriptions;
    @OneToOne(mappedBy = "coursd")
    @JsonManagedReference("courseDefinitionReference")
    private Course cours;

    public CourseDefinition() {
    }

    public CourseDefinition(int id, String code, String name, String descriptions, Course cours) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.descriptions = descriptions;
        this.cours = cours;
    }

    public CourseDefinition(String code, String name, String descriptions) {
        this.code = code;
        this.name = name;
        this.descriptions = descriptions;
    }

    public CourseDefinition(String code) {
        this.code = code;
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

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Course getCours() {
        return cours;
    }

    public void setCours(Course cours) {
        this.cours = cours;
    }

    @Override
    public String toString() {
        return "CourseDefinition{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", cours=" + cours +
                '}';
    }
}
