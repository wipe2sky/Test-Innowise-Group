package com.kurtsevich.innowise.ui.action.user;

import com.kurtsevich.innowise.exception.DaoException;
import com.kurtsevich.innowise.model.ERole;
import com.kurtsevich.innowise.model.User;
import com.kurtsevich.innowise.ui.action.AbstractAction;
import com.kurtsevich.innowise.ui.action.IAction;
import com.kurtsevich.innowise.util.validator.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddUserRole extends AbstractAction implements IAction {
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write users id");
            Integer userId = Integer.parseInt(reader.readLine());
            System.out.println(facade.getUserById(userId));
            addRole(reader, userId);
        } catch (DaoException | NumberFormatException | IOException e) {
            System.out.println("Get user info failed");
        }
    }

    private void addRole(BufferedReader reader, Integer userId) throws IOException {
        try {
            System.out.println("Write user role. Possible roles:");
            for (ERole value : ERole.values()) {
                System.out.print(value + " ");
            }
            System.out.println();
            String roleString = reader.readLine();
            if (!roleString.equals("cancel")) {
                User user = facade.getUserById(userId);
                if (Validator.validateRole(user.getRoles().getName(), roleString)) {
                    System.out.println(facade.addUserRole(userId, ERole.valueOf(roleString)));
                } else {
                    System.out.println("Add role failed");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role name. Try again or write \"cancel\"");
            addRole(reader, userId);
        }
    }
}
