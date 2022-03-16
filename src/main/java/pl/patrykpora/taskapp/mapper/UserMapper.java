package pl.patrykpora.taskapp.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.patrykpora.taskapp.dto.UserDto;
import pl.patrykpora.taskapp.entity.User;

public class UserMapper {

    public static UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setStreet(user.getStreet());
        userDto.setCity(user.getCity());
        userDto.setZip(user.getZipCode());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }

    public static User map(UserDto userDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCity(userDto.getCity());
        user.setFirstName(user.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }
}
