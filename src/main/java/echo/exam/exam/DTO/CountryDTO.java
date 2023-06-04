package echo.exam.exam.DTO;

import echo.exam.exam.Entity.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO {

    private String countryId;
    private String countryName;
    /**
     * CountryDTO의 생성자입니다.
     * 주어진 Country 객체로부터 CountryDTO를 생성합니다.
     *
     * @param country Country 객체
     */
    public CountryDTO(Country country) {
        this.countryId = country.getCountryId();
        this.countryName = country.getCountryName();
    }
}
