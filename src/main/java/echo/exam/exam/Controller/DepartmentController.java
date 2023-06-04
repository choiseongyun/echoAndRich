package echo.exam.exam.Controller;

import echo.exam.exam.DTO.DepartmentDTO;
import echo.exam.exam.DTO.LocationDTO;
import echo.exam.exam.Entity.Department;
import echo.exam.exam.Entity.Location;
import echo.exam.exam.Service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // 모든 부서 조회
    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        List<DepartmentDTO> departmentDTOs = departments.stream()
                .map(DepartmentDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(departmentDTOs);
    }

    // 특정 부서 ID로 부서 조회
    @GetMapping("/get-departments/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Integer departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        DepartmentDTO departmentDTO = new DepartmentDTO(department);
        return ResponseEntity.ok(departmentDTO);
    }

    // 모든 위치 조회
    @GetMapping("/locations")
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<Location> locations = departmentService.getAllLocations();
        List<LocationDTO> locationDTOs = locations.stream()
                .map(LocationDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(locationDTOs);
    }

    // 특정 위치 ID로 위치 조회
    @GetMapping("/get-locations/{locationId}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Integer locationId) {
        Location location = departmentService.getLocationById(locationId);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        LocationDTO locationDTO = new LocationDTO(location);
        return ResponseEntity.ok(locationDTO);
    }

    // 특정 부서의 급여를 비율로 증가시킴
    @PostMapping("/increase-salary/{departmentId}")
    public ResponseEntity<String> increaseSalaryByRate(
            @PathVariable Integer departmentId,
            @RequestParam BigDecimal increaseRate) {
        try {
            Department department = departmentService.increaseSalaryByRate(departmentId, increaseRate);
            DepartmentDTO departmentDTO = new DepartmentDTO(department);
            JSONObject json = new JSONObject();
            json.put("status", "success");
            json.put("message", "급여 증가가 성공적으로 이루어졌습니다");
            return ResponseEntity.ok(json.toJSONString());
        } catch (Exception e) {
            // 에러 메시지를 위한 JSON 객체 생성
            JSONObject responseJson = new JSONObject();
            responseJson.put("status", "error");
            responseJson.put("message", "급여 증가 실패: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson.toJSONString());
        }
    }
}
