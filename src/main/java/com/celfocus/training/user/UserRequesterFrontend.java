package com.celfocus.training.user;

import com.celfocus.training.Saver;
import com.celfocus.training.controllers.UserController;
import com.celfocus.training.models.ItemInfoModel;
import com.celfocus.training.models.ShoppingCartModel;
import com.celfocus.training.models.UserModel;
import com.celfocus.training.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, UserModel user) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>User</h1>"
             + "<span>" + user.getUserName() + "</span>"
             + "<span>" + user.getBirthdayDate() + "</span>"
             + "<span>" + user.isAdult() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.getUserName() + "</name>"
                    + "<bd>" + user.getBirthdayDate() + "</bd>"
                    + "<older> " + user.isAdult() + "</older>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     */
    public String returnFrontendShoppingCart(String type, ShoppingCartModel shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>ShoppingCart</h1>"
             + "<span> " + shoppingCart.getUser() + "</span>"
             + "<span> " + shoppingCart.getUser() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.getUser() + "</user>"
                    + "<itens> " + shoppingCart.getItems() + "</itens>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String returnFrontendItem(String type, ItemInfoModel item) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>Item</h1>"
             + "<span> " + item.getName() + "</span>"
             + "<span> " + item.getValue() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.getName() + "</name>"
                    + "<valor> " + item.getValue() + "</valor>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Cria ou atualiza usuario
     * @param name
     * @param birthdayDate
     * @param isAdult
     */
    public void createOrUpdateUser(String name, String birthdayDate, String isAdult) {
        Saver saver = new Saver();

        name = name.toUpperCase();

        Date date = Utils.toDate(birthdayDate, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - date.getYear() < 65) {
            isAdult = "false";
        }

        saver.createOrUpdateUser(name, Utils.toDate(birthdayDate, new SimpleDateFormat("dd/mm/yyyy")), isAdult.equals("true"));
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String name) {
        UserController userController = new UserController();
        userController.deleteUser(name);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void addItemToUserShoppingCart(String userName, String itemName, int quantity) {
        Saver saver = new Saver();

        itemName = itemName.toLowerCase().concat("_item");

        saver.addItemToUser(userName, itemName, quantity);
    }

}