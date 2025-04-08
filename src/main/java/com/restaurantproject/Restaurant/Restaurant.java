package com.restaurantproject.Restaurant;

import java.util.LinkedList;
import java.util.Queue;

public class Restaurant {

    private Queue<OrderItem> orderQueue = new LinkedList<OrderItem>();

    public Restaurant(OrderItem orderItem) {
        orderQueue.add(orderItem);
        System.out.println("Restaurant receive the order: " + orderItem.getFoodName());
    }

    public void processOrder() {
        while (!orderQueue.isEmpty()) {
            OrderItem orderItem = orderQueue.poll();

            Waiter waiter = new Waiter(orderItem);
            waiter.start();
        }
    }
}
