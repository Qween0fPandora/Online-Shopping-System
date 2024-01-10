package controller;

import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
public class SQL
{
    Statement statement;
    Connection con;
    String tableCostumerName = "CUSTOMER";
    String tableProductName = "PRODUCTS";
    String tableOrderName = "OR_DER";
    String tableCartName = "SHOPPING_CART";
    String tableAdminName = "ADMIN";
    String tableReviewName = "REVIEW";


    public SQL() throws SQLException
    {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        //String url = "jdbc:oracle:thin:@localhost:5000:dbms";
        String url = "jdbc:oracle:thin:@studora.comp.polyu.edu.hk:1521:dbms";
        con = DriverManager.getConnection (url, "\"22097304d\"", "bazbwmzp") ;
        statement = con.createStatement();
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your username: ");    // Your Oracle ID with double quote
//        String username = scanner.nextLine();         // e.g. "98765432d"
//        System.out.print("Enter your password: ");    // Password of your Oracle Account
//        String password = scanner.nextLine();
//        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

//        String url = "jdbc:oracle:thin:localhost:5000:dbms";
//        con = DriverManager.getConnection (url, username, password) ;
//        statement = con.createStatement();
    }
    public void connectCostumerTable(String name)
    {
        tableCostumerName = name;
    }

    public void connectAdminTable(String admin)
    {
        tableAdminName = admin;
    }

