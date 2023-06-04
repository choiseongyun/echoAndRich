package echo.exam.exam.DTO;

import echo.exam.exam.Entity.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    /**
     * EmployeeDTO의 생성자입니다.
     * 주어진 Employee 객체로부터 EmployeeDTO를 생성합니다.
     *
     * @param employee Employee 객체
     */
    public EmployeeDTO(Employee employee) {
        if (employee != null) {
            this.employeeId = employee.getEmployeeId();
            this.firstName = employee.getFirstName();
            this.lastName = employee.getLastName();
            this.email = employee.getEmail();
        }
    }

}
