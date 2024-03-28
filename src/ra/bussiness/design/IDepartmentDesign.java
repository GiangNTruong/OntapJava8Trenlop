package ra.bussiness.design;

import ra.bussiness.entity.Department;

public interface IDepartmentDesign extends IGeneric<Department,Integer> {
    void findNameDepartment(String departmentName);
}
