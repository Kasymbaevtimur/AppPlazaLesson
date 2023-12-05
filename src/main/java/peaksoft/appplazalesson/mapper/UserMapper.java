package peaksoft.appplazalesson.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplazalesson.model.dto.RegistrationRequest;
import peaksoft.appplazalesson.model.dto.RegistrationResponse;
import peaksoft.appplazalesson.model.dto.UserResponse;
import peaksoft.appplazalesson.model.entities.User;
import peaksoft.appplazalesson.model.enums.Status;


import java.time.LocalDate;

@Component
public class UserMapper {
    // Бул метод реквесттен келген классты ентити класска откорот
    public User mapToEntity(RegistrationRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());
        user.setStatus(Status.BASIC);
        user.setLocalDate(LocalDate.now());
        user.setSubscribe(request.isSubscribe());
        return user;
    }
    public RegistrationResponse mapToResponse(User user){
        return RegistrationResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .age(user.getAge())
                .status(user.getStatus())
                .response("Success Registered").build();
    }
    public UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .age(user.getAge())
                .status(user.getStatus())
                .subscribe(user.isSubscribe())
                .localDate(user.getLocalDate()).build();
    }

}
