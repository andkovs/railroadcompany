package com.railroad.core.mapper;

import com.railroad.model.dto.UserDto;
import com.railroad.model.entity.User;
import com.railroad.rest.MasterController;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserMapper {

    private static final Logger logger = Logger.getLogger(MasterController.class);

    /**
     * Converts user to userDto.
     *
     * @param user Convertible user.
     * @return userDto.
     */
    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }
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

    /**
     * Converts user dto to user.
     *
     * @param userDto Convertible user dto.
     * @return user.
     */
    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        Timestamp birthDate;
        try {
            birthDate = new Timestamp(new SimpleDateFormat("dd-MM-yyyy").parse(userDto.getBirthDate()).getTime());
            birthDate.setTime(birthDate.getTime()+(1800*60*100));
        } catch (ParseException e) {
            birthDate = new Timestamp(new Date().getTime());
            birthDate.setTime(birthDate.getTime()+(1800*60*100));
            logger.error("invalid date format in userDtoToUser()!", e);
        }

        String password = null;
        if (userDto.getPassword() != null) {
            password = DigestUtils.sha256Hex(userDto.getPassword());
            Long id = null;
        }
        Long id = null;
        if (userDto.getUserId() != null) {
            id = userDto.getUserId();
        }
        return new User(
                id,
                userDto.getLogin(),
                password,
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
