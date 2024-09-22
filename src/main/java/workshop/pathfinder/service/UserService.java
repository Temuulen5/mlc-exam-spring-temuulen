package workshop.pathfinder.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import workshop.pathfinder.domain.DTOs.ProfileEditForm;
import workshop.pathfinder.domain.DTOs.UserLoginForm;
import workshop.pathfinder.domain.DTOs.UserRegisterForm;
import workshop.pathfinder.domain.entities.UserRole;
import workshop.pathfinder.domain.enums.Role;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.entities.User;
import workshop.pathfinder.repository.UserRepository;
import workshop.pathfinder.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userRoleRepository = userRoleRepository;
    }

    public boolean UserExistsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public boolean PasswordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public void registerUser(UserRegisterForm userRegisterForm) {
        if (!UserExistsByUsername(userRegisterForm.getUserName())) {
            if (PasswordsMatch(userRegisterForm.getPassword(), userRegisterForm.getConfirmPassword())) {
                User user = this.modelMapper.map(userRegisterForm, User.class);

                //generating role
                UserRole userRole = new UserRole();
                userRole = userRoleRepository.findUserRoleByRole(Role.valueOf(String.valueOf(userRegisterForm.getRole()).toUpperCase()));
                if (userRole == null) {
                    userRole.setRole(Role.valueOf(userRegisterForm.getRole()));
                    userRole = this.userRoleRepository.save(userRole);
                }

                user.setRoles(Collections.singletonList(userRole));

                //saving data
                this.userRepository.save(user);
            }
        }
    }

    public void LoginUser(UserLoginForm userLoginForm) {
        User user = this.userRepository.getByUsername(userLoginForm.getUserName());
        if (user != null && user.getPassword().equals(userLoginForm.getPassword())) {
            modelMapper.map(user, this.loggedUser);
            Role role = user.getRoles().get(0).getRole();
            loggedUser.setRole(role.toString());
        }
    }

    public void EditProfile(ProfileEditForm profileEditForm) {
        User user = this.userRepository.findUsersById(loggedUser.getId());
        user.setUsername(profileEditForm.getUserName());
        user.setAge(profileEditForm.getAge());
        this.userRepository.saveAndFlush(user);
        this.loggedUser.Logout();
        this.modelMapper.map(user, this.loggedUser);
    }
}
