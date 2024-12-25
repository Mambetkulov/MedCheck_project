package dao;
import dao.interfacDao.HospitalDao;
import dataBase.DataBase;
import models.Hospital;
import models.Patient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDaoImp implements HospitalDao {


    @Override
    public String addHospital(Hospital hospital) {
        DataBase.hospitals.add(hospital);
        return "successfully added";
    }

    @Override
    public Hospital findHospitalById(int index) {
        return DataBase.hospitals.get(index);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return DataBase.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(int index) {
        return DataBase.hospitals.get(index).getPatients();
    }

    @Override
    public String deleteHospitalById(int index) {
        DataBase.hospitals.remove(index);
        return "successfully deleted";
    }

    @Override
    public Map<Integer, Hospital> getAllHospitalByAddress(String address) {
        int key = 0;
        Map<Integer,Hospital> hospitalByAddress = new HashMap<>();
        for(Hospital hospital : DataBase.hospitals){
            if(hospital.getAddress().equals(address)){
                hospitalByAddress.put(++key,hospital);
            }
        }
        return hospitalByAddress;
    }
}
