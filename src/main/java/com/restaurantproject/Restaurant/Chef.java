package com.restaurantproject.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Chef extends Thread{

    private OrderItem orderItem;
    private final Waiter waiter;

    public Chef(Waiter waiter) {
        this.waiter = waiter;
    }

    public void isReceivedOrder(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public void run() {
        synchronized (this) {
            try {
                while (orderItem == null) {
                    Thread.sleep(100); // Wait for order to be received
                }

                System.out.println("Chef Preparing The Food " + orderItem.getFoodName());
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println("Ops....Something when wrong... ");
            }
            System.out.println("Chef Finish The Food " + orderItem.getFoodName());

            synchronized (waiter) {
                //notify the waiter that the food is ready
                waiter.notify();
            }

            if (orderItem != null) {
                waiter.setIsComplete(true);
            }
        }
    }
}
