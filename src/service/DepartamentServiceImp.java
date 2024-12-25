package service;
import dao.DepartamentDaoImp;
import dataBase.DataBase;
import models.Department;
import models.Hospital;
import service.Interface.DepartamentService;
import service.Interface.GenericService;

import java.util.List;

public class DepartamentServiceImp implements GenericService<Department> , DepartamentService {
    public DepartamentDaoImp daoImp ;

    public DepartamentServiceImp(DepartamentDaoImp daoImp) {
        this.daoImp = daoImp;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        for(Hospital hospital : DataBase.hospitals){
            if(hospital.getId().equals(hospitalId)){
                return daoImp.add(DataBase.hospitals.indexOf(hospital),department);
            }
        }
        return "Hospital with this id not found";
    }

    @Override
    public void removeById(Long id) {
        boolean isTrue = true;
         for(Hospital hospital : DataBase.hospitals){
             for(Department department : hospital.getDepartments()){
                 if(department.getId().equals(id) ){
                     isTrue = false;
                     daoImp.removeById(hospital.getDepartments().indexOf(department),
                                       DataBase.hospitals.indexOf(hospital));
                     break;
                 }
             }
         }
         if(isTrue) System.out.println("Department with this id not found");
    }

    @Override
    public String updateById(Long id, Department department) {
        for(Hospital hospital : DataBase.hospitals){
            for(Department department1 : hospital.getDepartments()){
                if(department1.getId().equals(id)){
                    return daoImp.updateById(DataBase.hospitals.indexOf(hospital),
                                              hospital.getDepartments().indexOf(department1),department);
                }
            }
        }
        return "Department with this id not found";
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        for(Hospital hospital : DataBase.hospitals){
            if(hospital.getId().equals(id)){
                return daoImp.getAllDepartmentByHospital(DataBase.hospitals.indexOf(hospital));
            }
        }
        System.out.println("Hospital with this id not found");
        return List.of();
    }

    @Override
    public Department findDepartmentByName(String name) {
        for(Hospital hospital : DataBase.hospitals){
            for(Department department : hospital.getDepartments()){
                if(department.getDepartmentName().equals(name)){
                    return daoImp.findDepartmentByName(DataBase.hospitals.indexOf(hospital)
                                                       ,hospital.getDepartments().indexOf(department));
                }
            }
        }
        return null;
    }
}
