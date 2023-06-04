package echo.exam.exam.Repository;

import echo.exam.exam.Entity.Department;
import echo.exam.exam.Entity.Location;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    /**
     * 모든 위치 정보를 조회하는 쿼리입니다.
     *
     * @return 위치 목록
     */
    @Query("SELECT d FROM Location d")
    List<Location> findAllLocations();

    /**
     * 특정 위치 ID로 위치를 조회하는 쿼리입니다.
     *
     * @param locationId 위치 ID
     * @return 위치 객체
     */
    @Query("SELECT d FROM Location d WHERE d.locationId = :locationId")
    Location findLocationById(@Param("locationId") Integer locationId);
}