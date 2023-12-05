package peaksoft.appplazalesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.appplazalesson.mapper.UserMapper;
import peaksoft.appplazalesson.model.dto.RegistrationRequest;
import peaksoft.appplazalesson.model.dto.RegistrationResponse;
import peaksoft.appplazalesson.model.dto.UserResponse;
import peaksoft.appplazalesson.model.entities.Role;
import peaksoft.appplazalesson.model.entities.User;
import peaksoft.appplazalesson.repository.RoleRepository;
import peaksoft.appplazalesson.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public RegistrationResponse registration(RegistrationRequest request) {
        User user = userMapper.mapToEntity(request);
//        User user1 = new User();
//        user1.setAge(request.getAge());
        if (user.getName().length() < 2 || user.getLastName().length() < 2) {
            throw new RuntimeException("Узердин аты 2 символдон  коп болсун !");
        }
        if (!user.getEmail().contains("@")){
            throw new RuntimeException("email'де @ символ камтылышы керек");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();
        if (repository.findAll().isEmpty()){
            Role role = roleRepository.findByName("ADMIN");
            if (role == null){
                role = new Role("ADMIN");
            }
            roles.add(role);
        }else {
            Role role = roleRepository.findByName("USER");
            if (role == null){
                role = new Role("USER");
//                role.setName("USER");
            }
            roles.add(role);
        }
        user.setRoles(roles);
        repository.save(user);
        return userMapper.mapToResponse(user);
    }

    public UserResponse findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by id: " + id));
        return userMapper.mapToUserResponse(user);
    }
    public List<UserResponse> findAll(){
        System.out.println("I'm' in find all method in service layer");
        return repository.findAll()
                .stream()
                .map(userMapper::mapToUserResponse).toList();
    }

    public UserResponse update(Long userId,RegistrationRequest request){
        User oldUser = repository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found by id: " + userId));
        oldUser.setName(request.getName());
        oldUser.setAge(request.getAge());
        oldUser.setEmail(request.getEmail());
        oldUser.setSubscribe(request.isSubscribe());
        oldUser.setLastName(request.getLastName());
        repository.save(oldUser);
        return userMapper.mapToUserResponse(oldUser);
    }

    public void removeUserById(Long userId){
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id: " + userId));
        repository.delete(user);
    }


}
