package com.celfocus.training.models;

public class ShoppingCartItemModel {

    private ItemInfoModel item;
    private int quantity;
    private double discount;

    public ItemInfoModel getItem() {
        return item;
    }

    public void setItem(ItemInfoModel item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


}
