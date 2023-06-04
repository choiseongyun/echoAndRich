package echo.exam.exam.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class JobHistoryId implements Serializable {

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "start_date")
    private LocalDate startDate;
}
