package com.kurtsevich.innowise.ui.action.user;

import com.kurtsevich.innowise.exception.DaoException;
import com.kurtsevich.innowise.exception.ServiceException;
import com.kurtsevich.innowise.model.ERole;
import com.kurtsevich.innowise.ui.action.AbstractAction;
import com.kurtsevich.innowise.ui.action.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveUserRole extends AbstractAction implements IAction {
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write users id");
            Integer userId = Integer.parseInt(reader.readLine());
            System.out.println(facade.getUserById(userId));
            removeRole(reader, userId);
        } catch (DaoException | NumberFormatException | IOException e) {
            System.out.println("Get user info failed");
        } catch (ServiceException e){
            System.out.println("Remove user role failed");

        }
    }

    private void removeRole(BufferedReader reader, Integer userId) throws IOException{
        try {
            System.out.println("Write user role");
            String roleString = reader.readLine();
            if (!roleString.equals("cancel")) {
                ERole role = ERole.valueOf(roleString);
                System.out.println(facade.removeUserRole(userId, role));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role name. Try again or write \"cancel\"");
            removeRole(reader, userId);
        }
    }
}
