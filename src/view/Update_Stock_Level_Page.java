package view;

import controller.Global;
import controller.StateManager;

import java.util.Scanner;

public class Update_Stock_Level_Page implements State{
    @Override
    public void onEnter() {
        Global.server.connectProductTable("products");
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println("Input the id of the product you want to modify the stock level");
        System.out.print("ID: ");
        int product_id = scanner.nextInt();

        if(!Global.server.testIfProductExists(String.valueOf(product_id))){
            return;
        }

        int former_stock = Global.server.searchProduct(Integer.toString(product_id)).getStock();
        System.out.println("The Stock level now: " + former_stock);

        System.out.println("Input the stock level");
        System.out.print("Stock Level: ");

        int latter_stock = scanner.nextInt();

        Global.server.updateStockLevel(product_id, latter_stock);

        System.out.println("--------------------------------------------");
        StateManager.setState("manage"); // return to the manage product page

    }

    @Override
    public void onExit() {

    }
}