package com.restaurantproject.Apps;

import com.restaurantproject.Restaurant.OrderItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class App extends JFrame implements ActionListener {

    private int HEIGHT = 700;
    private int WIDTH = 500;
    private String food;
    private Double price;
    private int quantity = 0;

    private JPanel foodPanel;
    private JLabel numberLabel, logoLabel;
    private JButton start, button1, button2;
    private JScrollPane scrollPane;

    private final List<OrderItem> orderItemList;
    private OrderItem orderItem;

    public App(){
        orderItemList = new ArrayList<OrderItem>();
        this.initialising();
    }

    private void initialising(){
        this.setTitle("Kiosk Ordering System");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        startPanel();

        this.setVisible(true);
    }

    private JPanel startPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(50, 450, 400, 200);
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setBackground(new Color(0,0,0,0));

        ImageIcon image = new ImageIcon("main\\resources\\kfc.png");
        logoLabel = new JLabel(image);

        start = new JButton("PRESS TO ORDER  >>");
        start.setBorder(BorderFactory.createLineBorder(Color.RED));
        start.setFont(new Font("Times New Roman", Font.BOLD, 14));
        start.setForeground(Color.WHITE);
        start.setBackground(Color.RED);
        start.setFocusable(false);
        start.setBounds(100, 50, 200, 40);
        start.addActionListener(this);

        panel.add(logoLabel);
        panel.add(start);
        this.add(panel);

        return panel;
    }

    private JPanel FoodMenu() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(WIDTH, HEIGHT);

        JPanel middlePanel = new JPanel();

        // Settings the header panel
        JPanel headerPanel = headPanel("FOOD MENU");

        int numItems = 0;
        for (int i = 0; i < 10; i++) {
            numItems = i; //getting the total number of panel
            foodPanel = FoodPanel(null, "Double Cheese Burger" + numItems, 3.50);
            middlePanel.add(foodPanel);
        }

        //re-calculate the size when the number of panel increase
        int rows = (int) Math.ceil(numItems / 4.0);
        int panelHeight = rows * 235;

        middlePanel.setBackground(Color.WHITE);
        middlePanel.setPreferredSize(new Dimension(WIDTH - 25, panelHeight));

        scrollPane = new JScrollPane(middlePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel bottomPanel = bottomPanel("Cancel", "Confirm");

        // add all the panel to the main panel then return it
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel headPanel(String headerName) {
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel(headerName);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(11, 0, 0, 0));
        headerLabel.setFont(new Font("New Time Roman", Font.BOLD, 18));
        headerPanel.setPreferredSize(new Dimension(500, 60));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(headerLabel);
        return headerPanel;
    }

    private JPanel bottomPanel(String button1Name, String button2Name) {
        JPanel bottomPanel = new JPanel(new FlowLayout());

        //setting the button and customize it with the size and color
        button1 = new JButton(button1Name);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button1.setFont(new Font("New Time Roman",~Font.BOLD,14));
        button1.setForeground(Color.BLACK);
        button1.setBackground(Color.WHITE);
        button1.setPreferredSize(new Dimension(220,40));
        button1.setFocusable(false);
        button1.addActionListener(this);

        button2 = new JButton(button2Name);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button2.setFont(new Font("New Time Roman",~Font.BOLD,14));
        button2.setForeground(Color.BLACK);
        button2.setBackground(Color.WHITE);
        button2.setPreferredSize(new Dimension(220,40));
        button2.setFocusable(false);
        button2.addActionListener(this);

        bottomPanel.setBackground(Color.lightGray);
        bottomPanel.setPreferredSize(new Dimension(500, 100));
        bottomPanel.add(button1);
        bottomPanel.add(button2);

        return bottomPanel;
    }

    private JPanel FoodPanel(ImageIcon image, String name, Double prices) {
        JPanel foodPanel = new JPanel();
        foodPanel.setLayout(new BoxLayout(foodPanel,BoxLayout.Y_AXIS));
        foodPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JPanel imagePanel = new JPanel((LayoutManager) image);
        imagePanel.setBackground(Color.gray);
        imagePanel.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT - 580));

        JLabel foodName = new JLabel(name);
        foodName.setFont(new Font("New Time Roman",Font.BOLD,8));
        foodPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel foodPrice = new JLabel("EUR " + String.valueOf(prices));
        foodPrice.setFont(new Font("New Time Roman",Font.BOLD,8));
        foodPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        foodPanel.add(imagePanel,BorderLayout.NORTH);
        foodPanel.add(foodName, BorderLayout.CENTER);
        foodPanel.add(foodPrice, BorderLayout.SOUTH);

        //store the food name and price in the panel
        foodPanel.putClientProperty("name", name);
        foodPanel.putClientProperty("prices", prices);
        foodPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel clickedPanel = (JPanel) e.getSource();
                food = (String) clickedPanel.getClientProperty("name");
                price = (Double) clickedPanel.getClientProperty("prices");
                orderItem = new OrderItem(food,price,quantity);
                System.out.println(orderItem.toString());
                swapping(orderPanel());
