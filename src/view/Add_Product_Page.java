package view;

import controller.Global;
import controller.StateManager;
import model.Product;

import java.util.Scanner;

public class Add_Product_Page implements State{
    @Override
    public void onEnter() {

    }

    @Override
    public void run(Scanner scanner) {
        System.out.println("Input the id of the product you want to add");
        System.out.print("ID: ");
        int product_id = scanner.nextInt();

        scanner.nextLine();
        if (Global.server.testIfProductExists(String.valueOf(product_id))){
            return;
        }
        System.out.println("Input the name");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.println("Input the price");
        System.out.print("Price: ");
        Double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Input the stock");
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Input the description");
        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.println("Input the specification");
        System.out.print("Specification: ");
        String specification = scanner.nextLine();

        System.out.println("Input the brand");
        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        System.out.println("Input the category");
        System.out.print("Category: ");
        String category = scanner.nextLine();


        Product newProduct = new Product(product_id, name, price, stock, 0,description, specification, brand, category);
        Global.server.insertProduct(newProduct);

        System.out.println("--------------------------------------------");
        StateManager.setState("manage");
    }

    @Override
    public void onExit() {

    }
}