package ra.bussiness.impl;

import ra.bussiness.design.IDepartmentDesign;
import ra.bussiness.entity.Department;
import ra.bussiness.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentImpl implements IDepartmentDesign {
    static List<Department> departmentList = new ArrayList<>();

    @Override
    public void findNameDepartment(String departmentName) {
        boolean found = departmentList.stream()
                .anyMatch(department -> department.getName().equalsIgnoreCase(departmentName));

        if (found) {
            System.out.println("Phòng ban có tên '" + departmentName + "' tồn tại.");
        } else {
            System.out.println("Không tìm thấy phòng ban với tên '" + departmentName + "'.");
        }
    }

    @Override
    public void displayAll() {
        if (departmentList.isEmpty()) {
            System.err.println("Danh sach rỗng");
        } else {
            departmentList.forEach(Department::displayData);
        }
    }

    @Override
    public void addNew() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so luong phong ban muon them");
        byte count = Byte.parseByte(scanner.nextLine());
        for (int i = 1; i <= count; i++) {
            System.out.println("Nhạp thong tin cho phong ban thu" + i);
            Department department = new Department();
            department.inputData( scanner, departmentList);
            departmentList.add(department);
        }
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hãy chọn phòng ban muốn cập nhật (nhập id):");
        int idToUpdate = Integer.parseInt(scanner.nextLine());

        Department edit = findById(idToUpdate);
        if (edit==null){
            System.err.println("id ko tìm thấy ");
            return;
        }
        System.out.println("Thong tin cũ là ");
        edit.displayData();

        // yêu cầu nhập thông tin mới
        System.out.println("Hãy nhap thong tin mơi ");
        edit.inputData(scanner,departmentList);
        System.out.println("Cập nhật thành công");
    }


    @Override
    public Department changeOfStatus(Integer id, boolean active) {
        // Tìm phòng ban theo ID
        for (Department department : departmentList) {
            if (department.getId() == id) {
                // Cập nhật trạng thái
                department.setStatus(active);
                return department;
            }
        }
        // Nếu không tìm thấy phòng ban với ID tương ứng
        System.out.println("Không tìm thấy phòng ban với ID: " + id);
        return null;
    }

    @Override
    public Department findById(Integer id) {
        for (Department d : departmentList) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }
}
