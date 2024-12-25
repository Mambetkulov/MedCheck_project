package service;
import dao.DoctorDaoImp;
import dataBase.DataBase;
import models.Department;
import models.Doctor;
import models.Hospital;
import service.Interface.DoctorService;
import service.Interface.GenericService;
import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImp implements GenericService<Doctor> , DoctorService {
    public DoctorDaoImp doctorDaoImp;

    public DoctorServiceImp(DoctorDaoImp doctorDaoImp) {
        this.doctorDaoImp = doctorDaoImp;
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        for(Hospital hospital : DataBase.hospitals){
            if(hospital.getId().equals(hospitalId)){
                return doctorDaoImp.add(DataBase.hospitals.indexOf(hospital),doctor);
            }
        }
        return "Hospital with this id not found";
    }

    @Override
    public void removeById(Long id) {
        boolean isTrue = true;
       for(Hospital hospital : DataBase.hospitals){
           for(Doctor doctor : hospital.getDoctors()){
               if(doctor.getId().equals(id)){
                   isTrue = false;
                   doctorDaoImp.removeById(hospital.getDoctors().indexOf(doctor)
                           ,DataBase.hospitals.indexOf(hospital));
                   break;
               }
           }
       }
       if(isTrue) System.out.println("Doctor with this id not found");
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        for(Hospital hospital : DataBase.hospitals){
            for(Doctor doctor1 : hospital.getDoctors()){
                if(doctor1.getId().equals(id)){
                    return doctorDaoImp.updateById(DataBase.hospitals.indexOf(hospital),
                                                 hospital.getDoctors().indexOf(doctor1),doctor);
                }
            }
        }
        return "Doctor with this id not found";
    }

    @Override
    public Doctor findDoctorById(Long id) {
        for(Hospital hospital : DataBase.hospitals){
            for (Doctor doctor : hospital.getDoctors()){
                if(doctor.getId().equals(id)){
                    return doctorDaoImp.findDoctorById(DataBase.hospitals.indexOf(hospital),
                                                       hospital.getDoctors().indexOf(doctor));
                }
            }
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        int count = 0;
        List<Doctor> doctors = new ArrayList<>();
        for(Hospital hospital : DataBase.hospitals){
            for(Department department : hospital.getDepartments()){
                if(department.getId().equals(departmentId)){
                    for(Doctor doctor : hospital.getDoctors()){
                        if(doctor.getId().equals(doctorsId.get(count))){
                            doctors.add(doctor);
                            count ++;
                        }
                    }
                }
            }
        }
        if (!doctors.isEmpty()){
            return doctorDaoImp.assignDoctorToDepartment(departmentId,doctors);
        }
        return "Departament with this id or Doctors with ids you entered not found";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        for(Hospital hospital : DataBase.hospitals){
            if(hospital.getId().equals(id)){
                return doctorDaoImp.getAllDoctorsByHospitalId(DataBase.hospitals.indexOf(hospital));
            }
        }
        System.out.println("Hospital with this id not found");
        return List.of();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for(Hospital hospital : DataBase.hospitals){
            for(Department department : hospital.getDepartments())
                if(department.getId().equals(id)){
                    return doctorDaoImp.getAllDoctorsByDepartmentId(DataBase.hospitals.indexOf(hospital)
                                                                   ,hospital.getDepartments().indexOf(department));
                }
        }
        System.out.println("Department with this id not found");
        return List.of();
    }
}
