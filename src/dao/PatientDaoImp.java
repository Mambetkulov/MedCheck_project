package dao;

import dao.interfacDao.GenericDao;
import dao.interfacDao.PatientDao;
import dataBase.DataBase;
import models.Hospital;
import models.Patient;

import java.util.*;

public class PatientDaoImp implements GenericDao<Patient> , PatientDao {


    @Override
    public String add(int index, Patient patient) {
        DataBase.hospitals.get(index).getPatients().add(patient);
        return "successfully added";
    }

    @Override
    public void removeById(int index,int hosIndex) {
      DataBase.hospitals.get(hosIndex).getPatients().remove(index);
        System.out.println("successfully deleted");
    }

    @Override
    public String updateById(int index,int index2, Patient patient) {
        DataBase.hospitals.get(index).getPatients().set(index2,patient);
        return "successfully deleted";
    }

    @Override
    public String addPatientsToHospital(int index, List<Patient> patients) {
        DataBase.hospitals.get(index).getPatients().addAll(patients);
        return "successfully added to hospital ";
    }

    @Override
    public Patient getPatientById(int index,int indexP) {
        return DataBase.hospitals.get(index).getPatients().get(indexP);
    }

    @Override
    public Map<Integer,List<Patient> > getPatientByAge() {

        Map<Integer,List<Patient>> patiensByAge = new HashMap<>();
        List<Patient> patients = new ArrayList<>();
        for(Hospital hospital : DataBase.hospitals){
            patients.addAll(hospital.getPatients());
        }

        if(!patients.isEmpty()){
            patients.sort((o1,o2) -> Integer.valueOf(o1.getAge()).compareTo(o2.getAge()));
            patiensByAge.put(1,patients);
        }

        return patiensByAge;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = new ArrayList<>();
        if(ascOrDesc.equalsIgnoreCase("A")){
            for(Hospital hospital : DataBase.hospitals){
               patients.addAll(hospital.getPatients());
            }
            patients.sort((o1,o2) -> Integer.valueOf(o1.getAge()).compareTo(o2.getAge()));
        }else if(ascOrDesc.equalsIgnoreCase("D")){
            for(Hospital hospital : DataBase.hospitals){
                patients.addAll(hospital.getPatients());
            }
            patients.sort((o1,o2) -> Integer.compare(o2.getAge(), o1.getAge()));
        }else{
            System.out.println("Patient with this id not found");
        }
        return patients;
    }
}
