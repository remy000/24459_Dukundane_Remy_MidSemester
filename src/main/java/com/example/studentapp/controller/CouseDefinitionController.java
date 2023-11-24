package com.example.studentapp.controller;

import com.example.studentapp.domain.CourseDefinition;
import com.example.studentapp.domain.Student;
import com.example.studentapp.service.CourseDefService;
import com.example.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;

@RestController
@RequestMapping(value = "/definitions/",consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class CouseDefinitionController {
    @Autowired
    private CourseDefService courseDefService;

    @PostMapping(value = "/course")
    public ResponseEntity<?> saveStudent(@RequestBody CourseDefinition course) throws RemoteException {
        if (course != null) {
            CourseDefinition courseDefinition=courseDefService.insertCourseDefinition(course);
            if (courseDefinition != null) {
                return new ResponseEntity<>("course Saved", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("course is null", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping(value = "/course/{id}")
    public ResponseEntity<?>selectCourse(@PathVariable ("id") Integer id) throws RemoteException{
        CourseDefinition course=courseDefService.selectCourseDefinition(id);
        if(course!=null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("course is null", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping(value = "/course")
    public ResponseEntity<List<?>>allCourse() throws RemoteException{
        List<CourseDefinition> courses=courseDefService.selectAllCourseDefinitions();

            return new ResponseEntity<>(courses, HttpStatus.OK);


    }
    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Integer id) {
        try {
            courseDefService.deleteCourseDefinition(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
