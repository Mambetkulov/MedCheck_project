package dao;

import dao.interfacDao.DepartamentDao;
import dao.interfacDao.GenericDao;
import dataBase.DataBase;
import models.Department;

import java.util.List;

public class DepartamentDaoImp implements GenericDao<Department>, DepartamentDao {


    @Override
    public List<Department> getAllDepartmentByHospital(int index) {
        return DataBase.hospitals.get(index).getDepartments();
    }

    @Override
    public Department findDepartmentByName(int index , int index2) {
        return DataBase.hospitals.get(index).getDepartments().get(index2);
    }

    @Override
    public String add(int index, Department department) {
        DataBase.hospitals.get(index).getDepartments().add(department);
        return "successfully added";
    }

    @Override
    public void removeById(int index,int hosIndex) {
        DataBase.hospitals.get(hosIndex).getDepartments().remove(index);
        System.out.println("successfully deleted");
    }

    @Override
    public String updateById(int index,int index2, Department department) {
        DataBase.hospitals.get(index).getDepartments().set(index2,department);
        return "successfully updated";
    }
}
