package com.example.studentapp.service;

import com.example.studentapp.domain.Semester;
import com.example.studentapp.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.List;
@Service
public class SemesterServiceImpl implements SemesterService{
    @Autowired
    private SemesterRepository semesterRepository;
    @Override
    public Semester insertSemester(Semester semester) throws RemoteException {
        return semesterRepository.save(semester);
    }

    @Override
    public Semester selectSemester(int semesterId) throws RemoteException {
        Semester semester=semesterRepository.findById(semesterId).orElse(null);
        if(semester!=null){
            return semester;
        }
        return null;
    }

    @Override
    public List<Semester> selectAllSemesters() throws RemoteException {
        return semesterRepository.findAll();
    }

    @Override
    public boolean deleteSemester(int id) throws RemoteException {
        Semester semester=semesterRepository.findById(id).orElse(null);
        if(semester!=null){
            semesterRepository.delete(semester);
            return true;
        }
        return false;
    }

    @Override
    public Semester updateSemester(Semester semester) throws RemoteException {
        return semesterRepository.save(semester);
    }
}
