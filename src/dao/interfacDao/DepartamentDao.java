package dao.interfacDao;

import models.Department;

import java.util.List;

public interface DepartamentDao {

    List<Department> getAllDepartmentByHospital(int index);

    Department findDepartmentByName(int index , int index2);
}
