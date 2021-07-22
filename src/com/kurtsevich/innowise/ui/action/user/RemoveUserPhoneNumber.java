package com.kurtsevich.innowise.ui.action.user;

import com.kurtsevich.innowise.exception.DaoException;
import com.kurtsevich.innowise.exception.ServiceException;
import com.kurtsevich.innowise.model.User;
import com.kurtsevich.innowise.ui.action.AbstractAction;
import com.kurtsevich.innowise.ui.action.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveUserPhoneNumber extends AbstractAction implements IAction {
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write users id");
            Integer userId = Integer.parseInt(reader.readLine());
            User user = facade.getUserById(userId);
            System.out.println(user);
            System.out.println("Write user phone number");
            String phoneNumber = reader.readLine();
            System.out.println(facade.removeUserPhoneNumber(userId, phoneNumber));
        } catch (DaoException | NumberFormatException | IOException e) {
            System.out.println("Get user info failed");
        } catch (ServiceException e) {
            System.out.println("Remove user phone number failed");
        }
    }
}
