package com.example.punyawork;

public class Contacts {
    private int orderId;
    private int sync_status;
    private String Category_Name;
    private String Order_Date;

    public int getOrderId() {
        return orderId;
    }

    public String getOrder_Date() {
        return Order_Date;
    }

    public void setOrder_Date(String order_Date) {
        Order_Date = order_Date;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSync_status() {
        return sync_status;
    }

    public void setSync_status(int sync_status) {
        this.sync_status = sync_status;
    }

    Contacts(int orderId, int Sync_status,String Category_Name,String Order_date){
        this.setOrderId(orderId);
        this.setSync_status(Sync_status);
        this.setCategory_Name(Category_Name);
        this.setOrder_Date(Order_date);
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String category_Name) {
        Category_Name = category_Name;
    }
}
