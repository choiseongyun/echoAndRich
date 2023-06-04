package echo.exam.exam.DTO;

import echo.exam.exam.Entity.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private Integer locationId;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private CountryDTO country;
    /**
     * LocationDTO의 생성자입니다.
     * 주어진 Location 객체로부터 LocationDTO를 생성합니다.
     *
     * @param location Location 객체
     */
    public LocationDTO(Location location) {
        this.locationId = location.getLocationId();
        this.streetAddress = location.getStreetAddress();
        this.postalCode = location.getPostalCode();
        this.city = location.getCity();
        this.stateProvince = location.getStateProvince();
        this.country = new CountryDTO(location.getCountry());
    }
}
