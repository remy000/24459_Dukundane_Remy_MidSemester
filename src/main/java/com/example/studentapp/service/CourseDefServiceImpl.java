package com.example.studentapp.service;

import com.example.studentapp.domain.CourseDefinition;
import com.example.studentapp.repository.CourseDefinitionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.List;
@Service
public class CourseDefServiceImpl implements CourseDefService{
    @Autowired
    private CourseDefinitionRepo courseDefinitionRepo;
    @Override
    public CourseDefinition insertCourseDefinition(CourseDefinition courseDefinition) throws RemoteException {
        return courseDefinitionRepo.save(courseDefinition);
    }

    @Override
    public CourseDefinition selectCourseDefinition(int id) throws RemoteException {
        CourseDefinition course=courseDefinitionRepo.findById(id).orElse(null);
        if(course !=null){
            return course;
        }
        return null;
    }

    @Override
    public List<CourseDefinition> selectAllCourseDefinitions() throws RemoteException {
        return courseDefinitionRepo.findAll();
    }

    @Override
    public boolean deleteCourseDefinition(int id) throws RemoteException {
        CourseDefinition course=courseDefinitionRepo.findById(id).orElse(null);
        if(course !=null){
            courseDefinitionRepo.delete(course);
            return true;
        }
        return false;
    }

    @Override
    public CourseDefinition updateCourseDefinition(int courseId, String name, String Description) throws RemoteException {
        CourseDefinition course=courseDefinitionRepo.findById(courseId).orElse(null);
        if(course !=null){
            course.setName(name);
            course.setDescriptions(Description);
            courseDefinitionRepo.save(course);
        }
        return null;
    }
}
