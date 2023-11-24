package com.example.studentapp.service;

import com.example.studentapp.domain.StudentRegistration;
import com.example.studentapp.repository.StudentRegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.List;
@Service
public class StudentRegServiceImpl implements StudentRegService{
    @Autowired
    private StudentRegRepository studentRegRepository;
    @Override
    public StudentRegistration insertStudent(StudentRegistration student) throws RemoteException {
        return studentRegRepository.save(student);
    }

    @Override
    public StudentRegistration selectStudent(int studentId) throws RemoteException {
        StudentRegistration student=studentRegRepository.findById(studentId).orElse(null);
        if(student!=null){
            return student;
        }
        return null;
    }

    @Override
    public List<StudentRegistration> selectAllStudents() throws RemoteException {
        return studentRegRepository.findAll();
    }

    @Override
    public boolean deleteStudent(int id) throws RemoteException {
        StudentRegistration student=studentRegRepository.findById(id).orElse(null);
        if(student!=null){
            studentRegRepository.delete(student);
            return true;
        }
        return false;
    }

    @Override
    public StudentRegistration updateStudent(StudentRegistration student) throws RemoteException {
        return studentRegRepository.save(student);
    }

    @Override
    public List<StudentRegistration> studentsPerSem(int semid) throws RemoteException {
        return studentRegRepository.findStudentsBySemester(semid);
    }

    @Override
    public List<StudentRegistration> studentsPerSemAndDep(int acid, int semid) throws RemoteException {
        return studentRegRepository.findStudentsBySemesterAndAcademicUnit(acid, semid);
    }

    @Override
    public List<StudentRegistration> studentsPerSemCourse(int semid, int courseid) throws RemoteException {
        return studentRegRepository.findStudentsBySemesterAndCourse(semid,courseid);
    }
}
