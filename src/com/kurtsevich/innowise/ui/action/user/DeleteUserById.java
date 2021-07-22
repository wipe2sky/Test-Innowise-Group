package com.kurtsevich.innowise.ui.action.user;

import com.kurtsevich.innowise.exception.DaoException;
import com.kurtsevich.innowise.ui.action.AbstractAction;
import com.kurtsevich.innowise.ui.action.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteUserById extends AbstractAction implements IAction {
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write users id");
            Integer userId = Integer.parseInt(reader.readLine());
           facade.deleteUserById(userId);
        } catch (DaoException | NumberFormatException | IOException e) {
            System.out.println("User deletion is not complete");
        }
    }
}
