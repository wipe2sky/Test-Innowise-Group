package com.kurtsevich.innowise.ui.action.user;

import com.kurtsevich.innowise.exception.DaoException;
import com.kurtsevich.innowise.ui.action.AbstractAction;
import com.kurtsevich.innowise.ui.action.IAction;
import com.kurtsevich.innowise.util.validator.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangeUserEmail extends AbstractAction implements IAction {
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write users id");
            Integer userId = Integer.parseInt(reader.readLine());
            System.out.println(facade.getUserById(userId));
            System.out.println("Write new email");
            String email = reader.readLine();
            if(Validator.validateEmail(email)) {
                System.out.println(facade.changeUserEmail(userId, email));
            } else {
                System.out.println("Change email failed. Email is not valid.");
            }
        }catch (DaoException | NumberFormatException | IOException e) {
            System.out.println("Get user info failed");
        }
        }
    }
