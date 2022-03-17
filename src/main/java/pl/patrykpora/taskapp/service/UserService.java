package pl.patrykpora.taskapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.patrykpora.taskapp.dto.UserDto;
import pl.patrykpora.taskapp.entity.User;
import pl.patrykpora.taskapp.mapper.UserMapper;
import pl.patrykpora.taskapp.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(UserDto userDto){
        User user = UserMapper.map(userDto, passwordEncoder);
        user = userRepo.save(user);

        return user.getId() !=0;
    }

    public UserDto getUserbyUsername(String username) {
        User user = userRepo.findByUsername(username);
        return UserMapper.map(user);
    }

}
