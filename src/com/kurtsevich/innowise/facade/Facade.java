package com.kurtsevich.innowise.facade;

import com.kurtsevich.innowise.api.service.IUserService;
import com.kurtsevich.innowise.model.ERole;
import com.kurtsevich.innowise.model.Role;
import com.kurtsevich.innowise.model.User;
import com.kurtsevich.innowise.service.UserService;

import java.util.List;

public class Facade {
    private static Facade instance;
    private static final IUserService userService = UserService.getInstance();
    private Facade() {
    }
    public static Facade getInstance() {
        if (instance == null) instance = new Facade();
        return instance;
    }


    public User addUser(String firstName, String lastName, String email, Role roles, List<String> phoneNumbers) {
        return userService.add(firstName, lastName, email, roles, phoneNumbers);
    }

    public List<User> getAllUser() {
        return userService.getAll();
    }

    public User getUserById(Integer id) {
        return userService.getById(id);
    }

    public void deleteUserById(Integer userId) {
        userService.deleteUser(userId);
    }

    public User changeUserFirstName(Integer userId, String firstName) {
        return userService.changeUserFirstName(userId, firstName);
    }

    public User changeUserLastName(Integer userId, String lastName) {
        return userService.changeUserLastName(userId, lastName);

    }

    public User changeUserEmail(Integer userId, String email) {
        return userService.changeUserEmail(userId, email);
    }

    public User addUserRole(Integer userId, ERole role) {
        return userService.addRole(userId, role);
    }

    public User removeUserRole(Integer userId, ERole role) {
        return userService.removeRole(userId, role);
    }

    public User removeAllRole(Integer userId) {
        return userService.removeAllRole(userId);
    }

    public User addUserPhoneNumber(Integer userId, String phoneNumber) {
        return userService.addPhoneNumber(userId, phoneNumber);
    }

    public User removeUserPhoneNumber(Integer userId, String phoneNumber) {
        return userService.removePhoneNumber(userId, phoneNumber);
    }

    public User removeAllPhoneNumber(Integer userId) {
        return userService.removeAllPhoneNumber(userId);
    }
}
