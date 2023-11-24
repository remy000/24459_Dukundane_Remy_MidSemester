package com.example.studentapp.service;

import com.example.studentapp.domain.Course;
import com.example.studentapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course insertCourse(Course course) throws RemoteException {
        return courseRepository.save(course);
    }

    @Override
    public Course selectCourse(int courseId) throws RemoteException {
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course!=null){
            return  course;
        }
        return null;
    }

    @Override
    public List<Course> selectAllCourses() throws RemoteException {
        return courseRepository.findAll();
    }

    @Override
    public boolean deleteCourse(int id) throws RemoteException {
        Course course=courseRepository.findById(id).orElse(null);
        if(course!=null) {
            courseRepository.delete(course);
            return true;
        }
        return false;
    }

    @Override
    public Course updateAcademicUnit(Course course) throws RemoteException {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCourseByDepAndSemester(int acaid, int semid) {
        return courseRepository.findCoursesBySemesterAndAcademicUnit(acaid, semid);
    }

    @Override
    public List<Course> getCoursePerStudent(int studentId) {
        return courseRepository.findCoursesByStudent(studentId);
    }
}
