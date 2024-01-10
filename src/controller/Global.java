package controller;

import model.Admin;
import model.Customer;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Global {
    public static Customer customer;
    public static Admin admin;
    // Add more variables as needed
    public static SQL server;
    public static Product currentProduct;
    public static ArrayList<Product> searchResult;
    public static ArrayList<Product> cart;

    public static HashMap<Integer, Product> cart_list;
//    public static boolean buyNow = false;
    public static boolean buyNow;

    static {
        try {
            server = new SQL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}