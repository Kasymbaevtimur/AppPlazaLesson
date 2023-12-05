package peaksoft.appplazalesson.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String name;
    private String lastName;
    private String email;
    private int age;
    private String password;
    private boolean subscribe;

}
