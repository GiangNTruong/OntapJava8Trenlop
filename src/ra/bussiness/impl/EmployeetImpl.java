package ra.bussiness.impl;

import ra.bussiness.design.IEmployeeDesign;
import ra.bussiness.entity.Department;
import ra.bussiness.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static ra.bussiness.impl.DepartmentImpl.departmentList;

public class EmployeetImpl implements IEmployeeDesign {
    static List<Employee> employeeList = new ArrayList<>();
    @Override
    public void getListEmployeeByDerpartment() {
        System.out.println("Danh sách phòng ban ");
        departmentList.forEach(department -> System.out.printf("| ID : %-2s | DepartmentName : %-10s |\n", department.getId(), department.getName()));
        System.out.println("Hãy chọn department bạn muốn xem danh sách ");
        int id = new Scanner(System.in).nextInt();

        // Tìm phòng ban theo ID
        Optional<Department> foundDepartment = departmentList.stream()
                .filter(department -> department.getId() == id)
                .findFirst();

        if (foundDepartment.isPresent()) {
            List<Employee> filterList = employeeList.stream()
                    .filter(e -> e.getDepartment() != null && e.getDepartment().getId() == id)
                    .collect(Collectors.toList());

            if (filterList.isEmpty()) {
                System.err.println("Phòng ban này trống");
            } else {
                filterList.forEach(Employee::displayData);
            }
        } else {
            System.err.println("ID không tồn tại");
        }
    }


    @Override
    public void sortNameEmployee() {
        // Sắp xếp danh sách nhân viên theo tên tăng dần
        Collections.sort(employeeList, Comparator.comparing(Employee::getFullName));
        // Hiển thị danh sách sau khi sắp xếp
        for (Employee employee : employeeList) {
            employee.displayData();
        }
    }

    @Override
    public void displayAll() {
        if (employeeList.isEmpty()) {
            System.err.println("Danh sách trống");
        } else {
            int pageSize = 2;
            for (int i = 0; i < employeeList.size(); i += pageSize) {
                System.out.println("|   Trang " + (i / pageSize + 1) + " |");
                for (int j = i; j < Math.min(i + pageSize, employeeList.size()); j++) {
                    employeeList.get(j).displayData();
                }
                System.out.println();

                if (i + pageSize < employeeList.size()) {
                    System.out.println("Nhấn 1 để xem trang tiếp theo , 2 để thoát");
                    Scanner scanner = new Scanner(System.in);
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:

                            break;
                        case 2:

                            return;
                        default:
                            System.out.println("Lựa chọn không hợp lệ, chọn lại.");
                    }
                }
            }
        }
    }


    @Override
    public void addNew() {
        if (departmentList.isEmpty()) {
            System.err.println("chưa có phong ban, them phong ban trươc");
            return;
        }
        System.out.println("Nhap so luong nhan vien muon them");
        byte count = new Scanner(System.in).nextByte();
        for (int i = 1; i <= count; i++) {
            System.out.println("Nhap thong tin cho nhan vien thư " + i);
            Employee employee = new Employee();
            employee.inputData( departmentList, employeeList);
            employeeList.add(employee);
        }
        System.out.println("Đã them moi thanh cong ");
    }

    @Override
    public void update() {
        System.out.println("Hãy chọn department muốn update");
        int idEdit = new Scanner(System.in).nextInt();
        Employee edit = findById(idEdit);
        if (edit == null) {
            System.err.println("id không tim thấy");
            return;
        }

        System.out.println("Thông tin cũ ");
        edit.displayData();

        // trừ số lương phòng ban cũ đi 1
        Department old = edit.getDepartment();
        old.setNumberEmployee(old.getNumberEmployee() - 1);

        System.out.println("Nhập thông tin mới");
        edit.inputData( departmentList,employeeList);
        System.out.println("Cập nhật thành công");
    }

    @Override
    public Employee changeOfStatus(Integer id, boolean active) {
        // Tìm phòng ban theo ID
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                // Cập nhật trạng thái
                employee.setStatus(active);
                return employee;
            }
        }
        // Nếu không tìm thấy phòng ban với ID tương ứng
        System.out.println("Không tìm thấy phòng ban với ID: " + id);
        return null;

    }

    @Override
    public Employee findById(Integer id) {
        for (Employee e : employeeList) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