    public void insertCostumerTable(Customer customer) {
        try {
            String insertSQL = "INSERT INTO " + tableCostumerName + " VALUES ('" +
                    customer.getEmail() + "', '" +
                    customer.getName() + "', '" +
                    customer.getPassword() + "', '" +
                    customer.getDetails() + "', '" +
                    customer.getCity() + "', '" +
                    customer.getCountry() + "') ";

//            System.out.println(insertSQL);

            statement.executeUpdate(insertSQL);
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode();
            String sqlState = e.getSQLState();
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
    }

/*    public Boolean whetherCustomerExist(String email) {
        String querySQL = "SELECT * FROM customer WHERE customer_email = \'" + email + "\'";
        try {
            ResultSet rset = statement.executeQuery(querySQL);
            if (rset.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error querying customer table: " + ex.getMessage());
        }
        return null;
    }*/

    public Customer queryCostumerTable(String input_email)
    {
        String querySQL = "SELECT * FROM " + tableCostumerName + " WHERE customer_email = \'" + input_email + "\'";

        try {
//             System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while (rs.next()) {
                String email = rs.getString (1);
                String password = rs.getString (2);
                String name = rs.getString(3);
                String details = rs.getString(4);
                String city = rs.getString(5);
                String country = rs.getString(6);

                return new Customer(email,name,password,details, city, country);
            }
        } catch (SQLException e)
        {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            // something more meaningful than a print would be good
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return new Customer("-1", "-1", "-1", "-1", "-1", "-1");
    }

    public Admin queryAdminTable(String input_email)
    {
        String querySQL = "SELECT * FROM " + tableAdminName + " WHERE admin_email = \'" + input_email + "\'";

        try {
//            System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while (rs.next()) {
                String email = rs.getString (1);
                String password = rs.getString (2);

                return new Admin(email,password);
            }
        } catch (SQLException e)
        {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            // something more meaningful than a print would be good
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return new Admin("-1", "-1");
    }

    public void connectProductTable(String newName)
    {
        tableProductName = newName;
    }

    public void insertProduct(Product product) {
        String insertSQL = "INSERT INTO " + tableProductName + " VALUES (" +
                product.getId() + ", '" +
                product.getName() + "', " +
                product.getPrice() + ", " +
                product.getStock() + ", '" +
                product.getDescription() + "', '" +
                product.getSpecs() + "', '" +
                product.getBrand() + "', '" +
                product.getCategory() + "') ";

        try {
            int rowsInserted = statement.executeUpdate(insertSQL);
            if (rowsInserted > 0) {
                System.out.println("productID: " + product.getId() + ".  has been appended to the inventory successfully!");
            }
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
    }

    public void deleteProduct(int product_id) {
        String deleteSQL = "DELETE FROM " + tableProductName + " WHERE product_id = " + product_id;

        try {
            int rowsDeleted = statement.executeUpdate(deleteSQL);
            if (rowsDeleted > 0) {
                System.out.println("productID: " + product_id + ". The product was deleted from the inventory successfully!");
            } else {
                System.out.println("No product found with the given id.");
            }
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
    }

    public ArrayList<Product> getAllProduct()
    {
        ArrayList<Product> res = new ArrayList<Product>();
        String querySQL = "SELECT * FROM " + tableProductName;
        try {
            // System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString (2);
                double price = rs.getDouble(3);
                int stock = rs.getInt(4);
                String description = rs.getString(5);
                String specs = rs.getString(6);
                String brand = rs.getString(7);
                String category = rs.getString(8);

                res.add(new Product(id,name,price, stock, 0, description, specs, brand, category));
            }
        } catch (SQLException e)
        {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            // something more meaningful than a print would be good
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return res;
    }

    public Product searchProduct(String pid)
    {
        String querySQL = "SELECT * FROM " + tableProductName + " WHERE product_id  = \'" + pid + "\' ";

        try {
            statement = con.createStatement();
            // System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString (2);
                double price = rs.getDouble(3);
                int stock = rs.getInt(4);
                String description = rs.getString(5);
                String specs = rs.getString(6);
                String brand = rs.getString(7);
                String category = rs.getString(8);

                return new Product(id,name,price, stock, 0, description, specs, brand, category);
            }
        } catch (SQLException e)
        {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return new Product(-1,"",-1, -1, 0,"", "", "", "");
    }

    public ArrayList<Product> filterProduct(String p_category, String p_brand, double p_mn, double p_mx)
    {
        ArrayList<Product> res = new ArrayList<>();
        String queryCategory = p_category.isEmpty() ? "" :  "category = \'" + p_category + "\' AND ";
        String queryBrand = p_brand.isEmpty() ? "" : "brand = \'" + p_brand + "\' AND ";
        String queryPrice = "price BETWEEN " + p_mn + " AND " + p_mx + " ";
        String querySQL = "SELECT * FROM " + tableProductName + " WHERE " + queryCategory + queryBrand + queryPrice;
        System.out.println(querySQL);
        try {
            statement = con.createStatement();
            // System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString (2);
                double price = rs.getDouble(3);
                int stock = rs.getInt(4);
                String description = rs.getString(5);
                String specs = rs.getString(6);
                String brand = rs.getString(7);
                String category = rs.getString(8);

                res.add(new Product(id,name,price, stock, 0, description, specs, brand, category));
            }
        } catch (SQLException e)
        {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return res;
    }

    public void close() throws SQLException
    {
        con.close();
    }


    public ArrayList<Product> getCart() {

        ArrayList<Product> products = getAllProduct();

        HashMap<Integer, Product> mp = new HashMap<>();

        for (Product p : products) {
            mp.put(p.getId(), p);
        }

        ArrayList<Product> res = new ArrayList<>();
        String querySQL = "SELECT * FROM " + tableCartName + " WHERE customer_email = \'" + Global.customer.getEmail() + "\' ";
        try {
//             System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while (rs.next()) {
                String email = rs.getString(1);
                int id = rs.getInt(2);
                int quantity = rs.getInt(3);
                Product cProduct = mp.get(id);
                cProduct.setQuantity(quantity);
                res.add(cProduct);
            }
        } catch (SQLException e)
        {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            // something more meaningful than a print would be good
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return res;
    }

/*    public int insertToCart(int productId, int quantity) {
        try {
            String insertSQL = "INSERT INTO " + tableCartName + " VALUES ('" +
                    Global.customer.getEmail() + "', '" +
                    productId + "', '" +
                    quantity + "') ";

            statement.executeUpdate(insertSQL);
            return 1;
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode();
            String sqlState = e.getSQLState();
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return 0;
    }*/

    public int insertToCart(int productId, int quantity) {
        // Check if the product is already in the cart
        String checkSQL = "SELECT quantity FROM " + tableCartName +
                " WHERE customer_email = '" + Global.customer.getEmail() +
                "' AND product_id = " + productId;

        try {
            ResultSet rs = statement.executeQuery(checkSQL);
            if (rs.next()) {
                // Product is in the cart, update the quantity
                int existingQuantity = rs.getInt("quantity");
                int newQuantity = existingQuantity + quantity;

                String updateSQL = "UPDATE " + tableCartName +
                        " SET quantity = " + newQuantity +
                        " WHERE customer_email = '" + Global.customer.getEmail() +
                        "' AND product_id = " + productId;

                statement.executeUpdate(updateSQL);
            } else {
                // Product is not in the cart, insert a new entry
                String insertSQL = "INSERT INTO " + tableCartName +
                        " (customer_email, product_id, quantity) VALUES ('" +
                        Global.customer.getEmail() + "', " +
                        productId + ", " +
                        quantity + ")";

                statement.executeUpdate(insertSQL);
            }
            return 1; // Success
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error properly
            return 0; // Error
        }
    }

    public void updateStockLevel(int product_id, int new_stock) {
        String updateSQL = "UPDATE " + tableProductName + " SET stock_qty = " + new_stock + " WHERE product_id = " + product_id;

        try {
            int rowsUpdated = statement.executeUpdate(updateSQL);
            if (rowsUpdated > 0) {
                System.out.println("productID: " + product_id + ". The stock level has been updated successfully!");
            } else {
                System.out.println("No product found with the given id.");
            }
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
    }

/*    public int changeCartProduct(String email, int productId, int newQuantity) {
        try {
            String updateSQL = "UPDATE " + tableCartName +
                    " SET quantity = " + newQuantity +
                    " WHERE customer_email = \'" + email +
                    "\' AND product_id = " + productId;

            int rowsAffected = statement.executeUpdate(updateSQL);
            return rowsAffected;
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode();
            String sqlState = e.getSQLState();
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return 0;
    }*/

    public int changeCartProduct(String email, int productId, int newQuantity) {
        try {
            String insertSQL = "UPDATE " + tableCartName +
                    " SET customer_email = \'" + email +
                    "\', product_id = " + productId +
                    ", quantity = " + newQuantity +
                    " WHERE customer_email = \'" + email +
                    "\' AND product_id = \'" + productId + "\'";

            statement.executeUpdate(insertSQL);
            return 1;
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode();
            String sqlState = e.getSQLState();
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return 0;
    }

    public int placeOrder(Order order) {
        try {
            for (int i=0;i<order.getProductList().size();i++) {
                Product product = order.getProductList().get(i);
                String insertSQL = "INSERT INTO \"" + tableOrderName + "\" VALUES ('" +
                        order.getOrderId() + "', '" +
                        order.getPaymentMethod() + "', '" +
                        order.getDeliveryAddress() + "\', \'" +
                        Global.customer.getEmail() + "\'," +
                        product.getQuantity() + "," +
                        order.getPaymentAmount() + "," +
                        product.getId() + ") ";
                statement.executeUpdate(insertSQL);
                String updateSQL = "UPDATE " + tableProductName + " SET STOCK_QTY = " + (product.getStock() - product.getQuantity()) + " WHERE product_id = " + product.getId();

                statement.executeUpdate(updateSQL);
                product.setStock(product.getStock() - product.getQuantity());
            }

            return 1;
        } catch (SQLException e) {
            String sqlCode = e.getMessage();
            String sqlState = e.getSQLState();
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return 0;
    }

    public ArrayList<Order> getOrder(){
        ArrayList<Order> res = new ArrayList<>();
        try{
            statement = con.createStatement();
            String customer_email = Global.customer.getEmail();
            String querySQL = "SELECT * FROM \"" + tableOrderName + "\" WHERE customer_email = \'" + customer_email + "\'";

            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while (rs.next()) {
                String orderId = rs.getString(1);
                String paymentMethod = rs.getString(2);
                String deliveryAddress = rs.getString(3);
                String customerEmail = rs.getString(4);
                int quantity = rs.getInt(5);
                double price = rs.getDouble(6);
                int productId = rs.getInt(7);
                Product product = searchProduct(String.valueOf(productId));
                product.setQuantity(quantity);
                ArrayList<Product> productList = new ArrayList<>();
                productList.add(product);
                res.add(new Order(orderId, paymentMethod, price, deliveryAddress, productList, customerEmail));
            }
        } catch (SQLException e){
            int sqlCode = e.getErrorCode();
            String sqlState = e.getSQLState();
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return res;
    }

    public ArrayList<Review> getReview(int productId) {
        ArrayList<Review> res = new ArrayList<>();
        try {
            String querySQL = "SELECT * FROM " + tableReviewName + " WHERE product_id = " + productId;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while (rs.next()) {
                String name = rs.getString(1);
                String review = rs.getString(2);
                int id = rs.getInt(3);
                res.add(new Review(name, review, id));
            }
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode();
            String sqlState = e.getSQLState();
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return  res;
    }


    public int insertReview(Review review) {
        try {
            String insertSQL = "INSERT INTO " + tableReviewName + " VALUES ('" +
                    review.getName() + "', '" +
                    review.getReview() + "', " +
                    review.getProductId() + ") ";
            statement.executeUpdate(insertSQL);
            return 1;
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode();
            String sqlState = e.getSQLState();
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
        return 0;
    }

    public String queryOrderStats() {
        // Query for total paymentAmount
        String querySumSQL = "SELECT SUM(total_price) FROM " + tableOrderName;
        StringBuilder sb = new StringBuilder();
        try {
            // Query for total paymentAmount
            ResultSet rsSum = statement.executeQuery(querySumSQL);
            if (rsSum.next()) {
                double totalPaymentAmount = rsSum.getDouble(1);
                sb.append("Total sales figure: " + totalPaymentAmount+ '\n');
            }

        } catch (SQLException e) {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            sb.append("Code: " + sqlCode + "  sqlState: " + sqlState + '\n');
        }
        return sb.toString();
    }

    public Boolean testIfProductExists(String ProductID){
        try {
            String selSQL = "SELECT * FROM Products WHERE product_id = " + ProductID;
            ResultSet rset = statement.executeQuery(selSQL);
            if (rset.next()) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e) {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }return null;
    }

    //12.1
/*    public String queryTop5Products() {
        String queryProductsSQL = "SELECT * FROM (SELECT product_id, COUNT(*) as count FROM or_der GROUP BY product_id ORDER BY count DESC) WHERE ROWNUM <= 5";
        StringBuilder sb = new StringBuilder();
        try {
            // Query for top 5 popular products
            ResultSet rsProducts = statement.executeQuery(queryProductsSQL);
            sb.append("Top 5 popular products:" + '\n');
            while (rsProducts.next()) {
                String productId = rsProducts.getString(1);
                int count = rsProducts.getInt(2);
                String subquery = "SELECT product_name FROM products WHERE product_id = " +productId;
                ResultSet subrs = statement.executeQuery(subquery);
                String productName = subrs.getString(1);
                sb.append("Product ID: " + productId + " Product Name: " + productName + " ordered " + count + " times" + '\n');
            }
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE
            sb.append("Code: " + sqlCode + "  sqlState: " + sqlState + '\n');
        }
        return sb.toString();
    }*/

    //12.1
    public String queryTop2PaymentMethods() {
        String queryMethodsSQL = "SELECT * FROM (\n" +
                "                  SELECT payment, COUNT(*) as count" +
                "                  FROM or_der" +
                "                  GROUP BY payment" +
                "                  ORDER BY count DESC" +
                "              ) WHERE ROWNUM <= 2";
        StringBuilder sb = new StringBuilder();

        try {
            // Query for top 2 popular payment methods
            ResultSet rsMethods = statement.executeQuery(queryMethodsSQL);
            sb.append("Top 2 popular payment methods:" + '\n');
            while (rsMethods.next()) {
                String payment = rsMethods.getString(1);
                int count = rsMethods.getInt(2);
                sb.append("Payment method: " + payment + " used " + count + " times" + '\n');
            }
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE
            sb.append("Code: " + sqlCode + "  sqlState: " + sqlState + '\n');
        }
        return sb.toString();
    }

    public String queryTop3CitiesWithMostCustomers() {
        String queryCitiesSQL = "SELECT * FROM (SELECT address_city, COUNT(DISTINCT customer_email) as count FROM " + tableCostumerName + " GROUP BY address_city ORDER BY COUNT(DISTINCT customer_email) DESC) WHERE ROWNUM <= 3";
        StringBuilder sb = new StringBuilder();

        try {
            // Query for top 3 cities with most customers
            ResultSet rsCities = statement.executeQuery(queryCitiesSQL);
            sb.append("Top 3 cities with the most customers:" + '\n');
            while (rsCities.next()) {
                String city = rsCities.getString("address_city");
                int count = rsCities.getInt("count");
                sb.append("City: " + city + " has " + count + " customers." + '\n');
            }
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE
            sb.append("Code: " + sqlCode + "  sqlState: " + sqlState + '\n');
        }
        return sb.toString();
    }

    public String queryTop3BestSellingProducts() {
        String queryProductsSQL = "SELECT * FROM (SELECT o.product_id, p.product_name, COUNT(*) as count FROM "
                + tableOrderName + " o JOIN Products p ON o.product_id = p.product_id GROUP BY o.product_id, p.product_name ORDER BY COUNT(*) DESC) WHERE ROWNUM <= 3";

        StringBuilder sb = new StringBuilder();

        try {
            // Query for top 3 best selling products
            ResultSet rsProducts = statement.executeQuery(queryProductsSQL);
            sb.append("Top 3 best selling products:" + '\n');
            while (rsProducts.next()) {
                String product_Id = rsProducts.getString(1);
                String product_Name = rsProducts.getString(2);
                int count = rsProducts.getInt(3);
                sb.append("Product ID: " + product_Id + " Product Name: " + product_Name + " sold " + count + " times" + '\n');
            }
        } catch (SQLException e) {
            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE
            sb.append("Code: " + sqlCode + "  sqlState: " + sqlState + '\n');
        }
        return sb.toString();
    }


    public void exportReports(String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");

            // Query and write the total amount
            writer.println("Total sales amount:");
            // Assume you have a method that returns the total amount as a String
            writer.println(Global.server.queryOrderStats());

            // Query and write top 5 popular products
/*            writer.println("Top 5 popular products:");
            writer.println(Global.server.queryTop5Products());*/

            // Query and write top 2 popular payment methods
            writer.println("Top 2 popular payment methods:");
            writer.println(Global.server.queryTop2PaymentMethods());

            writer.println("Top 3 cities with the most customers");
            writer.println(Global.server.queryTop3CitiesWithMostCustomers());

            writer.println("Best-sellers (Top 3): ");
            writer.println(Global.server.queryTop3BestSellingProducts());

            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }
    }

    public boolean StockCheck(String pid, int quantity) {
        try {
            String querySQL = "SELECT * FROM " + tableProductName + " WHERE product_id  = \'" + pid + "\' ";
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
            while(rs.next()) {
                int remain = rs.getInt("stock_qty");
                if(quantity > remain)
                    return false; // Not enough stock
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public void clearShoppingCart() {
        try {
            String updateSQL = "DELETE FROM " + tableCartName + " WHERE customer_email = \'" + Global.customer.getEmail() + "\' ";
            statement.executeQuery(updateSQL);
        } catch (SQLException ex) {
            System.out.println("Error clearing cart: " + ex.getMessage());
        }
    }

    public void removeItemsFromShoppingCart(Customer customer, Integer productID) {
        try {
            String updateSQL = "DELETE FROM shopping_cart WHERE customer_email = '" + customer.getEmail() + "'";
            statement.executeQuery(updateSQL);
        } catch (SQLException ex) {
            System.out.println("Error removing items from cart: " + ex.getMessage());
        }
    }

        public void connectOrderTable(String order) {
        tableOrderName = order;
    }

}