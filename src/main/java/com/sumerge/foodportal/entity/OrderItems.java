package com.sumerge.foodportal.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_ITEM_SIZE", schema = "FOODPORTALDB")
public class OrderItems implements Serializable {

    @EmbeddedId
    private OrderItemsId id;

    @Column(name = "SIZE_ID")
    private Long sizeId;


    public OrderItemsId getId() {
        return id;
    }

    public void setId(OrderItemsId id) {
        this.id = id;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long size_id) {
        this.sizeId = size_id;
    }
}
