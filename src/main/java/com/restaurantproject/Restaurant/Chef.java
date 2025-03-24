package com.restaurantproject.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Chef extends Thread{

    private List<OrderItem> orderQueue = new ArrayList<>();
    private Waiter waiter = new Waiter();

    public void prepareOrder(OrderItem orderItem) {
        orderQueue.add(orderItem);
        System.out.println("DEBUG: " + orderQueue.toString());
    }

    public void run() {
        synchronized (this) {
            while (orderQueue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // preparing the order
        try {
            Thread.sleep(2000); // Simulating preparation time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (waiter) {
            //notify the waiter that the order is ready
            waiter.notify();
            waiter.setIsComplete(true);
            System.out.println("The order is ready");
        }
    }
}
