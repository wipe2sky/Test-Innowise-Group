package com.kurtsevich.innowise.api.service;

import com.kurtsevich.innowise.model.ERole;
import com.kurtsevich.innowise.model.Role;
import com.kurtsevich.innowise.model.User;

import java.util.List;

public interface IUserService {
    User add(String firsName, String lastName, String email, Role roles, List<String> phoneNumber);

    User getById(Integer id);

    List<User> getAll();

    void deleteUser(Integer id);

    User changeUserFirstName(Integer id, String firsName);

    User changeUserLastName(Integer id, String lastName);

    User changeUserEmail(Integer id, String email);

    User addRole(Integer id, ERole role);

    User removeRole(Integer id, ERole role);

    User removeAllRole(Integer id);

    User addPhoneNumber(Integer id, String phoneNumber);

    User removePhoneNumber(Integer id, String phoneNumber);

    User removeAllPhoneNumber(Integer id);
}
