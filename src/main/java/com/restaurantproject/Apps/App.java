package com.restaurantproject.Apps;

import com.restaurantproject.Restaurant.OrderItem;
import com.restaurantproject.Restaurant.Restaurant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class App extends JFrame implements ActionListener {

    private final int HEIGHT = 700;
    private final int WIDTH = 500;
    private String food;
    private String typeOption;
    private String category;
    private Double price;
    private int quantity = 1;

    private JLabel numberLabel,logoLabel, messageLabel, messageLabel2, viewOrder;
    private JButton start;

    private final List<OrderItem> orderCartList;
    private ArrayList<OrderItem> foodList;
    private OrderItem orderCart;
    private OrderItem orderItem;

    String[] categoryImage = {"transparent_Category.png","bucket_Category.png","burger_Category.png","chicken_Category.png","dessert_Category.png","drink_Category.png"};
    String[] categoryName = {"Recommended","Bucket","Burger","Chicken","Dessert","Drink",};

    public App(){
        orderCartList = new ArrayList<>();
        foodList = new ArrayList<>();
        this.initialising();

        Food_Insert food_insert = new Food_Insert();
        foodList = food_insert.getOrderItemsList();
    }

    private void initialising(){
        this.setTitle("Kiosk Ordering System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //setting the app image icon
        ImageIcon image = new ImageIcon("C:\\Users\\Anson\\OneDrive - Technological University Dublin\\Year 2\\Semester 2\\OOSD\\OOSD Assignment 3\\RestaurantProject2\\SelfOrderingSystem\\src\\main\\resources\\KFC_logo.svg.png");
        this.setIconImage(image.getImage());

        String URL = "C:\\Users\\anson\\OneDrive - Technological University Dublin\\Year 2\\Semester 2\\OOSD\\OOSD Assignment 3\\RestaurantProject\\src\\main\\resources\\images.png";
        BackgroundPanel backgroundPanel = new BackgroundPanel(URL);
        backgroundPanel.setLayout(null);
        this.setContentPane(backgroundPanel);

        startPanel();

        this.setVisible(true);
    }

    /**
     * Page 1 - Ask the user to start the order by pressing the button below
     */
    private JPanel startPanel() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(WIDTH, HEIGHT));
        panel.setBounds(50, 450, 400, 200);
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setBackground(new Color(0,0,0,0));

        Image image = getImage("KFC-Logo.wine.png",250,150);
        if (image != null) {
            Icon icon = new ImageIcon(image);
            logoLabel = new JLabel(icon);
            logoLabel.setHorizontalAlignment(JLabel.CENTER);
            logoLabel.setBounds(125, 50, 250, 150);

            this.add(logoLabel);
        }

        messageLabel = new JLabel("AND START ORDER!");
        messageLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBackground(Color.BLACK);
        messageLabel.setOpaque(true);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setBounds(145, 255, 303, 50);
        this.add(messageLabel);

        messageLabel2 = new JLabel("TAP THE BUTTON BELOW");
        messageLabel2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        messageLabel2.setForeground(Color.WHITE);
        messageLabel2.setBackground(Color.RED);
        messageLabel2.setOpaque(true);
        messageLabel2.setHorizontalAlignment(JLabel.CENTER);
        messageLabel2.setBounds(45, 210, 408, 50);
        this.add(messageLabel2);

        start = new JButton("PRESS TO ORDER  >>");
        start.setBorder(BorderFactory.createLineBorder(Color.RED));
        start.setFont(new Font("Times New Roman", Font.BOLD, 18));
        start.setForeground(Color.WHITE);
        start.setBackground(Color.RED);
        start.setFocusable(false);
        start.setBounds(80, 50, 230, 60);
        start.addActionListener(this);
        panel.add(start);

        this.add(panel);

        return panel;
    }

    /**
     * Page 2 - Ask for the user to select the option before moving on to the menu page
     */
    private JPanel OptionTypePanel(String filePath, String eatInType) {
        JPanel optionTypePanel = new JPanel();
        optionTypePanel.setPreferredSize(new Dimension(200,250));
        optionTypePanel.setBackground(new Color(242, 242, 242));

        Image eatInImage = getImage(filePath,150,150);
        ImageIcon imageIcon = new ImageIcon(eatInImage);

        JLabel eatInBackgroundImage = new JLabel(imageIcon);
        eatInBackgroundImage.setPreferredSize(new Dimension(150,150));
        eatInBackgroundImage.setBackground(Color.LIGHT_GRAY);
        eatInBackgroundImage.setOpaque(true);
        optionTypePanel.add(eatInBackgroundImage);

        JLabel eatInName = new JLabel(eatInType);
        eatInName.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
        eatInName.setFont(new Font("Times New Roman", Font.BOLD, 18));
        optionTypePanel.add(eatInName);

        return optionTypePanel;
    }

    private JPanel OrderType() {
        JPanel orderTypePanel = new JPanel();
        orderTypePanel.setBackground(Color.WHITE);
        orderTypePanel.setSize(WIDTH,HEIGHT);

        JPanel optionTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        optionTypePanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        optionTypePanel.setBackground(Color.WHITE);

        JLabel selectOptionLabel = new JLabel("PLEASE SELECT AN OPTION BEFORE ORDER");
        selectOptionLabel.setForeground(Color.LIGHT_GRAY);
        selectOptionLabel.setFont(new Font("Times New Roman", Font.BOLD,18));
        selectOptionLabel.setBorder(BorderFactory.createEmptyBorder(130,0,0,0));

        JPanel eatInType = OptionTypePanel("","EAT IN");
        eatInType.setBackground(new Color(217, 217, 217));
        eatInType.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        eatInType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                swapping(CategoryPanel());
                typeOption = "Eat In";
            }
        });

        JPanel takeAwayType = OptionTypePanel("","TAKE AWAY");
        takeAwayType.setBackground(new Color(217, 217, 217));
        takeAwayType.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        takeAwayType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                swapping(CategoryPanel());
                typeOption = "Take Away";
            }
        });

        optionTypePanel.add(eatInType);
        optionTypePanel.add(takeAwayType);
        orderTypePanel.add(selectOptionLabel);
        orderTypePanel.add(optionTypePanel);

        return orderTypePanel;
    }

    private JPanel CategoryPanel() {
        JPanel categoryPanel = new JPanel(new BorderLayout());
        categoryPanel.setSize(WIDTH,HEIGHT);

        JPanel categoryHeaderPanel = headPanel("Category");

        JPanel categoryMiddlePanel = new JPanel();
        int numItems = 0;
        for (int i = 0; i < categoryName.length; i++) {
            numItems = i; //getting the total number of panel
            String fileName = categoryImage[i]; // example: burger
            String resourcePath = "/Category-File/" + fileName;
            JPanel categoryFoodPanel = CategoryFoodPanel(resourcePath,categoryName[i]);
            categoryFoodPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            categoryFoodPanel.setBackground(Color.WHITE);
            categoryMiddlePanel.add(categoryFoodPanel);
        }

        //re-calculate the size when the number of panel increase
        int rows = (int) Math.ceil(numItems / 4.0);
        int panelHeight = rows * 235;

        categoryMiddlePanel.setBackground(Color.WHITE);
        categoryMiddlePanel.setPreferredSize(new Dimension(WIDTH - 25, panelHeight));

        JPanel categoryBottomPanel = bottomPanel("CANCEL", "CONFIRM");

        categoryPanel.add(categoryHeaderPanel, BorderLayout.NORTH);
        categoryPanel.add(categoryMiddlePanel, BorderLayout.CENTER);
        categoryPanel.add(categoryBottomPanel, BorderLayout.SOUTH);

        return categoryPanel;
    }

    private JPanel CategoryFoodPanel(String filePath, String name) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel,BoxLayout.Y_AXIS));
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);

        URL imageUrl = getClass().getResource(filePath);
        JLabel thumbnail = null;
        if (imageUrl == null) {
            System.out.println("Image not found: " + filePath);
        } else {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaledImage = icon.getImage().getScaledInstance(WIDTH / 4, HEIGHT - 580, Image.SCALE_SMOOTH);
            thumbnail = new JLabel(new ImageIcon(scaledImage));
            thumbnail.setAlignmentX(Component.CENTER_ALIGNMENT);
            thumbnail.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT - 580));
        }

        JLabel categoryLabelName = new JLabel(name);
        categoryLabelName.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoryLabelName.setFont(new Font("Times New Roman",Font.BOLD,12));
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0));

        container.add(thumbnail,BorderLayout.NORTH);
        container.add(categoryLabelName, BorderLayout.CENTER);
        categoryPanel.add(container);

        categoryPanel.putClientProperty("name", name);
        categoryPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel clickPanel = (JPanel) e.getSource();
                category = (String) clickPanel.getClientProperty("name");
                swapping(FoodMenu(category));
                System.out.println(category);
            }
        });

        return categoryPanel;
    }

    /**
     * Page 3 - Once the user select the option
     *        - It will move to menu page which allow user's to select the food with the quantity
     *        - Once user's has confirmed the order
     *        - The user's can either cancel the order or either continue to check the order list page
     */
    private JPanel FoodMenu(String category) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(WIDTH, HEIGHT);

        JPanel middlePanel = new JPanel();

        // Settings the header panel
        JPanel headerPanel = headPanel("FOOD MENU");

        int numItems = 0;
        for (OrderItem od: foodList) {
            if (category.equals(od.getCategory())) {
                numItems++; //getting the total number of panel
                JPanel foodPanel = FoodPanel(od.getImage(), od.getFoodName(), od.getFoodPrice());
                foodPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                foodPanel.setBackground(Color.WHITE);
                middlePanel.add(foodPanel);
            }
        }

        //re-calculate the size when the number of panel increase
        int rows = (int) Math.ceil(numItems / 4.0);
        int panelHeight = rows * 235;

        middlePanel.setBackground(Color.WHITE);
        middlePanel.setPreferredSize(new Dimension(WIDTH - 25, panelHeight));

        JScrollPane scrollPane = new JScrollPane(middlePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        JPanel bottomPanel = bottomPanel("GO BACK", "CONFIRM");

        // add all the panel to the main panel then return it
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel headPanel(String headerName) {
        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel logoLabel = new JLabel("KFC");
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0,55,0,80));
        logoLabel.setFont(new Font("Bernard MT Condensed",Font.BOLD,30));
        logoLabel.setForeground(new Color(255, 26, 26));

        JLabel headerLabel = new JLabel(headerName);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setPreferredSize(new Dimension(160,60));
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBackground(Color.RED);
        headerLabel.setOpaque(true);

        viewOrder = new JLabel("Total Items x " + orderCartList.size());
        viewOrder.setBorder(BorderFactory.createEmptyBorder(0,70,0,30));

        headerPanel.setPreferredSize(new Dimension(500, 60));
        headerPanel.setBackground(new Color(240, 240, 245));
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(viewOrder, BorderLayout.EAST);
        return headerPanel;
    }

    private JPanel bottomPanel(String button1Name, String button2Name) {
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(240, 240, 245));
        bottomPanel.setPreferredSize(new Dimension(500, 100));

        //setting the button and customize it with the size and color
        JButton button1 = ButtonPanel(button1Name);
        JButton button2 = ButtonPanel(button2Name);

        // add to the panel
        bottomPanel.add(button1);
        bottomPanel.add(button2);

        return bottomPanel;
    }

    private JButton ButtonPanel(String text) {
        JButton button= new JButton(text);
        button.setBorder(BorderFactory.createLineBorder(Color.RED));
        button.setFont(new Font("Times New Roman",Font.BOLD,14));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.RED);
        button.setPreferredSize(new Dimension(220,40));
        button.setFocusable(false);
        button.addActionListener(this);
        return button;
    }

    private JPanel FoodPanel(String filePath, String name, double prices) {
        JPanel foodPanel = new JPanel();
        foodPanel.setLayout(new BoxLayout(foodPanel,BoxLayout.Y_AXIS));
        foodPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);

        String resourcePath = "/Food-Image-File/" + filePath;
        URL imageUrl = getClass().getResource(resourcePath);
        ImageIcon icon = new ImageIcon(imageUrl);
        Image scaledImage = icon.getImage().getScaledInstance(WIDTH / 4, HEIGHT - 580, Image.SCALE_SMOOTH);
        JLabel thumbnail = new JLabel(new ImageIcon(scaledImage));
        thumbnail.setAlignmentX(Component.CENTER_ALIGNMENT);
        thumbnail.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT - 580));

        JLabel foodName = new JLabel(name);
        foodName.setHorizontalAlignment(SwingConstants.CENTER);
        foodName.setAlignmentX(Component.CENTER_ALIGNMENT);
        foodName.setFont(new Font("Times New Roman",Font.BOLD,10));
        foodPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel foodPrice = new JLabel(String.valueOf(prices));
        foodPrice.setHorizontalAlignment(SwingConstants.CENTER);
        foodPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        foodPrice.setFont(new Font("Times New Roman",Font.BOLD,12));
        foodPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        foodPrice.setForeground(Color.RED);

        container.add(thumbnail,BorderLayout.NORTH);
        container.add(foodName, BorderLayout.CENTER);
        container.add(foodPrice, BorderLayout.SOUTH);
        foodPanel.add(container);

        //store the food name and price in the panel
        foodPanel.putClientProperty("name", name);
        foodPanel.putClientProperty("prices", prices);
        foodPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel clickedPanel = (JPanel) e.getSource();
                food = (String) clickedPanel.getClientProperty("name");
                price = (Double) clickedPanel.getClientProperty("prices");
                orderCart = new OrderItem(food,price,quantity);
                System.out.println(orderCart.toString());
                swapping(orderPanel(icon,name,prices));
