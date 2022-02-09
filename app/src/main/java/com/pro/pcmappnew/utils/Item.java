package com.pro.pcmappnew.utils;

public class Item {
    private String itemId;

    public String getItemCategory() {
        return itemCategory;
    }

    private String itemCategory;
    private String itemName;

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    private String itemPrice;
    private String itemDesc;
    private String itemImage;

    public Item(){
    }

    public String getItemId(){
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
