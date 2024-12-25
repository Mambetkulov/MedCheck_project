package dao.interfacDao;

import models.Doctor;

import java.util.List;

public interface DoctorDao {

    Doctor findDoctorById(int index, int index2);

    String assignDoctorToDepartment(Long id, List<Doctor> doctorsId);

    List<Doctor> getAllDoctorsByHospitalId(int index);

    List<Doctor> getAllDoctorsByDepartmentId(int index,int index2);
}
