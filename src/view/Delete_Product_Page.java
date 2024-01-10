package view;

import controller.Global;
import controller.StateManager;

import java.util.Scanner;

public class Delete_Product_Page implements State {
    @Override
    public void onEnter() {
        Global.server.connectProductTable("products");
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println("Input the id of the product you want to delete");
        System.out.print("ID: ");
        int product_id = scanner.nextInt();

        if(!Global.server.testIfProductExists(String.valueOf(product_id))){
            return;
        }
        Global.server.deleteProduct(product_id);

        System.out.println("--------------------------------------------");
        StateManager.setState("manage"); // return to the manage product page
    }

    @Override
    public void onExit() {

    }
}
