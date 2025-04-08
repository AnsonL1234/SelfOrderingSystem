package com.restaurantproject.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Waiter extends Thread{

    private boolean isComplete = false;
    private OrderItem orderItem;;
    Chef chef = null;

    public Waiter(OrderItem orderItem) {
        this.orderItem = orderItem;
        System.out.println("Waiter - Passes over the food " + orderItem.getFoodName());
        this.chef = new Chef(this);
        chef.start();
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void run() {

        synchronized (this) {
            try {
                if (chef.isAlive()) {
                    chef.isReceivedOrder(orderItem);
                    System.out.println("Chef has receive the order");
                }

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isComplete) {
                System.out.println("The order is ready to pick up!");
            }
        }
    }
}
