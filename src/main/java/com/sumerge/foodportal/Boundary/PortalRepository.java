package com.sumerge.foodportal.Boundary;

import com.sumerge.foodportal.Entity.*;
import com.sumerge.foodportal.REST.Models.OrderModel;
import com.sumerge.foodportal.Entity.*;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class PortalRepository {

    @PersistenceContext
    private EntityManager em;

    public void addNewUser(User user){
        user.setRole("admin");
        em.persist(user);

    }

    public Long getUserId(String username){
        User u = em.createQuery("select u from User u where u.userName = :um", User.class)
                .setParameter("um", username).getSingleResult();
        System.out.println(u.getId());
        return u.getId();
    }

    public void addComplaint(Complaint complaint){

        em.persist(complaint);

    }

    public List<Item> viewAllItems(){

        return em.createQuery("select i from Item i").getResultList();

    }

    public void saveUserItems(OrderModel orderModel){

        Order order = new Order();
        order.setUserId(orderModel.getUserId());
        order = em.merge(order);
        em.flush();

        List<OrderItems> items = new ArrayList<>();

        for(int i = 0; i < orderModel.getOrderItems().size(); i++){

            OrderItemsId itemId = new OrderItemsId();
            itemId.setItemId(orderModel.getOrderItems().get(i).getItemId());
            itemId.setOrderId(order.getId());

            OrderItems temp = new OrderItems();
            temp.setSizeId(orderModel.getOrderItems().get(i).getSizeId());
            temp.setId(itemId);

            em.merge(temp);
        }

    }

}
