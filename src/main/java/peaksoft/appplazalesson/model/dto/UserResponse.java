package peaksoft.appplazalesson.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplazalesson.model.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String lastName;
    private int age;
    private Status status;
    private boolean subscribe;
    private LocalDate localDate;
}
