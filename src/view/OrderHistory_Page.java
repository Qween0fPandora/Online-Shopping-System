package view;

import controller.Global;
import controller.StateManager;
import model.Order;

import java.util.Scanner;
public class OrderHistory_Page implements State{
    @Override
    public void onEnter() {

    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the Order History Page!");
        System.out.println("Please choose one of the following options:");
        System.out.println("1. View Order History");
        System.out.println("2. Go back to the main page");
        System.out.println("3. Quit");
        String option = scanner.nextLine();
        if(option.equals("1")){
/*           Global.server.getOrder().get(0).getOrderId();*/
            Order.printOrderList(Global.server.getOrder());
        }
        else if(option.equals("2")){
            StateManager.setState("main");
        }
        else{
            System.out.println("Thank you for using our system!");
            System.exit(0);
        }
    }

    @Override
    public void onExit() {

    }

}
