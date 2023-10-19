package api.Generator;

import api.payload.Contact;
import api.payload.User;
import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PayloadGenerator {
    Contact contactPayload;
    Contact contactPayloadPut;
    User userPayload;
    Faker faker;
    public Contact ContactPayloadGenerator(){
        faker = new Faker();
        contactPayload = new Contact();
        contactPayload.setFirstName(faker.name().firstName());
        contactPayload.setLastName(faker.name().lastName());
        Date fakeBirthDay = faker.date().birthday();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        contactPayload.setBirthdate(dateFormat.format(fakeBirthDay));

        contactPayload.setEmail(faker.internet().safeEmailAddress());
        contactPayload.setPhone(faker.number().digits(10));
        contactPayload.setStreet1(faker.address().streetAddress());
        contactPayload.setStreet2(faker.address().streetAddress());
        contactPayload.setCity(faker.address().city());
        contactPayload.setStateProvince(faker.address().state());
        contactPayload.setPostalCode(faker.address().zipCode());
        contactPayload.setCountry(faker.address().country());

        return contactPayload;
    }

    public User userPayloadGenerator(){
        userPayload = new User();
        faker = new Faker();
;        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());
        return userPayload;
    }

    public Contact updateContactPayload(){
        faker = new Faker();
        contactPayloadPut = new Contact();
        contactPayloadPut.setFirstName(faker.name().firstName());
        contactPayloadPut.setLastName(faker.name().lastName());
        contactPayloadPut.setBirthdate("2001-01-01");

        contactPayloadPut.setEmail(contactPayload.getEmail());
        contactPayloadPut.setPhone("123456432");
        contactPayloadPut.setStreet1(faker.address().streetAddress());
        contactPayloadPut.setStreet2(contactPayload.getStreet2());
        contactPayloadPut.setCity(contactPayload.getCity());
        contactPayloadPut.setStateProvince(contactPayload.getStateProvince());
        contactPayloadPut.setPostalCode("12345");
        contactPayloadPut.setCountry(contactPayload.getCountry());
        return contactPayloadPut;
    }
    public Contact payloadForPatchMethod(){
        faker = new Faker();
        contactPayload.setFirstName("FName");
        return contactPayload;
    }
}
