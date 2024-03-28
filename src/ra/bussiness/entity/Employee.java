package ra.bussiness.entity;

import ra.bussiness.config.ShopConfig;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Employee {
    private static int idCounter = 0;
    private int id;
    private String fullName;
    private String address;
    private String phone;
    private LocalDate dateOfBirth;
    private Department department;
    private boolean status;

    public Employee() {
        this.id = ++idCounter;
        this.status = true; // mặc định là true
    }

    public Employee(String fullName, String address, String phone, LocalDate dateOfBirth, Department department) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.status = true; // mặc định là true
    }


    // Phương thức để validate dữ liệu khi nhập
    public void inputData( List<Department> departmentList, List<Employee> employeeList){
        Scanner scanner = new Scanner(System.in);
           this.fullName = getInputfullNameEmployee(scanner);
        this.address = getInputaddressEmployee(scanner);
       this.phone = getInputPhoneEmployee(scanner);
       this.dateOfBirth = getInputDateOfBirth(scanner);
       this.department = getInputDepartment(scanner,departmentList);
       this.status = inputEmployeeStatus(scanner);
    }

    public String getInputfullNameEmployee(Scanner scanner){
        System.out.println("Nhập tên đầy đủ của nhân viên: ");
        String fullName = scanner.nextLine();
        while (fullName == null || fullName.trim().isEmpty()) {
            System.out.println("Tên không được để trống. Nhập lại: ");
            fullName = scanner.nextLine();
        }
        return fullName;
    }

    public String getInputaddressEmployee(Scanner scanner){
        System.out.println("Nhập địa chỉ của nhân viên: ");
        String address = scanner.nextLine();
        while (address == null || address.trim().isEmpty()) {
            System.out.println("Địa chỉ không được để trống. Nhập lại: ");
            address = scanner.nextLine();
        }
        return address;
    }

    private String getInputPhoneEmployee(Scanner scanner){
        System.out.println("Nhập số điện thoại của nhân viên: ");
        String phone = scanner.nextLine();
        while (phone == null || phone.trim().isEmpty() || !phone.matches("0[0-9]{9}")) {
            System.out.println("Số điện thoại không hợp lệ. Nhập lại: ");
            phone = scanner.nextLine();
        }
        return phone;
    }

    private LocalDate getInputDateOfBirth(Scanner scanner){
       while (true){
           System.out.println("Nhập vào ngày sinh dd/MM/yyyy");
           String employeeDateInput = scanner.nextLine();
           try {
               return LocalDate.parse(employeeDateInput, ShopConfig.DTF);
           }catch (DateTimeParseException e){
               System.err.println("Không đúng dịnh dạng");
           }
       }
    }

    private Department getInputDepartment(Scanner scanner, List<Department> departmentList) {
        System.out.println("Danh sách phòng ban:");
        for (int i = 0; i < departmentList.size(); i++) {
            System.out.printf("STT: %d | Tên phòng ban: %s\n", i + 1, departmentList.get(i).getName());
        }

        while (true) {
            System.out.println("Nhập vào vị trí phòng bạn (Theo STT) hoặc nhấn Enter để bỏ qua:");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null; // Trả về null nếu không chọn phòng ban
            }

            try {
                int index = Integer.parseInt(input);
                if (index >= 1 && index <= departmentList.size()) {
                    Department selectedDepartment = departmentList.get(index - 1);
                    // Tăng số nhân viên lên 1
                    selectedDepartment.setNumberEmployee(selectedDepartment.getNumberEmployee() + 1);
                    return selectedDepartment;
                } else {
                    System.err.println("Vị trí nhập không hợp lệ. Vui lòng chọn lại.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số nguyên hoặc nhấn Enter để bỏ qua.");
            }
        }
    }

    private boolean inputEmployeeStatus(Scanner scanner){
        System.out.println("Nhập vào trạng thái nhân viên");
        do {
            String statusEmployee = scanner.nextLine();
            if (statusEmployee.equals("true")|| statusEmployee.equals("false")){
                return Boolean.parseBoolean(statusEmployee);
            }else {
                System.err.println("Trạng thái nhân viên chỉ nhận true or false , vui lòng nhập lại : ");
            }
        }while (true);
    }


    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Employee.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void displayData() {
        System.out.println("Nhân viên ID: " + this.id +
                " | Họ và tên: " + this.fullName +
                " | Địa chỉ: " + this.address +
                " | Số điện thoại: " + this.phone +
                " | Ngày sinh: " + this.dateOfBirth.format(ShopConfig.DTF) +
                " | Phòng ban: " + (this.department !=null ? this.department.getName() : "Ko thuộc phòng ban nào") +
                " | Trạng thái: " + (this.status ? "Active" : "Inactive"));
        System.out.println("------------------------------------");
    }


}