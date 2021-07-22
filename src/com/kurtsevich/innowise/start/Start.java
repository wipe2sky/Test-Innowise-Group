package com.kurtsevich.innowise.start;

import com.kurtsevich.innowise.model.ERole;
import com.kurtsevich.innowise.model.Role;
import com.kurtsevich.innowise.service.UserService;
import com.kurtsevich.innowise.ui.menu.MenuController;

import java.util.ArrayList;
import java.util.Collections;

public class Start {
    public static void main(String[] args) {
//        UserService userService = UserService.getInstance();
//        Role role =  new Role();
//        role.setName(Collections.singletonList(ERole.ADMIN));
//        userService.add("first", "last", "mail",role, new ArrayList<>(Collections.singletonList("375447503355")));
//        userService.add("first1", "last1", "mail1", role, new ArrayList<>(Collections.singletonList("375447504466")));

        MenuController.getInstance().run();
    }
}
