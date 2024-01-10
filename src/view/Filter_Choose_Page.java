package view;

import controller.Global;
import controller.StateManager;
import model.Product;

import java.util.Scanner;

public class Filter_Choose_Page implements State {
    @Override
    public void onEnter() {

    }

    @Override
    public void run(Scanner scanner) {
        Product.printProductList(Global.searchResult);

        System.out.println("Input the id of the product you want to choose");
        System.out.print("ID: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        for (Product product : Global.searchResult) {
            if(product.getId() == option) {
                Global.currentProduct = product;
                StateManager.setState("details");
            }
        }
    }

    @Override
    public void onExit() {

    }
}
