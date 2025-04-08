package com.restaurantproject.Apps;

import com.restaurantproject.Restaurant.OrderItem;

import java.io.*;
import java.util.ArrayList;

public class Food_Insert {

    private ArrayList<OrderItem> orderItemsList;
    private String foodInfo = "food-info.csv";

    public ArrayList<OrderItem> getOrderItemsList() {
        orderItemsList = new ArrayList<>();
        BufferedReader br = null;
        String line = "";

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(foodInfo);
            br = new BufferedReader(new InputStreamReader(is));

            //Skipping the header
            br.readLine();

            //read everything on the list
            while((line = br.readLine()) != null) {

                String[] data = line.split(", ");

                String category = data[0];
                String imagePath = data[1];
                String foodName = data[2];
                double price = Double.parseDouble(data[3]);
                System.out.println("price: " + price);

                OrderItem orderItem = new OrderItem(category,imagePath,foodName,price);
                orderItemsList.add(orderItem);
            }
            br.close();
        } catch (IOException e) { //catch the error
            e.printStackTrace();
        }

        return orderItemsList;
    }


/** Testing purpose */
//    public static void main(String[] args) {
//        Food_Insert f = new Food_Insert();
//        ArrayList<OrderItem> orderItems = f.getOrderItemsList();
//        System.out.println(orderItems.toString());
//    }
}
