package view;

import controller.Global;
import controller.StateManager;
import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class Order_Details_Page implements State{
    @Override
    public void onEnter() {

    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Your order:");
        ArrayList<Product> cart = new ArrayList<>();

        if (!Global.buyNow) {
            cart = Global.server.getCart();
        } else {
            cart.add(Global.currentProduct);
        }
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.println("Choose payment method");
        System.out.println("1. Apple pay");
        System.out.println("2. WeChat");
        System.out.println("3. Credit card");
        System.out.println("4. Bank Transfer");
        System.out.println("5. Cash-on-delivery");
        HashMap<Integer, String> mp = new HashMap<Integer, String>();
        mp.put(1, "Apple pay");
        mp.put(2, "WeChat");
        mp.put(3, "Credit Card");
        mp.put(4, "Bank Transfer");
        mp.put(5, "Cash-on-delivery");
        int payment = scanner.nextInt();
        scanner.nextLine();
        /*int payment = 10;
        while(payment < 1 || payment > 3) {
            System.out.println("method: ");
            payment = scanner.nextInt();
            scanner.nextLine();
        }*/

        double total = 0;
        boolean flag = false;
        for (Product product : cart) {
            total += product.getQuantity() * product.getPrice();
/*            if (!Global.server.StockCheck(String.valueOf(product.getId()), product.getQuantity())) {
                System.out.println("Not enough stock!");
                flag = true;
            }*/
        }

        if(flag){
            return;
        }

        String orderId = UUID.randomUUID().toString();
        Order finalOrder = new Order(orderId, mp.get(payment), total, address, cart, Global.customer.getEmail());
        //System.out.println(finalOrder.getOrderId());
        int flag1 = Global.server.placeOrder(finalOrder);
        if(flag1 == 1){
            if(!Global.buyNow){
                Global.server.clearShoppingCart();
            }
            System.out.println("Your order has been placed :)");
            System.out.println("Thank you for using our system");
        } else{
            System.out.println("Something went wrong :(");
        }
        Global.buyNow = false;
        StateManager.setState("main");
    }

    @Override
    public void onExit() {
//
    }
}
