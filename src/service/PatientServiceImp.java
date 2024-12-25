package service;
import dao.PatientDaoImp;
import dataBase.DataBase;
import models.Hospital;
import models.Patient;
import service.Interface.GenericService;
import service.Interface.PatientService;
import java.util.List;
import java.util.Map;

public class PatientServiceImp implements GenericService<Patient> , PatientService {
    public PatientDaoImp patientDaoImp;

    public PatientServiceImp(PatientDaoImp patientDaoImp) {
        this.patientDaoImp = patientDaoImp;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        for(Hospital hospital : DataBase.hospitals){
            if(hospital.getId().equals(hospitalId)){
                return patientDaoImp.add(DataBase.hospitals.indexOf(hospital),patient);
            }
        }
        return "Hospital with this id not found";
    }

    @Override
    public void removeById(Long id) {
        boolean isTrue = true;
      for(Hospital hospital : DataBase.hospitals){
          for(Patient patient : hospital.getPatients()){
              if(patient.getId().equals(id)){
                  isTrue = false;
                  patientDaoImp.removeById(hospital.getPatients().indexOf(patient),
                          DataBase.hospitals.indexOf(hospital));
                  break;
              }
          }
      }
      if(isTrue) System.out.println("Patient with this id not found");
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for(Hospital hospital : DataBase.hospitals){
            for(Patient patient2 : hospital.getPatients()){
                if(patient2.getId().equals(id)){
                 return patientDaoImp.updateById(DataBase.hospitals.indexOf(hospital),
                                                 hospital.getPatients().indexOf(patient2),patient);
                }
            }
        }
        return "Patient with this id not found";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        for(Hospital hospital : DataBase.hospitals){
            if(hospital.getId().equals(id)){
                return patientDaoImp.addPatientsToHospital(DataBase.hospitals.indexOf(hospital),patients);
            }
        }
        return "Hospital with this id not found";
    }

    @Override
    public Patient getPatientById(Long id) {
        for(Hospital hospital : DataBase.hospitals){
            for(Patient patient : hospital.getPatients()){
                if(patient.getId().equals(id)){
                  return   patientDaoImp.getPatientById(DataBase.hospitals.indexOf(hospital),
                            hospital.getPatients().indexOf(patient));
                }
            }
        }
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        return patientDaoImp.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return patientDaoImp.sortPatientsByAge(ascOrDesc);
    }
}
