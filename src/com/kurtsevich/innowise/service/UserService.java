package com.kurtsevich.innowise.service;

import com.kurtsevich.innowise.api.dao.IUserDao;
import com.kurtsevich.innowise.api.service.IUserService;
import com.kurtsevich.innowise.dao.UserDao;
import com.kurtsevich.innowise.exception.ServiceException;
import com.kurtsevich.innowise.model.ERole;
import com.kurtsevich.innowise.model.Role;
import com.kurtsevich.innowise.model.User;
import com.kurtsevich.innowise.util.generator.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    private static UserService instance;
    IUserDao userDao;

    private UserService() {
        this.userDao = UserDao.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public User add(String firsName, String lastName, String email, Role roles, List<String> phoneNumber) {
        User user = new User(firsName, lastName, email, roles, phoneNumber);
        user.setId(IdGenerator.getInstance().generateUserId());
        userDao.save(user);
        return user;
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public User changeUserFirstName(Integer id, String firsName) {
        User user = userDao.getById(id);
        user.setFirsName(firsName);
        userDao.update(user);
        return user;
    }

    @Override
    public User changeUserLastName(Integer id, String lastName) {
        User user = userDao.getById(id);
        user.setLastName(lastName);
        userDao.update(user);
        return user;
    }

    @Override
    public User changeUserEmail(Integer id, String email) {
        User user = userDao.getById(id);
        user.setEmail(email);
        userDao.update(user);
        return user;
    }

    @Override
    public User addRole(Integer id, ERole role) {
        User user = userDao.getById(id);
        Role roles = user.getRoles();
        roles.getName().add(role);
        userDao.update(user);
        return user;
    }

    @Override
    public User removeRole(Integer id, ERole role) {
        User user = userDao.getById(id);
        List<ERole> eRoles = user.getRoles().getName();
        if (eRoles.contains(role)) {
            eRoles.remove(role);
            userDao.update(user);
        } else {
            throw new ServiceException("User don't have this role");
        }
        return user;
    }

    @Override
    public User removeAllRole(Integer id) {
        User user = userDao.getById(id);
        user.setRoles(new Role());
        userDao.update(user);
        return user;
    }

    @Override
    public User addPhoneNumber(Integer id, String phoneNumber) {
        User user = userDao.getById(id);
        user.getPhoneNumbers().add(phoneNumber);
        userDao.update(user);
        return user;
    }

    @Override
    public User removePhoneNumber(Integer id, String phoneNumber) {
        User user = userDao.getById(id);
        List<String> phoneNumbers = user.getPhoneNumbers();
        if (phoneNumbers.contains(phoneNumber)) {
            phoneNumbers.remove(phoneNumber);
            userDao.update(user);
        } else {
            throw new ServiceException("User don't have this phone number");
        }
        return user;
    }

    @Override
    public User removeAllPhoneNumber(Integer id) {
        User user = userDao.getById(id);
        user.setPhoneNumbers(new ArrayList<>());
        userDao.update(user);
        return user;
    }
}
