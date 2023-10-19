package api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    String firstName;
    String lastName;
    String birthdate;
    String email;
    String phone;
    String street1;
    String street2;
    String city;
    String stateProvince;
    String postalCode;
    String country;

}
