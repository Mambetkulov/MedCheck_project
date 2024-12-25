package dao.interfacDao;

import models.Patient;

import java.util.List;
import java.util.Map;

public interface PatientDao {

    String addPatientsToHospital(int index, List<Patient> patients);

    Patient getPatientById(int index,int indexP);

    Map<Integer,List<Patient> > getPatientByAge();

    List<Patient> sortPatientsByAge(String ascOrDesc);
}
