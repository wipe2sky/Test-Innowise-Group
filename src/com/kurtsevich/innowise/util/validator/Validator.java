package com.kurtsevich.innowise.util.validator;

import com.kurtsevich.innowise.model.ERole;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kurtsevich.innowise.model.ERole.ADMIN;
import static com.kurtsevich.innowise.model.ERole.CUSTOMER;
import static com.kurtsevich.innowise.model.ERole.PROVIDER;
import static com.kurtsevich.innowise.model.ERole.SUPER_ADMIN;
import static com.kurtsevich.innowise.model.ERole.USER;

public class Validator {
    private static final Map<ERole, Integer> eRoleMap =
            Map.of(
                    USER, 0,
                    CUSTOMER, 0,
                    ADMIN, 1,
                    PROVIDER, 1,
                    SUPER_ADMIN, 2
            );

    private Validator() {
    }

    public static boolean validatePhone(List<String> phoneNumbers, String phoneNumber) {
        if (phoneNumbers.size() > 2) {
            return false;
        }
        Pattern pattern = Pattern.compile("^375[ ]?\\d{2}[ ]?\\d{3}[ ]?\\d{2}[ ]?\\d{2}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^\\S+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateRole(List<ERole> role, String addRole) {
        try {
            ERole eRole = ERole.valueOf(addRole);

            if (role.contains(SUPER_ADMIN)) {
                System.out.println("User have role SUPER_ADMIN. Impossible add another roles!");
                return false;
            } else if (role.contains(eRole)) {
                System.out.println("User already have role " + eRole);
                return false;
            } else {
                for (ERole tempRole :
                        role) {
                    if (eRoleMap.get(tempRole).equals(eRoleMap.get(eRole))
                            || (!role.isEmpty() && eRole == SUPER_ADMIN)) {
                        return false;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
