package echo.exam.exam.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "job_history")
@Getter
@Setter
public class JobHistory {

    @EmbeddedId
    private JobHistoryId id;

    @ManyToOne
    @MapsId("employeeId")
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "start_date",insertable=false, updatable=false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;


}
