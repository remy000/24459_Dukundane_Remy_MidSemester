package com.example.studentapp.controller;

import com.example.studentapp.domain.Semester;
import com.example.studentapp.domain.Teacher;
import com.example.studentapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;

@RestController
@RequestMapping(value = "/teachers/",consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping(value = "/teacher")
    public ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher) throws RemoteException {
        if (teacher != null) {
            Teacher savedTeach=teacherService.insertTeacher(teacher);
            if (savedTeach != null) {
                return new ResponseEntity<>("teacher Saved", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("teacher is null", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping(value = "/teacher")
    public ResponseEntity<List<?>>allTeachers() throws RemoteException {
        List<Teacher> teachers = teacherService.selectAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);

    }
    @GetMapping(value = "/teacher/{id}")
    public ResponseEntity<?>selectTeacher(@PathVariable ("id") Integer id) throws RemoteException{
        Teacher teache=teacherService.selectTeacher(id);
        if(teache!=null) {
            return new ResponseEntity<>(teache, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("student is null", HttpStatus.BAD_REQUEST);

        }
    }
    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("id") Integer id) {
        try {
            teacherService.deleteTeacher(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
