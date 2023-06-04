package echo.exam.exam.Controller;

import echo.exam.exam.Entity.Employee;
import echo.exam.exam.Service.EmployeeService;
import echo.exam.exam.Util.Exception.NotFoundException;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    /**
     * 특정 직원의 현재 정보를 가져오는 엔드포인트입니다.
     *
     * @param employeeId 직원 ID
     * @return 직원 정보를 포함한 JSON 응답
     */
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<JSONObject> getEmployeeById(@PathVariable Integer employeeId) {
        try {
            JSONObject json = new JSONObject();
            List<Employee> employee = employeeService.findEmployeeById(employeeId);
            json.put("employee", employee);
            return ResponseEntity.ok(json);
        } catch (IllegalStateException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalStateException("오류가 발생했습니다.");
        }
    }

    /**
     * 특정 직원의 이력 정보를 가져오는 엔드포인트입니다.
     *
     * @param employeeId 직원 ID
     * @return 직원 상세 정보를 포함한 JSON 응답
     */
    @GetMapping("/employees-record/{employeeId}")
    public ResponseEntity<JSONObject> getEmployeeDetails(@PathVariable Integer employeeId) {
        JSONObject json = new JSONObject();
        try {
            List<Employee> employeeDetails = employeeService.findEmployeeDetailsByJobHistoryEmployeeId(employeeId);
            json.put("record", employeeDetails);
            return ResponseEntity.ok(json);
        } catch (NotFoundException e) {
            json.put("NOT_FOUND_ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        } catch (Exception e) {
            json.put("INTERNAL_SERVER_ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }
}
