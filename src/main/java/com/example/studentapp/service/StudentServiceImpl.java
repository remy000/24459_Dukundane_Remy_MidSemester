package com.example.studentapp.service;

import com.example.studentapp.domain.Student;
import com.example.studentapp.repository.StudentRegRepository;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student insertStudent(Student student) throws RemoteException {
        return studentRepository.save(student);
    }

    @Override
    public Student selectStudent(int studentId) throws RemoteException {
        Student student=studentRepository.findById(studentId).orElse(null);
        if(student!=null){
            return student;
        }
        return null;
    }

    @Override
    public List<Student> selectAllStudents() throws RemoteException {
        return studentRepository.findAll();
    }

    @Override
    public boolean deleteStudent(int id) throws RemoteException {
        Student student=studentRepository.findById(id).orElse(null);
        if(student!=null){
            studentRepository.delete(student);
            return true;

        }        return false;
    }

    @Override
    public Student updateStudent(Student student) throws RemoteException {
        return studentRepository.save(student);
    }
}
