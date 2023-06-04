package echo.exam.exam.Service;

import echo.exam.exam.Entity.Employee;
import echo.exam.exam.Repository.EmployeeRepository;
import echo.exam.exam.Util.Exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * 특정 직원 ID로 직원을 조회하는 메서드입니다.
     *
     * @param employeeId 직원 ID
     * @return 직원 목록
     * @throws NotFoundException 직원이 존재하지 않을 경우 발생하는 예외
     */
    public List<Employee> findEmployeeById(Integer employeeId) {
        List<Employee> employees = employeeRepository.findEmployeeById(employeeId);
        if (employees.isEmpty()) {
            throw new NotFoundException("No employee details found for ID: " + employeeId);
        }
        return employees;
    }

    /**
     * 특정 직원 ID로 직원의 작업 이력을 조회하는 메서드입니다.
     *
     * @param employeeId 직원 ID
     * @return 직원의 작업 이력 목록
     * @throws NotFoundException 직원이 존재하지 않을 경우 발생하는 예외
     */
    public List<Employee> findEmployeeDetailsByJobHistoryEmployeeId(Integer employeeId) {
        List<Employee> employees = employeeRepository.findEmployeeById(employeeId);
        if (employees.isEmpty()) {
            throw new NotFoundException("No employee details found for ID: " + employeeId);
        }
        return employees;
    }
}
