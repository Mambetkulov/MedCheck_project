package service.Interface;

import models.Department;

import java.util.List;

public interface DepartamentService {

    List<Department> getAllDepartmentByHospital(Long id);

    Department findDepartmentByName(String name);
}
