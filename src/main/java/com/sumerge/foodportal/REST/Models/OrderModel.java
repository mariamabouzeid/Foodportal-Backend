package com.sumerge.foodportal.REST.Models;

import java.util.List;

public class OrderModel {

    private Long userId;

    private List<OrderItemModel> orderItems;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemModel> orderItems) {
        this.orderItems = orderItems;
    }
}
