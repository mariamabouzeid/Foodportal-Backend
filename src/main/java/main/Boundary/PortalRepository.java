package main.Boundary;

import main.Entity.*;
import main.REST.models.OrderModel;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateful
public class PortalRepository {

    @PersistenceContext
    private EntityManager em;

    public void AddNewUser(User user){
        user.setRole("admin");
        em.persist(user);

    }

    public Long getUserId(String username){
        User u = em.createQuery("select u from User u where u.userName = :um", User.class)
                .setParameter("um", username).getSingleResult();
        System.out.println(u.getId());
        return u.getId();
    }

    public void AddComplaint(Complaint complaint){

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
