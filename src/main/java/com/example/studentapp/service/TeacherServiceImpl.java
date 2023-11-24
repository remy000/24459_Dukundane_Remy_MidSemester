package com.example.studentapp.service;

import com.example.studentapp.domain.Teacher;
import com.example.studentapp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public Teacher insertTeacher(Teacher teacher) throws RemoteException {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher selectTeacher(int teacherId) throws RemoteException {
        Teacher teacher=teacherRepository.findById(teacherId).orElse(null);
        if(teacher!=null){
            return teacher;

        }        return null;
    }

    @Override
    public List<Teacher> selectAllTeachers() throws RemoteException {
        return teacherRepository.findAll();
    }

    @Override
    public boolean deleteTeacher(int id) throws RemoteException {
        Teacher teacher=teacherRepository.findById(id).orElse(null);
        if(teacher!=null){
            teacherRepository.delete(teacher);
            return true;

        }
        return false;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) throws RemoteException {
        return teacherRepository.save(teacher);
    }
}