//                System.out.println(orderItemList.size());z
            }
        });

        return foodPanel;
    }

    /**
     * Page 4 - the page for user's to confirm the order and increase or decrease the quantity
     */
    private JPanel orderPanel(ImageIcon filePath, String foodName, double foodPrices) {
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setLayout(new BoxLayout(orderPanel,BoxLayout.Y_AXIS));
        orderPanel.setSize(WIDTH, HEIGHT);
        orderPanel.setBackground(Color.WHITE);

        JPanel headerPanels = headPanel("ORDER ITEM");
        headerPanels.setMaximumSize(new Dimension(WIDTH, 60));

        JPanel midPanel = new JPanel();
        midPanel.setBackground(Color.WHITE);
        midPanel.setPreferredSize(new Dimension(WIDTH - 25, 0));

        orderCart = new OrderItem(food,price,quantity);
        JPanel foodOrderPanel = new UnderlinedPanel();
        foodOrderPanel.setBorder(BorderFactory.createEmptyBorder(0, 0,5,0));
        foodOrderPanel.setBackground(Color.WHITE);
        foodOrderPanel.add(getFoodPanel(filePath,foodName,foodPrices));
        midPanel.add(foodOrderPanel);

        JPanel addAndMinusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addAndMinusPanel.setBackground(Color.WHITE);
        JButton addButton = new JButton("+");
        addButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        addButton.setBackground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(25,23));
        addButton.setFocusable(false);
        addButton.addActionListener(this);

        numberLabel = new JLabel("    " + quantity);
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

        JPanel bottomPanels = bottomPanel("BACK","ADD TO CART");
        bottomPanels.setMaximumSize(new Dimension(WIDTH, 100));

        orderPanel.add(headerPanels, BorderLayout.NORTH);
        orderPanel.add(midPanel, BorderLayout.CENTER);
        orderPanel.add(bottomPanels, BorderLayout.SOUTH);

        return orderPanel;
    }

    private JPanel getFoodPanel(ImageIcon foodImages, String foodName, Double price) {
        JPanel foodOrderPanel = new JPanel(new BorderLayout());
        foodOrderPanel.setBackground(Color.WHITE);
        foodOrderPanel.setBorder(BorderFactory.createEmptyBorder(0, 20,0,0));
        foodOrderPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - 650));

        JLabel foodWestImage = new JLabel(foodImages);
        if (foodImages == null) {
            // if the image is == to null, then set the background color to null
            foodWestImage.setBackground(Color.GRAY);
        }
        foodWestImage.setPreferredSize(new Dimension(50,100));

        // setting the label for the food name
        JLabel foodNameLabel = new JLabel(foodName);
        foodNameLabel.setBorder(BorderFactory.createEmptyBorder(2, 10,0,0));
        foodNameLabel.setFont(new Font("Times New Roman",Font.BOLD,14));

        // setting the label for the food price
        JLabel foodPriceLabel = new JLabel("€ " + price);
        foodPriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10,0,0));
        foodPriceLabel.setFont(new Font("Times New Roman",~Font.BOLD,11));

        // set the layout of the border and add the label to the panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
        infoPanel.add(foodNameLabel);
        infoPanel.add(foodPriceLabel);

        // add the image and information label to the food order panel with the location
        foodOrderPanel.add(foodWestImage, BorderLayout.WEST);
        foodOrderPanel.add(infoPanel, BorderLayout.CENTER);

        return foodOrderPanel;
    }

    /**
     * Page 5 - A page that will show all the order items and the prices with specific taxes including the total prices
     */
    private JPanel OrderList() {
        JPanel orderList = new JPanel(new BorderLayout());
        orderList.setSize(WIDTH, HEIGHT);
        orderList.setBackground(Color.WHITE);
        orderList.setOpaque(true);

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Times New Roman", Font.BOLD,12));
        backButton.setBounds(25,20,70,35);
        backButton.setFocusable(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.RED);
        backButton.addActionListener(this);
        orderList.add(backButton);

        JLabel titleLabel = new JLabel("Order list", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD,30));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(80,0,0,0));
        orderList.add(titleLabel, BorderLayout.NORTH);

        JPanel listItemPanel = new JPanel();
        listItemPanel.setLayout(new BoxLayout(listItemPanel, BoxLayout.Y_AXIS));
        listItemPanel.setBackground(Color.WHITE);
        listItemPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 350, 60));


        double totalPriceBeforeTaxes = 0;
        if (orderCartList.isEmpty()) {
            JLabel emptyLabel = new JLabel("THE LIST IS EMPTY AT THE MOMENT!");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listItemPanel.add(emptyLabel);
        } else {
            for (OrderItem orderItem : orderCartList) {
                JPanel itemRow = listLabel(orderItem);
                listItemPanel.add(itemRow);

                //calculating the total price before the taxes
                totalPriceBeforeTaxes += orderItem.getFoodPrice() * orderItem.getQuantity();
            }

            double VAT_Rate = 0.145;
            double totalPriceAfterVATTaxes = totalPriceBeforeTaxes - (totalPriceBeforeTaxes / (1 + VAT_Rate));
            System.out.println("Total Price After VAT Taxes: " + totalPriceAfterVATTaxes);
            JLabel VATLabel = new JLabel("VAT%:  " + String.format("€ %.2f", totalPriceAfterVATTaxes));
            VATLabel.setFont(new Font("Times New Roman", Font.BOLD,13));
            VATLabel.setForeground(Color.GRAY);
            VATLabel.setBorder(BorderFactory.createEmptyBorder(10, 105, 0, 0));
            listItemPanel.add(VATLabel);

            double serviceTaxes = 0.049;
            double totalPriceAfterServiceTaxes = totalPriceBeforeTaxes - (totalPriceBeforeTaxes / (1 + serviceTaxes));
            System.out.println("Total Price After VAT Taxes: " + totalPriceAfterServiceTaxes);
            JLabel serviceTaxesLabel = new JLabel("Service Taxes:  " + String.format("€ %.2f", totalPriceAfterServiceTaxes));
            serviceTaxesLabel.setFont(new Font("Times New Roman", Font.BOLD,13));
            serviceTaxesLabel.setForeground(Color.GRAY);
            serviceTaxesLabel.setBorder(BorderFactory.createEmptyBorder(10, 67, 0, 0));
            listItemPanel.add(serviceTaxesLabel);

            JLabel totalPriceLabel = new JLabel("Total:  " + String.format("€ %.2f", totalPriceBeforeTaxes + totalPriceAfterVATTaxes + totalPriceAfterServiceTaxes));
            totalPriceLabel.setFont(new Font("Times New Roman", Font.BOLD,13));
            totalPriceLabel.setForeground(Color.GRAY);
            totalPriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 115, 0, 0));
            listItemPanel.add(totalPriceLabel);
        }

        JPanel bottomPanel = bottomPanel("CANCEL","PROCESS ORDER");
        orderList.add(listItemPanel, BorderLayout.CENTER);
        orderList.add(bottomPanel, BorderLayout.SOUTH);

        return orderList;
    }

    /**
     * A function method that will return the list of order items with the panel
     */
    private JPanel listLabel(OrderItem orderCart) {
        JPanel itemRow = new JPanel(new FlowLayout());
        itemRow.setBackground(Color.LIGHT_GRAY);

        JLabel listItemLabel = new JLabel(orderCart.getFoodName() + " x " + orderCart.getQuantity());
        listItemLabel.setFont(new Font("Times New Roman", Font.BOLD,13));
        listItemLabel.setForeground(Color.GRAY);
        listItemLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 170));

        JLabel priceLabel = new JLabel(String.format("€ %.2f", orderCart.getFoodPrice() * orderCart.getQuantity()));
        priceLabel.setFont(new Font("Times New Roman", Font.BOLD,13));
        priceLabel.setForeground(Color.GRAY);

        itemRow.add(listItemLabel);
        itemRow.add(priceLabel);

        return itemRow;
    }

    /**
     * Page 6 - the page for user's to pay the bill
     */
    private JPanel PaymentPanel() {
        JPanel paymentPanel = new JPanel(new BorderLayout());
        paymentPanel.setBackground(Color.WHITE);
        paymentPanel.setSize(WIDTH,HEIGHT);

        JLabel paymentMessageLabel = PaymentMessageLabel("The payment it's on the way!");
        paymentPanel.add(paymentMessageLabel, BorderLayout.NORTH);

        JLabel paymentLabel = PaymentImageLabel("payment.png",180, 180);
        paymentPanel.add(paymentLabel, BorderLayout.CENTER);

        return paymentPanel;
    }

    /**
     * Page 7 - it will update once the payment is pay
     *        - So I will be assuming the payment is always be pay here
     */
    private JPanel PaymentSuccessPanel() {
        JPanel paymentSuccessPanel = new JPanel(new BorderLayout());
        paymentSuccessPanel.setBackground(Color.WHITE);
        paymentSuccessPanel.setSize(WIDTH,HEIGHT);

        JLabel paymentMessageLabel = PaymentMessageLabel("Payment it's pay successfully!");
        paymentSuccessPanel.add(paymentMessageLabel, BorderLayout.NORTH);

        JLabel paymentLabel = PaymentImageLabel("payment-check.png",180, 180);
        paymentSuccessPanel.add(paymentLabel, BorderLayout.CENTER);

        return paymentSuccessPanel;
    }

    private JLabel PaymentMessageLabel(String text) {
        JLabel paymentMessageLabel = new JLabel(text);
        paymentMessageLabel.setFont(new Font("Times New Roman", Font.BOLD,30));
        paymentMessageLabel.setBorder(BorderFactory.createEmptyBorder(130,65,0,0));
        paymentMessageLabel.setForeground(Color.GRAY);
        return paymentMessageLabel;
    }

    private JLabel PaymentImageLabel(String imageFile, int x, int y) {
        Image paymentImage = getImage(imageFile,180,180);
        ImageIcon imageIcon = new ImageIcon(paymentImage);
        JLabel paymentLabel = new JLabel(imageIcon);
        paymentLabel.setPreferredSize(new Dimension(180,180));
        paymentLabel.setBorder(BorderFactory.createEmptyBorder(0,0,200,0));
        return paymentLabel;
    }

    /**
     * Page 8 - A page that will display the order number and the type of order
     */
    private JPanel OrderNumberPanel() {
        JPanel orderNumberPanel = new JPanel();
        orderNumberPanel.setSize(WIDTH,HEIGHT);
        orderNumberPanel.setBackground(Color.WHITE);

        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>" +
                "Thank you! Please collect your<BR>number below while wait for preparing food</div><html>");
        messageLabel.setFont(new Font("Times New Roman",~Font.BOLD,22));
        messageLabel.setBackground(new Color(38, 38, 38));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(90,0,0,0));
        orderNumberPanel.add(messageLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(70,0,0,0));

        JLabel orderMessageLabel = new JLabel("Your " + orderItem.getOrderType() + " order number is ");
        orderMessageLabel.setFont(new Font("Times New Roman",~Font.BOLD,22));
        orderMessageLabel.setBackground(new Color(38, 38, 38));
        orderMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(orderMessageLabel);

        JLabel orderNumberLabel = new JLabel(String.valueOf(orderItem.getOrderNumber()));
        orderNumberLabel.setFont(new Font("Times New Roman",Font.BOLD,23));
        orderNumberLabel.setBackground(Color.BLACK);
        orderNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderNumberLabel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        panel.add(orderNumberLabel);

        orderNumberPanel.add(panel);

        return orderNumberPanel;
    }

    private JPanel NotifyCustomerPanel() {
        JPanel notifyCustomerPanel = new JPanel();
        notifyCustomerPanel.setSize(WIDTH,HEIGHT);
        notifyCustomerPanel.setBackground(Color.WHITE);
        JLabel readyLabel = ReadyLabel();
        notifyCustomerPanel.add(readyLabel);
        return notifyCustomerPanel;
    }

    private JLabel ReadyLabel() {
        JLabel readyLabel = new JLabel("<html><div style='text-align: center;'>Your order number of <b>" + orderItem.getOrderNumber() + "</b> is ready to pick up<BR>" +
                "Please pick up at the counter<div><html>");
        readyLabel.setFont(new Font("Times New Roman",~Font.BOLD,22));
        readyLabel.setForeground(new Color(38, 38, 38));
        readyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        readyLabel.setBorder(BorderFactory.createEmptyBorder(250,0,0,0));
        return readyLabel;
    }

    /**
     * A pure method that allow the application to swap and repaint the page
     */
    public void swapping(JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.revalidate();
        this.repaint();
        this.add(logoLabel);
        this.add(messageLabel);
        this.add(messageLabel2);
    }

    /**
     * A function method that allow to insert the image with the side and return image
     */
    private Image getImage(String imageLink, int X, int Y) {
        URL imageUrl = getClass().getClassLoader().getResource(imageLink);
        if (imageUrl == null) {
            System.err.println("Image not found: " + imageLink);
            return null;
        }

        ImageIcon image = new ImageIcon(imageUrl);
        return image.getImage().getScaledInstance(X, Y, Image.SCALE_SMOOTH);
    }

    /**
     * A function method that will random generate the order number from 1 to 999
     */
    private int orderNumberGenerator() {
        int orderNumber = (int)(Math.random() * 999) + 1;
        if (isOrderNumberSame(orderNumber))
            return (int)(Math.random() * 999) + 1;
        else
            return orderNumber;
    }

    /**
     * A function method that check the duplicate value
     */
    private boolean isOrderNumberSame(int orderNumber) {
        for (OrderItem item : orderCartList) {
            if (item.getOrderNumber() == orderNumber) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            swapping(OrderType());
            System.out.println("Switched to Food Menu!");
        } else if (e.getActionCommand().equals("CANCEL")) {
            //once the user cancel the order, all the item on the list should be clear
            orderCartList.removeAll(orderCartList);
            swapping(startPanel());
        } else if (e.getActionCommand().equals("CONFIRM")) {
            swapping(OrderList());
            System.out.println("Switched to Order List");
        } else if (e.getActionCommand().equals("BACK")) {
            swapping(CategoryPanel());
            System.out.println("Switched back to Food Menu!");
        } else if (e.getActionCommand().equals("GO BACK")) {
            swapping(CategoryPanel());
        } else if (e.getActionCommand().equals("ADD TO CART")) {
            swapping(CategoryPanel());
            orderCart = new OrderItem(category,typeOption, food, price, quantity);
            orderCartList.add(orderCart);
            viewOrder.setText("Total Items x " + orderCartList.size());
            System.out.println("DEBUG: \n" + orderCart.toString());

            //after add to the cart set the quantity back to 0
            quantity = 1;
            System.out.println("The food is been added: " + orderCart.toString());
        } else if (e.getActionCommand().equals("+")) {
            quantity++;
            numberLabel.setText("    " + quantity);
        } else if (e.getActionCommand().equals("-")) {
            if (quantity <= 1)
                quantity = 1;
            else
                quantity--;

            numberLabel.setText("    " + quantity);
        } else if (e.getActionCommand().equals("PROCESS ORDER")) {
            Restaurant restaurant = null;
            int orderNumber = orderNumberGenerator();

            // transforming the order item from the cart to the new list for waiter and chef to handle the order
            for (OrderItem oI: orderCartList) {
                orderItem = new OrderItem(orderNumber, oI.getOrderType(), category, oI.getFoodName(), oI.getFoodPrice(), oI.getQuantity());
                restaurant = new Restaurant(orderItem);
                restaurant.processOrder();
            }

            int time = 2000;
            swapping(PaymentPanel());
            ProcessTransformPanel(PaymentSuccessPanel(),time);
            ProcessTransformPanel(OrderNumberPanel(),time * 2);
            ProcessTransformPanel(NotifyCustomerPanel(),time * 4);
            ProcessTransformPanel(startPanel(),time * 6);
        }
    }

    private void ProcessTransformPanel(JPanel panel, int time) {
        Timer timer = new Timer(time, e -> swapping(panel));
        timer.setRepeats(false);
        timer.start();
    }

    public static void main(String[] args) {
        new App();
    }
}

/**
 * A class that allow to set the background image for the panel
 */
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String filePath) {
        try {
            backgroundImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundImage != null) {
            System.out.println("DEBUG: the image is not null");
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            System.out.println("DEBUG: the image is null");
        }
    }
}

/**
 * A class that will allow to draw an underline after each panel
 */
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