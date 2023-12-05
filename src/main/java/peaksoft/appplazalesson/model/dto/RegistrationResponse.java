package peaksoft.appplazalesson.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplazalesson.model.enums.Status;

@Getter
@Setter
@Builder
public class RegistrationResponse {
    private Long id;
    private String name;
    private String email;
    private String lastName;
    private int age;
    private Status status;
    private String response;

}
