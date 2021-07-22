package com.kurtsevich.innowise.ui.action.user;

import com.kurtsevich.innowise.ui.action.AbstractAction;
import com.kurtsevich.innowise.ui.action.IAction;

public class GetAllUser extends AbstractAction implements IAction {
    @Override
    public void execute() {
        facade.getAllUser().forEach(System.out::println);
    }
}
