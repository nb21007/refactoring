package com.celfocus.training.controllers;

import com.celfocus.training.models.ShoppingCartItemModel;
import com.celfocus.training.models.ShoppingCartModel;
import com.celfocus.training.models.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserController {

    private static final List<UserModel> users = new ArrayList<>();


    private UserModel getUser(String name) {
        UserModel userFound = null;
        for (UserModel user : users) {
            if (user.getUserName().equals(name)) {
                userFound = user;
                break;
            }
        }
        return userFound;
    }

    private UserModel createUser(String name, Date birthdayDate, boolean isAdult) {

        UserModel user = new UserModel();
        setUserDetails(name, birthdayDate, isAdult, user);
        users.add(user);

        ShoppingCartModel shoppingCart = new ShoppingCartModel();
        List<ShoppingCartItemModel> items = shoppingCart.getItems();
        shoppingCart.setUser(user);
        items = new ArrayList<>();
        shoppingCarts.add(shoppingCart);

        return user;
    }

    private UserModel updateUser(String name, Date birthdayDate, boolean isAdult) {

        UserModel user = getUser(name);
        setUserDetails(name, birthdayDate, isAdult, user);
        users.add(user);

        boolean hasShoppingCart = getUserShoppingCart(user) != null;

        if (!hasShoppingCart){
            ShoppingCartModel shoppingCart = new ShoppingCartModel();
            shoppingCart.setUser(user);
            shoppingCarts.add(shoppingCart);
        }

        return user;
    }

    public void deleteUser(String name) {

        UserModel userFound = getUser(name);
        if (userFound != null) {
            users.remove(userFound);
        }
    }

    public boolean userExists(String name) {
        UserModel userFound = getUser(name);
        return userFound != null;
    }

    private int getUserAge(UserModel user) {
        return new Date().getYear() - user.birthdayDate.getYear();
    }

    private void setUserDetails(String name, Date birthdayDate, boolean isAdult, UserModel user) {
        user.setBirthdayDate(birthdayDate);
        user.setUserName(name);
        user.setAdult(isAdult);
    }

}
