package controller;

import view.*;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        // Create and add states
        State chooseUserState = new ChooseUser_Page();
        State adminState = new Admin_CheckIn_Page();
        State checkInState = new CheckIn_Page();
        State userCenterState = new UserCenter_Page();
        State mainPageState = new Main_Page();
        State productListState = new ProductList_Page();
        State productSearchState = new ProductSearch_Page();
        State productDetailState = new Product_Details_Page();
        State productFilterState = new ProductFilter_Page();
        State productOrderState = new Order_Details_Page();
        State orderHistoryState = new OrderHistory_Page();
        State cartState = new Cart_Page();
        State productPayment = new Payment_Page();
        State selectResult = new Filter_Choose_Page();
        State adminMainPage = new AdminMain_Page();
        State manageProduct = new Manage_Product_Page();
        State addProduct = new Add_Product_Page();
        State deleteProduct = new Delete_Product_Page();
        State report = new Report_Page();
        State updateStockLevel = new Update_Stock_Level_Page();
        State discount = new Discount_Page();

        StateManager.addState("choose", chooseUserState);
        StateManager.addState("admin", adminState);
        StateManager.addState("check", checkInState);
        StateManager.addState("center", userCenterState);
        StateManager.addState("main", mainPageState);
        StateManager.addState("list", productListState);
        StateManager.addState("search", productSearchState);
        StateManager.addState("details", productDetailState);
        StateManager.addState("filter", productFilterState);
        StateManager.addState("order", productOrderState);
        StateManager.addState("history", orderHistoryState);
        StateManager.addState("cart", cartState);
        StateManager.addState("payment", productPayment);
        StateManager.addState("select", selectResult);
        StateManager.addState("update", updateStockLevel);
        StateManager.addState("admin_main", adminMainPage);
        StateManager.addState("manage", manageProduct);
        StateManager.addState("add", addProduct);
        StateManager.addState("delete", deleteProduct);
        StateManager.addState("report", report);
        StateManager.addState("discount", discount);
        // Set the initial state
        StateManager.setState("choose");

        // Run the program
        StateManager.run();
    }
}