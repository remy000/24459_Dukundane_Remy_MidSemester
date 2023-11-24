package com.example.studentapp.controller;

import com.example.studentapp.domain.Semester;
import com.example.studentapp.domain.Student;
import com.example.studentapp.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;

@RestController
@RequestMapping(value = "/semesters/",consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class SemesterController {
    @Autowired
    private SemesterService semesterService;
    @PostMapping(value = "/semester")
    public ResponseEntity<?> saveSemester(@RequestBody Semester semester) throws RemoteException {
        if (semester != null) {
           Semester savedSemester=semesterService.insertSemester(semester);
            if (savedSemester != null) {
                return new ResponseEntity<>("semester Saved", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("semester is null", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping(value = "/semester")
    public ResponseEntity<List<?>>allSemester() throws RemoteException {
       List<Semester> semesters = semesterService.selectAllSemesters();
            return new ResponseEntity<>(semesters, HttpStatus.OK);

    }
    @GetMapping(value = "/semester/{id}")
    public ResponseEntity<?>selectSemester(@PathVariable ("id") Integer id) throws RemoteException{
        Semester semester=semesterService.selectSemester(id);
        if(semester!=null) {
            return new ResponseEntity<>(semester, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("student is null", HttpStatus.BAD_REQUEST);

        }
    }
    @DeleteMapping("/semester/{id}")
    public ResponseEntity<?> deleteSemester(@PathVariable("id") Integer id) {
        try {
            semesterService.deleteSemester(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
