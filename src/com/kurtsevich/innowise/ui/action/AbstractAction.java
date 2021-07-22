package com.kurtsevich.innowise.ui.action;

import com.kurtsevich.innowise.facade.Facade;

public abstract class AbstractAction {
    protected Facade facade = Facade.getInstance();
}
