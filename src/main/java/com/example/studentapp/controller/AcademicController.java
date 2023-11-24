package com.example.studentapp.controller;

import com.example.studentapp.domain.AcademicUnit;
import com.example.studentapp.domain.Semester;
import com.example.studentapp.service.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;

@RestController
@RequestMapping(value = "/academics/",consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class AcademicController {
    @Autowired
    private AcademicService academicService;
    @PostMapping(value = "/unit")
    public ResponseEntity<?> saveAcademic(@RequestBody AcademicUnit unit) throws RemoteException {
        if (unit != null) {
           AcademicUnit savedUnit=academicService.insertAcademicUnit(unit);
            if (savedUnit != null) {
                return new ResponseEntity<>("AcademicUnit Saved", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Academic is null", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping(value = "/unit")
    public ResponseEntity<List<?>>selectAllUnit() throws RemoteException{
        List<AcademicUnit> units=academicService.selectAllAcademicUnit();

            return new ResponseEntity<>(units, HttpStatus.OK);
    }
    @GetMapping(value = "/unit/{id}")
    public ResponseEntity<?>selectUnit(@PathVariable ("id") Integer id) throws RemoteException{
        AcademicUnit unit=academicService.selectAcademicUnit(id);
        if(unit!=null) {
            return new ResponseEntity<>(unit, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("student is null", HttpStatus.BAD_REQUEST);

        }
    }
    @DeleteMapping("/unit/{id}")
    public ResponseEntity<?> deleteUnit(@PathVariable("id") Integer id) {
        try {
           academicService.deleteAcademicUnit(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
