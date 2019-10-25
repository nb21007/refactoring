
package com.celfocus.training;

import com.celfocus.training.controllers.UserController;
import com.celfocus.training.models.ItemInfoModel;
import com.celfocus.training.models.ShoppingCartItemModel;
import com.celfocus.training.models.ShoppingCartModel;
import com.celfocus.training.models.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItemModel e ItemInfo
 */
public class Saver {

    private static final List<ItemInfoModel> items = new ArrayList<>();

    public UserModel createOrUpdateUser(String name, Date birthdayDate, boolean isAdult) {

        UserController userController = new UserController();
        UserModel user = userController.userExists(name) ? userController.updateUser(name, birthdayDate, isAdult) : userController.createUser(name, birthdayDate, isAdult);

        return user;

    }



    private ShoppingCartModel getUserShoppingCart(UserModel user) {

        ShoppingCartModel userShoppingCart = null;

        for (ShoppingCartModel shoppingCart : shoppingCarts) {
            if (shoppingCart.getUser() == user) {
                userShoppingCart = shoppingCart;
                break;
            }
        }
        return userShoppingCart;
    }




    public void addItemToUser(String name, String itemName, int quantity) {

        UserModel user = getUser(name);
        
        if (user != null) {
            ShoppingCartModel ShoppingCart = getUserShoppingCart(user);

            if (ShoppingCart != null) {
                ShoppingCartItemModel shoppingCartItemModel = getShoppingCartItem(itemName, ShoppingCart);
                updateShoppingCart(itemName, quantity, user, shoppingCartItemModel);
            }
        }
    }

    private void updateShoppingCart(String itemName, int quantity, UserModel user, ShoppingCartItemModel shoppingCartItemModel) {

        if (shoppingCartItemModel != null) {
            updateShoppingCartItemQuantity(quantity, shoppingCartItemModel);
        } else {
            updateShoppingCartItem(itemName, quantity, user);
        }
    }

    private void updateShoppingCartItem(String itemName, int quantity, UserModel user) {
        ItemInfoModel itemInfo = getItemInfo(itemName);

        if (itemInfo != null) {
            ShoppingCartItemModel shoppingCartItemModel = new ShoppingCartItemModel();
            shoppingCartItemModel.setItem(itemInfo;
            shoppingCartItemModel.setQuantity(quantity);

            validateUserDiscount(user, shoppingCartItemModel);
        }
    }

    private ItemInfoModel getItemInfo(String itemName) {
        ItemInfoModel itemInfo = null;
        for (ItemInfoModel item : items) {
            if (item.getName().equals(itemName)) {
                itemInfo = item;
            }
        }
        return itemInfo;
    }

    private void validateUserDiscount(UserModel user, ShoppingCartItemModel shoppingCartItemModel) {
        if (user.isAdult()){
            shoppingCartItemModel.setDiscount(0.1);;
            if ((getUserAge(user) < 80)){
                shoppingCartItemModel.setDiscount(0.2);
            }
        }
    }

    private void updateShoppingCartItemQuantity(int quantity, ShoppingCartItemModel shoppingCartInfo) {
        int newQuantity = 0;
        newQuantity += quantity;
        shoppingCartInfo.setQuantity(newQuantity);;
    }

    private ShoppingCartItemModel getShoppingCartItem(String nameItem, ShoppingCartModel shoppingCart) {
        ShoppingCartItemModel shoppingCartItemModelData = null;
        for (ShoppingCartItemModel shoppingCartItemModel : shoppingCart.getItems()) {
            String shoppingCartItemName = shoppingCartItemModel.getItem().getName();
            if (shoppingCartItemModel.getItem().getName() == nameItem) {
                shoppingCartItemModelData = shoppingCartItemModel;
            }
        }
        return shoppingCartItemModelData;
    }
} 