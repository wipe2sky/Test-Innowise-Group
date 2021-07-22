package com.kurtsevich.innowise.dao;

import com.kurtsevich.innowise.api.dao.IUserDao;
import com.kurtsevich.innowise.exception.DaoException;
import com.kurtsevich.innowise.model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private static UserDao instance;
    private List<User> repository = new ArrayList<>();
    private static final String DB = "database.dat";

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    @Override
    public void save(User entity) {
        repository = getAll();
        repository.add(entity);
        saveInFile();
        System.out.println("Save User complete");
    }

    @Override
    public User getById(Integer id) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DB))) {
            repository = (List<User>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Unable to read from database");
        }
        for (User user :
                repository) {
            if (id.equals(user.getId()))
                return user;
        }
        throw new DaoException("Couldn't find User by id: " + id);
    }

    @Override
    public List<User> getAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DB))) {
            repository = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
        return repository;
    }

    @Override
    public void deleteById(Integer id) {
        User user = getById(id);
        repository.remove(user);
        saveInFile();
        System.out.println("User id " + id + " removed");
    }

    @Override
    public void update(User updatedUser) {
        try {
            User user = getById(updatedUser.getId());
            repository.remove(user);
            repository.add(updatedUser);
        } catch (DaoException e) {
            throw new DaoException("User update failed", e);
        }
        saveInFile();
        System.out.println("User id " + updatedUser.getId() + " updated");
    }

    private void saveInFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DB))) {
            oos.writeObject(repository);
        } catch (IOException e) {
            System.out.println("Unable to save to database");
        }
    }
}
