package com.railroad.core.service;

import com.railroad.core.mapper.UserMapper;
import com.railroad.model.dao.UserDao;
import com.railroad.model.dto.UserDto;
import com.railroad.model.entity.User;
import com.railroad.model.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class UserService {

    private final
    UserMapper userMapper;

    private final
    UserDao userDao;

    @Autowired
    public UserService(UserMapper userMapper, UserDao userDao) {
        this.userMapper = userMapper;
        this.userDao = userDao;
    }

    /**
     * Gets user DTO by user login.
     *
     * @param login user login.
     * @return user DTO.
     */
    public UserDto getUserByLogin(String login) {
        //if User not exist, then return empty UserDto
        if (login == null || userDao.getUserByLogin(login) == null) {
            return getNewUser();
        }
        //get userDto by id
        UserDto userDto = userMapper.userToUserDto(userDao.getUserByLogin(login));
        if (userDto == null) {
            return getNewUser();
        }
        return userDto;
    }

    /**
     * Saves or updates user. If user doe's not exist,
     * then save, else update.
     *
     * @param userDto saved/updated user DTO.
     * @return update or add string.
     */
    public String saveOrUpdateUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User checkUser = userDao.getUserByLogin(userDto.getLogin());
        if (userDto.getUserId() == null || userDao.getUserById(userDto.getUserId()) == null) {
            //save user
            if (checkUser != null||userDto.getLogin() == null || userDto.getLogin().equals("")) {
                return null;
            }
            userDao.saveUser(userMapper.userDtoToUser(userDto));
            userDao.saveUserRole(new UserRole(userDto.getLogin(), 2L));
            return "added";
        } else {
            //update user
            userDao.updateUser(userMapper.userDtoToUser(userDto));
            return "updated";
        }
    }

    private UserDto getNewUser() {
        Date d = new Date();
        return new UserDto(0L, "", "", "", "", "", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(d), "", "");
    }
}
