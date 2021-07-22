package com.kurtsevich.innowise.ui.action.user;

import com.kurtsevich.innowise.exception.ServiceException;
import com.kurtsevich.innowise.model.ERole;
import com.kurtsevich.innowise.model.Role;
import com.kurtsevich.innowise.model.User;
import com.kurtsevich.innowise.ui.action.AbstractAction;
import com.kurtsevich.innowise.ui.action.IAction;
import com.kurtsevich.innowise.util.validator.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddUser extends AbstractAction implements IAction {
    private static final String ADD_CANCELED = "Add user canceled";
    private static final String CANCEL = "cancel";
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Write FirstName");
            String firstName = reader.readLine();
            System.out.println("Write LastName");
            String lastName = reader.readLine();
            String email = addEmail(reader);
            Role roles = new Role();
            roles.setName(addRoles(reader));
            List<String> phoneNumbers = addPhoneNumber(reader);
            User user = facade.addUser(firstName, lastName, email, roles, phoneNumbers);
            System.out.println(user);
        } catch (IOException e) {
            System.out.println("Add user failed");
        } catch (ServiceException ignore) {
            System.out.println(ADD_CANCELED);
        }
    }

    private String addEmail(BufferedReader reader) throws IOException, ServiceException {
        System.out.println("Write email");
        String email = reader.readLine();
        while (!Validator.validateEmail(email)) {
            System.out.println("Incorrect email. Tray again or write 'cancel'");
            email = reader.readLine();
            if (email.equals(CANCEL)) {
                throw new ServiceException(ADD_CANCELED);
            }
        }
        return email;
    }

    private List<String> addPhoneNumber(BufferedReader reader) throws IOException, ServiceException {
        System.out.println("Write phone numbers. If you want type several phone numbers use delimiter ,");
        String phoneNumbersString = reader.readLine();
        String[] phoneNumbersMassive = phoneNumbersString.split(",");
        List<String> phoneNumbers = new ArrayList<>();
        for (String s : phoneNumbersMassive) {
            if (s.equals(CANCEL)) {
                throw new ServiceException(ADD_CANCELED);
            }
            if (Validator.validatePhone(phoneNumbers ,s)) {
                phoneNumbers.add(s);
            } else {
                System.out.println("Can't add phone number. Try again or write 'cancel'");
                break;
            }
        }
        return phoneNumbers.size() == phoneNumbersMassive.length
                ? phoneNumbers
                : addPhoneNumber(reader);
    }

    private List<ERole> addRoles(BufferedReader reader) throws IOException {
        System.out.println("Write user role. If you want type several roles use delimiter ','. Possible roles:");
        for (ERole value : ERole.values()) {
            System.out.print(value + " ");
        }
        System.out.println();
        String roleString = reader.readLine();
        String[] roleMassive = roleString.split(",");
        List<ERole> roles = new ArrayList<>();

        for (String s : roleMassive) {
            if (s.equals(CANCEL)) {
                throw new ServiceException(ADD_CANCELED);
            }
            if (Validator.validateRole(roles, s)) {
                roles.add(ERole.valueOf(s));
            } else {
                System.out.println("Validation role failed. Try again or write 'cancel'");
                System.out.println("----------------------");
                break;
            }
        }
        return roles.size() == roleMassive.length
                ? roles
                : addRoles(reader);
    }
}

