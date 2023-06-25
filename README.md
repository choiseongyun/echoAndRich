# restfulAPI
AWS 반영

http://54.180.114.52:8080
api 구현

특정 직원의 현재 정보를 가져오는 API (GET)

(http://54.180.114.52:8080/api/employees/{employeeId})  

(employeeId 는 100~ 206 까지의 Integer 숫자)

특정 직원의 이력 정보를 가져오는 API (GET)

(http://54.180.114.52:8080/api/employees-record/{employeeId}) 

(employeeId 는 100~ 206 까지의 Integer 숫자)

모든 부서 조회 API (GET)

(http://54.180.114.52:8080/api/departments)

특정 부서 ID로 부서 조회 API (GET)
(http://54.180.114.52:8080/api/get-departments/{departmentId}) 

(departmentId는 10 부터 시작하여 10의 배수로 늘어나며 270까지의 Integer 숫자)

모든 위치 조회 API (GET)
(http://54.180.114.52:8080/api/locations)

특정 위치 ID로 위치 조회 API (GET)

(http://54.180.114.52:8080/api/get-locations/{locationId})

(locationId는 1000부터 시작하여 100의 배수로 늘어나며 3200까지의 Integer 숫자)

특정 부서의 급여를 비율로 증가시키는 API (POST)

http://54.180.114.52:8080/api/increase-salary/{departmentId}?increaseRate={increaseRate의 Value값} 

(departmentId는 10 부터 시작하여 10의 배수로 늘어나며 270까지의 Integer 숫자)

ex) http://54.180.114.52:8080/api/increase-salary/10?increaseRate=0.1   -> 부서 아이디 10의 값을 가진 직원들은 10% 급여 인상

http://54.180.114.52:8080/safety-news/{serviceKey}/{countryName}/{countryCode}

공공 데이터 포털 외교부_국가·지역별 최신안전소식(코로나관련) (GET)


