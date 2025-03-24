package com.restaurantproject.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Waiter extends Thread{

    private boolean isComplete = false;
    private OrderItem orderItem;
    private final Chef chef;
    private List<OrderItem> waiterCollection = new ArrayList<OrderItem>();

    public Waiter(List<OrderItem> waiterCollection, Chef chef) {
        this.waiterCollection = waiterCollection;
        this.chef = chef;
    }

    public Waiter() {
        this.waiterCollection = null;
        this.chef = null;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void run() {

        synchronized (chef) {
            // handle the order to chef to prepare food
            for (OrderItem orderItem1: waiterCollection) {
                System.out.println("Preparing the food");
                chef.prepareOrder(orderItem1);
            }
            chef.notify();
        }

        synchronized (this) {
            while (waiterCollection.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
