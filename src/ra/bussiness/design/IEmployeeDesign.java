package ra.bussiness.design;

import ra.bussiness.entity.Employee;

public interface IEmployeeDesign extends IGeneric<Employee,Integer> {
    void getListEmployeeByDerpartment();
    void sortNameEmployee();
}
