package echo.exam.exam.Service;

import echo.exam.exam.Entity.Department;
import echo.exam.exam.Entity.Employee;
import echo.exam.exam.Entity.Location;
import echo.exam.exam.Repository.DepartmentRepository;
import echo.exam.exam.Util.Exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * 모든 부서를 조회하는 메서드입니다.
     *
     * @return 부서 목록
     */
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    /**
     * 특정 부서 ID로 부서를 조회하는 메서드입니다.
     *
     * @param departmentId 부서 ID
     * @return 부서 정보
     */
    public Department getDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }

    /**
     * 모든 위치를 조회하는 메서드입니다.
     *
     * @return 위치 목록
     */
    public List<Location> getAllLocations() {
        return departmentRepository.findAllLocations();
    }

    /**
     * 특정 위치 ID로 위치를 조회하는 메서드입니다.
     *
     * @param locationId 위치 ID
     * @return 위치 정보
     */
    public Location getLocationById(Integer locationId) {
        return departmentRepository.findLocationById(locationId);
    }

    /**
     * 특정 부서의 직원들의 급여를 증가시키는 메서드입니다.
     *
     * @param departmentId 부서 ID
     * @param increaseRate 증가율
     * @return 업데이트된 부서 정보
     * @throws NotFoundException       부서가 존재하지 않을 경우 발생하는 예외
     * @throws IllegalArgumentException 증가율이 유효하지 않을 경우 발생하는 예외
     */
    public Department increaseSalaryByRate(Integer departmentId, BigDecimal increaseRate) {
        // departmentId를 기반으로 department를 조회합니다.
        // 조회되지 않을 경우 NotFoundException이 발생합니다.
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department not found"));

        // 조회된 department가 null인 경우에도 NotFoundException이 발생합니다.
        if (department == null) {
            throw new NotFoundException("Department not found");
        }

        // increaseRate가 null이거나 0보다 작은 경우에는 IllegalArgumentException이 발생합니다.
        if (increaseRate == null || increaseRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("0보다 작거나 값이 없습니다. 값 : " + increaseRate);
        }

        // 증가율에 1을 더하여 increaseFactor를 계산합니다.
        BigDecimal increaseFactor = BigDecimal.ONE.add(increaseRate);

        // department에 속한 모든 employees를 조회합니다.
        List<Employee> employees = department.getEmployees();

        // 모든 employees의 salary에 increaseFactor를 곱하여 업데이트합니다.
        for (Employee employee : employees) {
            BigDecimal currentSalary = employee.getSalary();
            BigDecimal updatedSalary = currentSalary.multiply(increaseFactor);
            employee.setSalary(updatedSalary);
        }

        // 변경된 department를 저장하고 반환합니다.
        return departmentRepository.save(department);
    }
}
