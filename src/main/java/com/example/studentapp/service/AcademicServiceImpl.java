package com.example.studentapp.service;

import com.example.studentapp.domain.AcademicUnit;
import com.example.studentapp.repository.AcademicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.List;
@Service
public class AcademicServiceImpl implements AcademicService {
    @Autowired
    private AcademicRepository academicRepository;
    @Override
    public AcademicUnit insertAcademicUnit(AcademicUnit academic) throws RemoteException {
        return academicRepository.save(academic);

    }

    @Override
    public AcademicUnit selectAcademicUnit(int academicUnitId) throws RemoteException {
        AcademicUnit academics=academicRepository.findById(academicUnitId).orElse(null);
        if(academics !=null){
            return  academics;
        }
        else{
            return null;
        }
    }

    @Override
    public List<AcademicUnit> selectAllAcademicUnit() throws RemoteException {
        return academicRepository.findAll();
    }

    @Override
    public boolean deleteAcademicUnit(int id) throws RemoteException {
        AcademicUnit academic=academicRepository.findById(id).orElse(null);
        if(academic !=null){
            academicRepository.delete(academic);
            return true;
        }
        return false;
    }

    @Override
    public AcademicUnit updateAcademicUnit(int id, String name) throws RemoteException {
        AcademicUnit acad=academicRepository.findById(id).orElse(null);
        if(acad !=null){
            acad.setName(name);
            return academicRepository.save(acad);

        }
        else{
            return null;
        }

    }
}
