package com.kurtsevich.innowise.ui.action.user;

import com.kurtsevich.innowise.exception.DaoException;
import com.kurtsevich.innowise.model.User;
import com.kurtsevich.innowise.ui.action.AbstractAction;
import com.kurtsevich.innowise.ui.action.IAction;
import com.kurtsevich.innowise.util.validator.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddUserPhoneNumber extends AbstractAction implements IAction {
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write users id");
            Integer userId = Integer.parseInt(reader.readLine());
            User user = facade.getUserById(userId);
            System.out.println(user);
            System.out.println("Write phone number");
            String phoneNumber = reader.readLine();
            if(Validator.validatePhone(user.getPhoneNumbers(), phoneNumber)) {
                System.out.println(facade.addUserPhoneNumber(userId, phoneNumber));
            }else {
                System.out.println("Add phone number failed");
            }
        } catch (DaoException | NumberFormatException | IOException e) {
            System.out.println("Get user info failed");
        }
    }
}
