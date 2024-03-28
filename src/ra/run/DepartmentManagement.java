package ra.run;

import ra.bussiness.design.IDepartmentDesign;
import ra.bussiness.impl.DepartmentImpl;

import java.util.Scanner;

public class DepartmentManagement {
    static IDepartmentDesign departmentDesign = new DepartmentImpl();

    public void displayDepartmentMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("================Department Menu===================");

            System.out.println("▪1.Hiển thị tất cả phòng ban\n" +
                    "▪2.Thêm mới phòng ban\n" +
                    "▪3.Sửa thông tin phòng ban\n" +
                    "▪4.Tìm kiếm phòng ban theo tên\n" +
                    "▪5.Thay đổi trạng thái phòng ban\n");
            System.out.println("6.Thoát");
            System.out.println("Chọn chức năng");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    departmentDesign.displayAll();
                    break;
                case 2:
                    departmentDesign.addNew();
                    break;
                case 3:
                    departmentDesign.update();
                    break;
                case 4:
                    System.out.println("Nhập tên phòng ban cần tìm kiếm:");
                    String departmentName = scanner.nextLine();
                    departmentDesign.findNameDepartment(departmentName);
                    break;
                case 5:
                    System.out.println("Nhập ID phòng ban cần thay đổi trạng thái:");
                    int idToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.println("Nhập trạng thái mới (true hoặc false):");
                    boolean newStatus = Boolean.parseBoolean(scanner.nextLine());
                    departmentDesign.changeOfStatus(idToUpdate, newStatus);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
