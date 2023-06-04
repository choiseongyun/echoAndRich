package echo.exam.exam.Department;

import echo.exam.exam.Controller.DepartmentController;
import echo.exam.exam.DTO.DepartmentDTO;
import echo.exam.exam.Entity.Department;
import echo.exam.exam.Service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@WebMvcTest(DepartmentController.class)
@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    public void testIncreaseSalaryByRate() throws Exception {
        // Mock department and expected departmentDTO
        Department department = new Department();
        department.setDepartmentId(1);
        DepartmentDTO expectedDepartmentDTO = new DepartmentDTO(department);

        // Mock increase rate
        BigDecimal increaseRate = BigDecimal.valueOf(0.1); // 10% increase rate

        // Mock service method
        when(departmentService.increaseSalaryByRate(eq(1), eq(increaseRate))).thenReturn(department);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/increase-salary/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(increaseRate.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").isArray());

        // Verify the service method is called with the correct arguments
        verify(departmentService, times(1)).increaseSalaryByRate(eq(1), eq(increaseRate));
    }
}
