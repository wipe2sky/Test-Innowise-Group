package com.kurtsevich.innowise.util.generator;

public class IdGenerator {
    private static IdGenerator instance;

    private  Integer userId = 1;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        if(instance == null) {
                instance = new IdGenerator();
        }
        return instance;
    }

    public Integer generateUserId() {
        return userId++;
    }


}
