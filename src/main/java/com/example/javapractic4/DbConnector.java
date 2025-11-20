package com.example.javapractic4;

import tables.*;

import java.io.File;
import java.sql.*;
import java.util.*;

public class DbConnector {
    public Connection c;

    public boolean Conn() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db1.db");

            // ВКЛЮЧИТЬ ПОДДЕРЖКУ ВНЕШНИХ КЛЮЧЕЙ
            try (Statement stmt = c.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON");
                System.out.println("Внешние ключи включены");
            }

            return true;
        } catch (Exception ex) {
            System.out.println("Ошибка подключения: " + ex.getMessage());
            return false;
        }
    }

    public void CloseDB() throws ClassNotFoundException, SQLException {
        if (c != null) {
            c.close();
            System.out.println("Соединения закрыты");
        }
    }

    public void DelDB(Object T, String TableName) throws SQLException {
        switch (TableName) {
            case "Customers":
                Statement statmt1 = c.createStatement();
                Customers customers = (Customers) T;
                String q1 = "delete from customers where customer_id = " + customers.getCustomerId();
                statmt1.executeUpdate(q1);
                statmt1.close();
                break;

            case "Orders":
                Statement statmt2 = c.createStatement();
                Orders orders = (Orders) T;
                String q2 = "delete from orders where order_id = " + orders.getOrderId();
                statmt2.executeUpdate(q2);
                statmt2.close();
                break;

            case "Categories":
                Statement statmt3 = c.createStatement();
                Categories categories = (Categories) T;
                String q3 = "delete from categories where category_id = " + categories.getCategoryId();
                statmt3.executeUpdate(q3);
                statmt3.close();
                break;

            case "Furniture":
                Statement statmt4 = c.createStatement();
                Furniture furniture = (Furniture) T;
                String q4 = "delete from furniture where furniture_id = " + furniture.getFurnitureId();
                statmt4.executeUpdate(q4);
                statmt4.close();
                break;

            case "Order_items":
                Statement statmt5 = c.createStatement();
                OrderItems orderItems = (OrderItems) T;
                String q5 = "delete from order_items where item_id = " + orderItems.getItemId();
                statmt5.executeUpdate(q5);
                statmt5.close();
                break;
        }
    }

    public void WriteDB(Object T, String TableName) throws SQLException {
        switch (TableName) {
            case "Customers":
                Statement statmt1 = c.createStatement();
                Customers customers = (Customers) T;
                String q1 = "insert into customers (customer_id, name, phone, adress) values (" +
                        customers.getCustomerId() + ", '" + customers.getName() + "', '" +
                        customers.getPhone() + "', '" + customers.getAdress() + "')";
                statmt1.executeUpdate(q1);
                statmt1.close();
                break;

            case "Orders":
                Statement statmt2 = c.createStatement();
                Orders orders = (Orders) T;
                String q2 = "insert into orders (order_id, customer_id, total, status) values (" +
                        orders.getOrderId() + ", " + orders.getCustomerId() + ", " +
                        orders.getTotal() + ", '" + orders.getStatus() + "')";
                statmt2.executeUpdate(q2);
                statmt2.close();
                break;

            case "Categories":
                Statement statmt3 = c.createStatement();
                Categories categories = (Categories) T;
                String q3 = "insert into categories (category_id, name) values (" +
                        categories.getCategoryId() + ", '" + categories.getName() + "')";
                statmt3.executeUpdate(q3);
                statmt3.close();
                break;

            case "Furniture":
                Statement statmt4 = c.createStatement();
                Furniture furniture = (Furniture) T;
                String q4 = "insert into furniture (furniture_id, name, price, category_id) values (" +
                        furniture.getFurnitureId() + ", '" + furniture.getName() + "', " +
                        furniture.getPrice() + ", " + furniture.getCategoryId() + ")";
                statmt4.executeUpdate(q4);
                statmt4.close();
                break;

            case "Order_items":
                Statement statmt5 = c.createStatement();
                OrderItems orderItems = (OrderItems) T;
                String q5 = "insert into order_items (item_id, order_id, furniture_id, count) values (" +
                        orderItems.getItemId() + ", " + orderItems.getOrderId() + ", " +
                        orderItems.getFurnitureId() + ", " + orderItems.getCount() + ")";
                statmt5.executeUpdate(q5);
                statmt5.close();
                break;
        }
    }

    public void UpdateDB(Object T, String TableName) throws SQLException {
        switch (TableName) {
            case "Customers":
                Statement statmt1 = c.createStatement();
                Customers customers = (Customers) T;
                String q1 = "update customers set name='" + customers.getName() + "', phone='" +
                        customers.getPhone() + "', adress='" + customers.getAdress() +
                        "' where customer_id=" + customers.getCustomerId();
                statmt1.executeUpdate(q1);
                statmt1.close();
                break;

            case "Orders":
                Statement statmt2 = c.createStatement();
                Orders orders = (Orders) T;
                String q2 = "update orders set customer_id=" + orders.getCustomerId() + ", total=" +
                        orders.getTotal() + ", status='" + orders.getStatus() +
                        "' where order_id=" + orders.getOrderId();
                statmt2.executeUpdate(q2);
                statmt2.close();
                break;

            case "Categories":
                Statement statmt3 = c.createStatement();
                Categories categories = (Categories) T;
                String q3 = "update categories set name='" + categories.getName() +
                        "' where category_id=" + categories.getCategoryId();
                statmt3.executeUpdate(q3);
                statmt3.close();
                break;

            case "Furniture":
                Statement statmt4 = c.createStatement();
                Furniture furniture = (Furniture) T;
                String q4 = "update furniture set name='" + furniture.getName() + "', price=" +
                        furniture.getPrice() + ", category_id=" + furniture.getCategoryId() +
                        " where furniture_id=" + furniture.getFurnitureId();
                statmt4.executeUpdate(q4);
                statmt4.close();
                break;

            case "Order_items":
                Statement statmt5 = c.createStatement();
                OrderItems orderItems = (OrderItems) T;
                String q5 = "update order_items set order_id=" + orderItems.getOrderId() +
                        ", furniture_id=" + orderItems.getFurnitureId() + ", count=" +
                        orderItems.getCount() + " where item_id=" + orderItems.getItemId();
                statmt5.executeUpdate(q5);
                statmt5.close();
                break;
        }
    }

    public List<? extends Object> ReadDB(String TableName) throws ClassNotFoundException, SQLException {
        switch (TableName) {
            case "Customers":
                try {
                    Statement statmt;
                    ResultSet resSet;
                    statmt = c.createStatement();
                    resSet = statmt.executeQuery("select * from customers");
                    List<Customers> customers = new ArrayList<Customers>();

                    while (resSet.next()) {
                        int Id = resSet.getInt("customer_id");
                        String Name = resSet.getString("name");
                        String Phone = resSet.getString("phone");
                        String Adress = resSet.getString("adress");

                        customers.add(new Customers(Id, Name, Phone, Adress));
                    }

                    resSet.close();
                    statmt.close();
                    return customers;
                } catch (Exception e) {
                    System.out.println("Ошибка при чтении Customers: " + e.getMessage());
                    return new ArrayList<>();
                }

            case "Orders":
                try {
                    Statement statmt;
                    ResultSet resSet;
                    statmt = c.createStatement();
                    resSet = statmt.executeQuery("select * from orders");
                    List<Orders> orders = new ArrayList<Orders>();

                    while (resSet.next()) {
                        int orderId = resSet.getInt("order_id");
                        int customerId = resSet.getInt("customer_id");
                        double total = resSet.getDouble("total");
                        String status = resSet.getString("status");

                        orders.add(new Orders(orderId, customerId, total, status));
                    }

                    resSet.close();
                    statmt.close();
                    return orders;
                } catch (Exception e) {
                    System.out.println("Ошибка при чтении Orders: " + e.getMessage());
                    return new ArrayList<>();
                }

            case "Categories":
                try {
                    Statement statmt;
                    ResultSet resSet;
                    statmt = c.createStatement();
                    resSet = statmt.executeQuery("select * from categories");
                    List<Categories> categories = new ArrayList<Categories>();

                    while (resSet.next()) {
                        int categoryId = resSet.getInt("category_id");
                        String name = resSet.getString("name");

                        categories.add(new Categories(categoryId, name));
                    }

                    resSet.close();
                    statmt.close();
                    return categories;
                } catch (Exception e) {
                    System.out.println("Ошибка при чтении Categories: " + e.getMessage());
                    return new ArrayList<>();
                }

            case "Furniture":
                try {
                    Statement statmt;
                    ResultSet resSet;
                    statmt = c.createStatement();
                    resSet = statmt.executeQuery("select * from furniture");
                    List<Furniture> furniture = new ArrayList<Furniture>();

                    while (resSet.next()) {
                        int furnitureId = resSet.getInt("furniture_id");
                        String name = resSet.getString("name");
                        double price = resSet.getDouble("price");
                        int categoryId = resSet.getInt("category_id");

                        furniture.add(new Furniture(furnitureId, name, price, categoryId));
                    }

                    resSet.close();
                    statmt.close();
                    return furniture;
                } catch (Exception e) {
                    System.out.println("Ошибка при чтении Furniture: " + e.getMessage());
                    return new ArrayList<>();
                }

            case "Order_items":
                try {
                    Statement statmt;
                    ResultSet resSet;
                    statmt = c.createStatement();
                    resSet = statmt.executeQuery("select * from order_items");
                    List<OrderItems> orderItems = new ArrayList<OrderItems>();

                    while (resSet.next()) {
                        int itemId = resSet.getInt("item_id");
                        int orderId = resSet.getInt("order_id");
                        int furnitureId = resSet.getInt("furniture_id");
                        int count = resSet.getInt("count");

                        orderItems.add(new OrderItems(itemId, orderId, furnitureId, count));
                    }

                    resSet.close();
                    statmt.close();
                    return orderItems;
                } catch (Exception e) {
                    System.out.println("Ошибка при чтении Order_items: " + e.getMessage());
                    return new ArrayList<>();
                }
        }
        return null;
    }
}
