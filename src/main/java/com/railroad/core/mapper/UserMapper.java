package com.railroad.core.mapper;

import com.railroad.model.dto.UserDto;
import com.railroad.model.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserMapper {

    /**
     * Converts user to userDto.
     *
     * @param user Convertible user.
     * @return userDto
     */
    public UserDto userToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getLogin(),
                null,
                user.getLastName(),
                user.getFirstName(),
                user.getMiddleName(),
                new SimpleDateFormat("dd-MM-yyyy HH:mm").format(user.getBirthDate()),
                user.getPhone(),
                user.getEmail());
    }

    public User userDtoToUser(UserDto userDto) {
        Timestamp birthDate;
        try {
            birthDate = new Timestamp(new SimpleDateFormat("dd-MM-yyyy").parse(userDto.getBirthDate()).getTime());
        } catch (ParseException e) {
            birthDate = new Timestamp(new Date().getTime());
        }
        return new User(
                userDto.getLogin(),
                DigestUtils.sha256Hex(userDto.getPassword()),
                userDto.getLastName(),
                userDto.getFirstName(),
                userDto.getMiddleName(),
                birthDate,
                userDto.getPhone(),
                userDto.getEmail(),
                true
        );
    }
}
