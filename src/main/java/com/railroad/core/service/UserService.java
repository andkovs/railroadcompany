package com.railroad.core.service;

import com.railroad.core.mapper.UserMapper;
import com.railroad.model.dao.UserDao;
import com.railroad.model.dto.StationDto;
import com.railroad.model.dto.UserDto;
import com.railroad.model.entity.Direction;
import com.railroad.model.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        if (userDao.getUserByLogin(login) == null) {
            Date d = new Date();
            return new UserDto(0L, "", "", "", "", "", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(d), "", "");
        }
        //get userDto by id
        return userMapper.userToUserDto(userDao.getUserByLogin(login));
    }

    /**
     * Saves or updates user. If user doe's not exist,
     * then save, else update.
     *
     * @param userDto saved/updated user DTO.
     */
    public void saveOrUpdateUser(UserDto userDto) {
        if (userDto.getUserId() == null || userDao.getUserById(userDto.getUserId()) == null) {
            //save user and get new id
            userDao.saveUser(userMapper.userDtoToUser(userDto));
            userDao.saveUserRole(new UserRole(userDto.getLogin(), 2L));
        } else {
            //update user
            userDao.updateUser(userMapper.userDtoToUser(userDto));
        }
    }
}
