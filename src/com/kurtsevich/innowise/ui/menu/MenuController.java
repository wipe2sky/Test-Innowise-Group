package com.kurtsevich.innowise.ui.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuController {
    private static MenuController instance;
    private final Builder builder;
    private final Navigator navigator;

    private MenuController() {
        this.navigator = Navigator.getInstance();
        this.builder = Builder.getInstance();
    }

    public static MenuController getInstance() {
        if(instance == null) instance = new MenuController();
        return instance;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            builder.buildMenu();
            navigator.setCurrentMenu(builder.getRootMenu());
            navigator.printMenu();
            int index = Integer.parseInt(reader.readLine());

            while (index !=0) {
                navigator.navigate(index);
                navigator.printMenu();
                index = Integer.parseInt(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
