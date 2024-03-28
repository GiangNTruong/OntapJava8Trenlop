package ra.run;

import ra.bussiness.design.IEmployeeDesign;
import ra.bussiness.impl.EmployeetImpl;

import java.util.Scanner;

public class EmployeeManagement {
    static IEmployeeDesign employeeDesign = new EmployeetImpl();
    public void displayEmployeeMenu(){
        Scanner scanner= new Scanner(System.in);
        while (true) {

            System.out.println("================Employee Menu===================");
            System.out.println("▪1.Hiển thị tất cả nhân viên (phân trang)\n" +
                    "▪2.Thêm mới nhân viên\n" +
                    "▪3.Sửa thông tin nhân viên\n" +
                    "▪4.Thay đổi trạng thái nhân viên\n" +
                    "▪5.Danh sách nhân viên theo phòng ban\n" +
                    "▪6.Sắp xếp nhân viên theo tên tăng dần\n");
            System.out.println("7.Thoát");
            System.out.println("Nhập lựa chọn");
            byte choice = new Scanner(System.in).nextByte();
            switch (choice){
                case 1: employeeDesign.displayAll();
                break;
                case 2:
                    employeeDesign.addNew();
                    break;
                case 3:
                    employeeDesign.update();
                    break;
                case 4:
                    System.out.println("Nhập ID nhân viên cần thay đổi trạng thái:");
                    int idToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.println("Nhập trạng thái mới (true hoặc false):");
                    boolean newStatus = Boolean.parseBoolean(scanner.nextLine());
                    employeeDesign.changeOfStatus(idToUpdate, newStatus);
                    break;
                case 5:
                    employeeDesign.getListEmployeeByDerpartment();
                    break;
                case 6:
                    employeeDesign.sortNameEmployee();
                    break;
                case 7: return;
                default:
                    System.err.println("Nhập từ 1-7 thôi , nhập lại");
            }
        }
        }
}
