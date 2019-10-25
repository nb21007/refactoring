package com.celfocus.training.models;

import java.util.List;

public class ShoppingCartModel {

    private UserModel user;
    private List<ShoppingCartItemModel> items;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<ShoppingCartItemModel> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItemModel> items) {
        this.items = items;
    }
}