//                System.out.println(orderItemList.size());z
            }
        });

        return foodPanel;
    }

    private JPanel orderPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setLayout(new BoxLayout(orderPanel,BoxLayout.Y_AXIS));
        orderPanel.setSize(WIDTH, HEIGHT);

        JPanel headerPanels = headPanel("Order Items");
        headerPanels.setMaximumSize(new Dimension(WIDTH, 60));

        JPanel midPanel = new JPanel();
        midPanel.setPreferredSize(new Dimension(WIDTH - 25, 0));
        if (orderItem == null) {
            JLabel label = new JLabel("The list is empty at the moment, please go back and re-order!");
            label.setBorder(BorderFactory.createEmptyBorder(80, 0,0,0));
            label.setFont(new Font("New Time Roman",Font.BOLD,16));
            label.setForeground(Color.LIGHT_GRAY);
            midPanel.add(label);
        } else {
            //display every item on the list
            orderItem = new OrderItem(food,price,quantity);
            JPanel foodOrderPanel = new UnderlinedPanel();
            foodOrderPanel.setBorder(BorderFactory.createEmptyBorder(0, 0,5,0));
            foodOrderPanel.add(getFoodPanel(null,orderItem.getFoodName(),orderItem.getFoodPrice()));
            midPanel.add(foodOrderPanel);
        }

//        int rows = (int) Math.ceil(numberOfPanel / 1.25);
//        int panelHeight = rows * 100;


        JPanel addAndMinusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("+");
        addButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        addButton.setBackground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(25,23));
        addButton.setFocusable(false);
        addButton.addActionListener(this);

        numberLabel = new JLabel(String.valueOf(quantity));
        numberLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        numberLabel.setBackground(Color.WHITE);
        numberLabel.setPreferredSize(new Dimension(35,25));

        JButton minusButton = new JButton("-");
        minusButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        minusButton.setBackground(Color.WHITE);
        minusButton.setPreferredSize(new Dimension(25,23));
        minusButton.setFocusable(false);
        minusButton.addActionListener(this);

        addAndMinusPanel.add(addButton);
        addAndMinusPanel.add(numberLabel);
        addAndMinusPanel.add(minusButton);
        midPanel.add(addAndMinusPanel);

        JPanel bottomPanels = bottomPanel("Back","Add To Cart");
        bottomPanels.setMaximumSize(new Dimension(WIDTH, 100));

        orderPanel.add(headerPanels, BorderLayout.NORTH);
        orderPanel.add(midPanel, BorderLayout.CENTER);
        orderPanel.add(bottomPanels, BorderLayout.SOUTH);

        return orderPanel;
    }

    private JPanel getFoodPanel(Image foodImages, String foodName, Double price) {
        JPanel foodOrderPanel = new JPanel(new BorderLayout());
        foodOrderPanel.setBorder(BorderFactory.createEmptyBorder(0, 20,0,0));
        foodOrderPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - 650));

        JPanel foodWestImage = new JPanel((LayoutManager) foodImages);
        if (foodImages == null) {
            // if the image is == to null, then set the background color to null
            foodWestImage.setBackground(Color.GRAY);
        }
        foodWestImage.setPreferredSize(new Dimension(50,100));

        // setting the label for the food name
        JLabel foodNameLabel = new JLabel(foodName);
        foodNameLabel.setBorder(BorderFactory.createEmptyBorder(2, 10,0,0));
        foodNameLabel.setFont(new Font("New Time Roman",Font.BOLD,14));

        // setting the label for the food price
        JLabel foodPriceLabel = new JLabel("EUR " + price);
        foodPriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10,0,0));
        foodPriceLabel.setFont(new Font("New Time Roman",~Font.BOLD,11));

        // set the layout of the border and add the label to the panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
        infoPanel.add(foodNameLabel);
        infoPanel.add(foodPriceLabel);

        // add the image and information label to the food order panel with the location
        foodOrderPanel.add(foodWestImage, BorderLayout.WEST);
        foodOrderPanel.add(infoPanel, BorderLayout.CENTER);

        return foodOrderPanel;
    }

    public void swapping(JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start){
            swapping(FoodMenu());
            System.out.println("Switched to Food Menu!");
        } else if (e.getActionCommand().equals("Cancel")) {
            swapping(startPanel());
        } else if(e.getActionCommand().equals("Confirm")) {
//            swapping(orderPanel());
//            System.out.println("Switched to Order List");
        } else if (e.getActionCommand().equals("Back")) {
            swapping(FoodMenu());
            System.out.println("Switched back to Food Menu!");
        } else if (e.getActionCommand().equals("Add To Cart")) {
            swapping(FoodMenu());
            orderItem = new OrderItem(food,price,quantity);
            orderItemList.add(orderItem);

            //after add to the cart set the quantity back to 0
            quantity = 0;
            System.out.println("The food is been added: " + orderItemList.toString());
        } else if (e.getActionCommand().equals("+")) {
            quantity++;
            numberLabel.setText(String.valueOf(quantity));
        } else if (e.getActionCommand().equals("-")) {
            if(quantity <= 0) {
                quantity = 0;
                numberLabel.setText(String.valueOf(quantity));
            } else {
                quantity--;
                numberLabel.setText(String.valueOf(quantity));
            }
        }
    }

    public static void main(String[] args) {
        new App();
    }
}

class UnderlinedPanel extends JPanel {
    /**
     * Drawing an underline
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        int y = getHeight() - 1;
        g.drawLine(0, y, getWidth(), y);
    }
}