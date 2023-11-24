package com.example.studentapp.controller;

import com.example.studentapp.domain.Course;
import com.example.studentapp.domain.Semester;
import com.example.studentapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;

@RestController
@RequestMapping(value = "/courses/",consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping(value = "/course")
    public ResponseEntity<?> saveCourse(@RequestBody Course course) throws RemoteException {
        if (course != null) {
            Course savedCourse=courseService.insertCourse(course);
            if (savedCourse != null) {
                return new ResponseEntity<>("course Saved", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("course is null", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping(value = "/course")
    public ResponseEntity<?>allCourses() throws RemoteException {
        List<Course> courses =courseService.selectAllCourses();
        if(courses!=null){
        return new ResponseEntity<>(courses, HttpStatus.OK);}
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/course/{id}")
    public ResponseEntity<?>selectCourse(@PathVariable ("id") Integer id) throws RemoteException{
       Course course=courseService.selectCourse(id);
        if(course!=null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("course is null", HttpStatus.BAD_REQUEST);

        }
    }
    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Integer id) {
        try {
            courseService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/course/academic/{acaid}/{semid}")
    public ResponseEntity<?>coursePerDepSem(@PathVariable("acaid") Integer acaid, @PathVariable("semid") Integer semid)throws RemoteException{
        List<Course> courses=courseService.getCourseByDepAndSemester(acaid,semid);
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }
    @GetMapping(value = "/course/student/{stid}")
    public ResponseEntity<?>CoursePerStud(@PathVariable("stid") Integer stid) throws RemoteException{
        List<Course>courses=courseService.getCoursePerStudent(stid);
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }
}
