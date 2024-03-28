package ra.bussiness.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Department {
    private static int idCounter = 0;
    private int id ;
    private String name ;
    private  int numberEmployee;
    private boolean status;

    public Department() {
        this.id = ++idCounter;
    }

    public Department(int id, String name, int numberEmployee, boolean status) {
        this.id = id;
        this.name = name;
        this.numberEmployee = numberEmployee;
        this.status = status;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Department.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberEmployee() {
        return numberEmployee;
    }

    public void setNumberEmployee(int numberEmployee) {
        this.numberEmployee = numberEmployee;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData( Scanner scanner, List<Department> departmentList){
           this.name = getInputnameDepartment(departmentList);
        System.out.println("Nhập trạng thái phòng ban(true hoac false)");
        this.status = inputDepartmentStatus(scanner);
    }

    public void displayData(){
        System.out.println("Department ID: " + this.id);
        System.out.println("Department Name: " + this.name);
        System.out.println("Number of Employees: " + this.numberEmployee);
        System.out.println("Status: " + (this.status ? "Active" : "Inactive"));
        System.out.println("_--------------------------------");
    }

    public String getInputnameDepartment(List<Department> departmentList){
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Nhập tên của Department ");
            String inputNameDepartment = scanner.nextLine();
            // Kiểm tra xem tên có để trống không
            if (inputNameDepartment == null || inputNameDepartment.trim().isEmpty()) {
                System.out.println("Tên không được để trống");
                return null;
            }

            // Kiểm tra xem tên có trùng lặp không
            for (Department department : departmentList) {
                if (department.getName().equals(inputNameDepartment)) {
                    System.out.println("Tên đã tồn tại");
                    return null;
                }
            }
            return inputNameDepartment;
        } while (true);
    }
    private boolean inputDepartmentStatus(Scanner scanner){
        System.out.println("Nhập vào trạng thái nhân viên");
        do {
            String statusDepartment = scanner.nextLine();
            if (statusDepartment.equals("true")|| statusDepartment.equals("false")){
                return Boolean.parseBoolean(statusDepartment);
            }else {
                System.err.println("Trạng thái phòng ban chỉ nhận true or false , vui lòng nhập lại : ");
            }
        }while (true);
    }
}
