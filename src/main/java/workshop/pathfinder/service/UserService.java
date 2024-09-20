package workshop.pathfinder.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import workshop.pathfinder.domain.DTOs.UserLoginForm;
import workshop.pathfinder.domain.DTOs.UserRegisterBindingModel;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.entities.User;
import workshop.pathfinder.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public boolean UserExistsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public boolean PasswordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        if (!UserExistsByUsername(userRegisterBindingModel.getUserName())) {
            if (PasswordsMatch(userRegisterBindingModel.getPassword(), userRegisterBindingModel.getConfirmPassword())) {
                User user = this.modelMapper.map(userRegisterBindingModel, User.class);
                this.userRepository.saveAndFlush(user);
                System.err.println("REGISTERED USER :" + user.toString());
            }
        }
    }

    public void LoginUser(UserLoginForm userLoginForm) {
        User user = this.userRepository.getByUsername(userLoginForm.getUserName());
        if (user != null && user.getPassword().equals(userLoginForm.getPassword())) {
            modelMapper.map(user, this.loggedUser);
            System.err.println("USER LOGGED WITH ID:" + loggedUser.getId());
        } else {
            System.err.println("LOGIN FAILED");
        }
    }
}
