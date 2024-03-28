package ra.run;

import java.util.Scanner;

public class MenuManagement {
    private static DepartmentManagement departmentManagement = new DepartmentManagement();
    private static EmployeeManagement employeeManagement = new EmployeeManagement();
    public static void main(String[] args) {
        while (true){
            System.out.println("================MENU===================");
            System.out.println("1- Quản trị phòng ban : \n" +
                    "2- Quản lý nhân viên\n" +
                    "3.Thoát ");

            System.out.println("Nhập lựa chọn");
            byte choice = new Scanner(System.in).nextByte();
            switch (choice){
                case 1:
                    departmentManagement.displayDepartmentMenu();
                    break;
                case 2:
                    employeeManagement.displayEmployeeMenu();
                    break;
                case 3:
                    System.out.println("Thoát");
                    return;
                default:
                    System.err.println("Nhập 1 - 3 thoi , nhap lai");
            }
        }
    }
}
