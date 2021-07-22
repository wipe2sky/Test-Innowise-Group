package com.kurtsevich.innowise.ui.menu;

import com.kurtsevich.innowise.ui.action.user.AddUser;
import com.kurtsevich.innowise.ui.action.user.AddUserPhoneNumber;
import com.kurtsevich.innowise.ui.action.user.AddUserRole;
import com.kurtsevich.innowise.ui.action.user.ChangeUserEmail;
import com.kurtsevich.innowise.ui.action.user.ChangeUserFirstName;
import com.kurtsevich.innowise.ui.action.user.ChangeUserLastName;
import com.kurtsevich.innowise.ui.action.user.DeleteUserById;
import com.kurtsevich.innowise.ui.action.user.GetAllUser;
import com.kurtsevich.innowise.ui.action.user.GetUserById;
import com.kurtsevich.innowise.ui.action.user.RemoveAllPhoneNumber;
import com.kurtsevich.innowise.ui.action.user.RemoveAllUserRole;
import com.kurtsevich.innowise.ui.action.user.RemoveUserPhoneNumber;
import com.kurtsevich.innowise.ui.action.user.RemoveUserRole;

public class Builder {
    private static final String EXIT = "Exit";
    private static final String COMEBACK = "Back";
    private static Builder instance;
    private Menu rootMenu;

    private Builder() {
    }

    public static Builder getInstance() {
        if (instance == null) instance = new Builder();
        return instance;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }


    public void buildMenu() {
        rootMenu = new Menu("Main menu");

        rootMenu.addMenuItems(new MenuItem(EXIT, () -> {
        }, null));
        rootMenu.addMenuItems(new MenuItem("Get all users", new GetAllUser(), rootMenu));
        rootMenu.addMenuItems(new MenuItem("Get user info by id", new GetUserById(), rootMenu));
        rootMenu.addMenuItems(new MenuItem("Create new user", new AddUser(), rootMenu));
        rootMenu.addMenuItems(new MenuItem("Update user", () -> {
        }, createUpdateUserMenu()));
        rootMenu.addMenuItems(new MenuItem("Delete User", new DeleteUserById(), rootMenu));

    }

    private Menu createUpdateUserMenu() {
        Menu updateUserMenu = new Menu("Update user menu");

        updateUserMenu.addMenuItems(new MenuItem(EXIT, () -> {
        }, null));
        updateUserMenu.addMenuItems(new MenuItem("Change user first name", new ChangeUserFirstName(), updateUserMenu));
        updateUserMenu.addMenuItems(new MenuItem("Change user last name", new ChangeUserLastName(), updateUserMenu));
        updateUserMenu.addMenuItems(new MenuItem("Change user email", new ChangeUserEmail(), updateUserMenu));
        updateUserMenu.addMenuItems(new MenuItem("Add role", new AddUserRole(), updateUserMenu));
        updateUserMenu.addMenuItems(new MenuItem("Remove role", new RemoveUserRole(), updateUserMenu));
        updateUserMenu.addMenuItems(new MenuItem("Remove all roles", new RemoveAllUserRole(), updateUserMenu));
        updateUserMenu.addMenuItems(new MenuItem("Add phone number", new AddUserPhoneNumber(), updateUserMenu));
        updateUserMenu.addMenuItems(new MenuItem("Remove phone number", new RemoveUserPhoneNumber(), updateUserMenu));
        updateUserMenu.addMenuItems(new MenuItem("Remove all phone numbers", new RemoveAllPhoneNumber(), updateUserMenu));
        updateUserMenu.addMenuItems(new MenuItem(COMEBACK, () -> {
        }, rootMenu));

        return updateUserMenu;
    }
}
