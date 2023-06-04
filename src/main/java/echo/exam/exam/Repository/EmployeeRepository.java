package echo.exam.exam.Repository;

import echo.exam.exam.Entity.Employee;
import echo.exam.exam.Entity.JobHistory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;
    /**
     * 특정 직원 ID로 직원을 조회하는 메서드입니다.
     *
     * @param employeeId 직원 ID
     * @return 직원 목록
     */
    public List<Employee> findEmployeeById(Integer employeeId){
        String jpql = "SELECT e FROM Employee e " +
                "JOIN e.job j " +
                "JOIN e.department d " +
                "JOIN d.location l " +
                "JOIN l.country c " +
                "JOIN c.region r " +
                "WHERE e.employeeId = :employeeId";

        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("employeeId", employeeId);

        return query.getResultList();
    }

    /**
     * 특정 직원의 직무 이력을 조회하는 메서드입니다.
     *
     * @param employeeId 직원 ID
     * @return 직무 이력 목록
     */
    public List<JobHistory> findEmployeeJobHistory(Integer employeeId) {
        String jpql = "SELECT jh FROM JobHistory jh " +
                "JOIN jh.employee e " +
                "JOIN jh.job j " +
                "JOIN jh.department d " +
                "WHERE e.employeeId = :employeeId";

        TypedQuery<JobHistory> query = entityManager.createQuery(jpql, JobHistory.class);
        query.setParameter("employeeId", employeeId);

        return query.getResultList();
    }
}
