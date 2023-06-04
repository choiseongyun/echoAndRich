package echo.exam.exam.DTO;

import echo.exam.exam.Entity.Department;
import echo.exam.exam.Entity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    private List<EmployeeDTO> employees;
    private EmployeeDTO manager;  // 직원 정보
    private LocationDTO location;  // 위치 정보
    /**
     * DepartmentDTO의 생성자입니다.
     * 주어진 Department 객체로부터 DepartmentDTO를 생성합니다.
     *
     * @param department Department 객체
     */
    public DepartmentDTO(Department department) {
        this.departmentId = department.getDepartmentId();
        this.departmentName = department.getDepartmentName();
        this.manager = department.getManager() != null ? new EmployeeDTO(department.getManager()) : null;
        if (department.getEmployees() != null) {
            this.employees = department.getEmployees().stream()
                    .map(EmployeeDTO::new)
                    .collect(Collectors.toList());
        } else {
            this.employees = new ArrayList<>();
        }
        this.location = department.getLocation() != null ? new LocationDTO(department.getLocation()) : null;
    }

}
