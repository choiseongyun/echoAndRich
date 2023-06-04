package echo.exam.exam.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "emp_details_view")
@Getter
public class EmployeeDetails {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "job_id")
    private String jobId;

    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "country_id")
    private String countryId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "commission_pct")
    private BigDecimal commissionPct;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "city")
    private String city;

    @Column(name = "state_province")
    private String stateProvince;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "region_name")
    private String regionName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private List<JobHistory> jobHistoryList;
}
