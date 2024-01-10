package view;

import controller.Global;
import controller.StateManager;

import java.util.Scanner;

public class Report_Page implements State{
    @Override
    public void onEnter() {
        Global.server.connectOrderTable("Or_der");
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the Report Center!");
        System.out.println("1. Total sales figure");
/*        System.out.println("2. Review on customer's behaviours: Top5 popular products");*/
        System.out.println("2. Review on consumer behaviours: Top 2 popular payment methods");
        System.out.println("3. Export into a text file");
        System.out.println("4. Back to main page");

        int option = scanner.nextInt();
        if(option == 1) {
            System.out.println(Global.server.queryOrderStats());
        }
/*        else if(option == 2) {
            System.out.println(Global.server.queryTop5Products());
        }*/
        else if(option == 2) {
            System.out.println(Global.server.queryTop2PaymentMethods());
        }

        else if(option == 3) {
            System.out.println("Please enter the file path to save the report:");
            scanner.nextLine();

            String filePath = scanner.nextLine();
            Global.server.exportReports(filePath);
            System.out.println("The report has been generated!");
        }

        else {
            StateManager.setState("admin_main");
        }
    }

    @Override
    public void onExit() {

    }
}