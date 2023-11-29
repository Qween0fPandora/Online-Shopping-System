import java.io.Console;
import java.sql.*;
import oracle.jdbc.driver.*;
import java.util.Calendar;

public class Application {

    private static String selSQL;
    private static Connection conn;

    public static void checkInPage() {
        System.out.println("Welcome to the Check-in Page");
        System.out.println("Please select an option:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Quit");

        // make the user input a number
        Console console = System.console();
        String option = console.readLine();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password")) {
            switch (option) {
                case "1":
                    try {
                        registerAccount(conn);
                        break;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                case "2":
                    logIn(conn);
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("Error connecting to the database: " + ex);
        }


        System.out.println();
        conn.close();
    }

    private static void registerAccount(Connection conn) throws SQLException {
        try {
            Console console = System.console();
            System.out.println("Registering account: \n");
            Statement stmt = conn.createStatement();
            System.out.println("Please enter a username:");
            String regName = console.readLine();
            String selSQL = "SELECT customer_name FROM customer WHERE customer_name = '" + regName + "'";
            ResultSet rset = stmt.executeQuery(selSQL);
            if (rset.next()) {  // Check whether the username already exists
                throw new SQLException("Username already exists. Registration failed.");
            } else {
                userCenter(regName);
                logIn(conn);
            }
        } catch (SQLException ex) {
            System.out.println("Error registering account: " + ex);
        }
    }


    private static void logIn(Connection conn) {
        try {
            Console console = System.console();
            System.out.println("Logging in: \n");
            Statement stmt = conn.createStatement();
            System.out.println("Please enter your username:");
            String logName = console.readLine();
            String selSQL = "SELECT customer_name FROM customer WHERE customer_name = '" + logName + "'";
            ResultSet rset = stmt.executeQuery(selSQL);
            if (rset.next()) {  // Check whether the username already exists
                userCenter(logName);
                mainPage();
            } else {
                throw new SQLException("Username does not exist. Login failed.");
            }
        } catch (SQLException ex) {
            System.out.println("Error logging in: " + ex);
        }
    }


    private static void userCenter(String regName) throws SQLException {
        Console console = System.console();
        System.out.println("Welcome to the User Center");
        System.out.println("Please enter your password:");
        String userpw = console.readLine();
        System.out.println("Please enter your email:");
        String email = console.readLine();
        System.out.println("Please enter your address:");
        String address = console.readLine();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password")) {
            // search for the username in the database
            String selectQuery = "SELECT regName FROM customer WHERE regName = ?";
            String sql = "INSERT INTO customer VALUES (userpw, email, address) WHERE customer_name = regName";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, regName);
                ResultSet resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    // update the user details
                    String updateQuery = "UPDATE customer SET userpw = ?, email = ?, address = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setString(1, userpw);
                        updateStatement.setString(2, email);
                        updateStatement.setString(3, address);
                        updateStatement.executeUpdate();
                        System.out.println("Update successful!");
                    }
                } else {
                    System.out.println("No matching regName found.");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error updating user details: " + ex);
        }
    }

    private static void mainPage() {
        System.out.println("Welcome to the Main Page");
        System.out.println("Please select an option:");
        System.out.println("1. List all the products");
        System.out.println("2. Search for a product");
        System.out.println("3. Filter products");
        System.out.println("4. Go to the shopping cart");
        System.out.println("5. To to the order history");
        System.out.println("6. Go to the user center");
        System.out.println("7. Quit");

        Console console = System.console();
        String option = console.readLine();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password")) {
            switch (option) {
                case "1":
                    listProducts(conn);
                    break;
                case "2":
                    searchProduct(conn);
                    break;
                case "3":
                    filterProduct(conn);
                    break;
                case "4":
                    shoppingCart(conn);
                    break;
                case "5":
                    orderHistory(conn);
                    break;
                case "6":
                    userCenter(String.valueOf(conn));
                    break;
                case "7":
                    System.out.println("Thank you for using our system!");
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
        } catch (SQLException ex) {
            System.out.println("Error connecting to the database: " + ex);
        }

    }

    private static void orderHistory(Connection conn) {
    }

    private static void shoppingCart(Connection conn) {
    }

    private static void filterProduct(Connection conn) {
        System.out.println("Please select an option:");
        System.out.println("1. Filter by price range");
        System.out.println("2. Filter by category");
        System.out.println("3. Filer by brand");
        System.out.println("4. Go back to the main page");
        System.out.println("5. Quit");
        Console console = System.console();
        String option = console.readLine();
        switch (option) {
            case "1":
                System.out.println("Please enter the minimum price: ");
                String minPrice = console.readLine();
                System.out.println("Please enter the maximum price: ");
                String maxPrice = console.readLine();
                displayProductByPrice(conn, Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
                break;
            case "2":
                System.out.println("Please enter the category: ");
                String category = console.readLine();
                displayProductByCategory(conn, category);
                break;
            case "3":
                System.out.println("Please enter the brand: ");
                String brand = console.readLine();
                displayProductByBrand(conn, brand);
                break;
            case "4":
                mainPage();
                break;
            case "5":
                System.out.println("Thank you for using our system!");
                System.exit(0);
                break;
        }
    }

    private static void displayProductByBrand(Connection conn, String brand) {
        String sql = "SELECT product_id, product_name, product_price, product_description FROM product WHERE product_brand = '" + brand + "'" +
                "ORDER BY product_price ASC";
        int offset = 0;
        int limit = 20;
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)
                        + " " + resultSet.getString(2)
                        + " " + resultSet.getString(3)
                        + " " + resultSet.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println("Error displaying product details: " + ex);
        }

    }

    private static void displayProductByCategory(Connection conn, String category) {
        String sql = "SELECT product_id, product_name, product_price, product_description FROM product WHERE product_category = '" + category + "'" +
                "ORDER BY product_price ASC";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)
                        + " " + resultSet.getString(2)
                        + " " + resultSet.getString(3)
                        + " " + resultSet.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println("Error displaying product details: " + ex);
        }

    }

    private static void displayProductByPrice(Connection conn, int min, int max) {
        String sql = "SELECT product_id, product_name, product_price, product_description FROM product WHERE product_price BETWEEN " + min + " AND " + max +
                "ORDER BY product_price ASC";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)
                        + " " + resultSet.getString(2)
                        + " " + resultSet.getString(3)
                        + " " + resultSet.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println("Error displaying product details: " + ex);
        }
    }

    private static void searchProduct(Connection conn) {
        System.out.println("Please select an option:");
        System.out.println("1. Search by product id"); // 这里还没确定是search by name还是id 要看看product detail怎么写
        System.out.println("2. Go back to the main page");
        System.out.println("3. Quit");
        Console console = System.console();
        String option = console.readLine();
        switch (option) {
            case "1":
                System.out.println("Please enter the product id: ");
                String product_id = console.readLine();
                displayProductDetails(conn, Integer.parseInt(product_id));
                break;
            case "2":
                mainPage();
                break;
            case "3":
                System.out.println("Thank you for using our system!");
                System.exit(0);
                break;
        }
    }

    private static void listProducts(Connection conn) {
        try {
            int offset = 0; // the offset of the current page
            int limit = 20; // the number of products displayed on each page
            boolean quit = false; // the flag to quit the program

            while (!quit) {
                offset = displayProductPage(conn, offset, limit);

                System.out.println();
                System.out.println("Please select an option:");
                System.out.println("1. View product details");
                System.out.println("2. Go back to the main page");
                System.out.println("3. Quit");
                System.out.println("4. View next page");
                System.out.print("Option: ");

                Console console = System.console();
                String option = console.readLine();

                switch (option) {
                    case "1":
                        System.out.print("Enter product ID: ");
                        int productId = Integer.parseInt(console.readLine());
                        displayProductDetails(conn, productId);
                        break;
                    case "2":
                        mainPage();
                        break;
                    case "3":
                        quit = true;
                        break;
                    case "4":
                        offset = displayProductPage(conn, offset, limit);
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }

            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error listing products: " + ex);
        }
    }

    private static int displayProductPage(Connection conn, int offset, int limit) throws SQLException {
        Statement stmt = conn.createStatement();
        String selSQL = "SELECT product_id, product_name, product_price, product_description, product_category, product_brand FROM product LIMIT " + limit + " OFFSET " + offset;
        ResultSet rset = stmt.executeQuery(selSQL);
        int count = 0; // record the number of products displayed on the current page

        while (rset.next() && count <= limit){
            System.out.println(rset.getInt(1)
                    + " " + rset.getString(2)
                    + " " + rset.getString(3)
                    + " " + rset.getString(4));
            count++;
        }

        return offset + count; // return the offset of the next page
    }

    private static void displayProductDetails(Connection conn, int productId) {
//        try {
//            Statement stmt = conn.createStatement();
//            String selSQL = "SELECT product_id, product_name, product_price, product_description FROM product WHERE product_id = " + productId;
//            ResultSet rset = stmt.executeQuery(selSQL);
//
//            if (rset.next()) {
//                System.out.println("Product ID: " + rset.getInt(1));
//                System.out.println("Product Name: " + rset.getString(2));
//                System.out.println("Price: " + rset.getString(3));
//                System.out.println("Description: " + rset.getString(4));
//            } else {
//                System.out.println("Product not found.");
//            }
//
//            System.out.println();
//        } catch (SQLException ex) {
//            System.out.println("Error displaying product details: " + ex);
//        }
    }





    public static boolean check_whether_available(int product_id, int quantity) throws SQLException {
        String query = "SELECT stock_qty FROM product WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, product_id);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    int stockQty = res.getInt("stock_qty");
                    if (stockQty > quantity) {
                        return true;
                    }
                    System.out.println(product_id + " is insuffient now. The remain: " + stockQty); // insuffient
                    return false;
                } else {
                    System.out.println(product_id + " not available right now.");
                    return false; // not exist
                }
            }
        }
    }

    public static void get_basic_details(int product_id) throws SQLException {
        String query = "SELECT * FROM product WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, product_id);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("name: " + res.getString("name") + '\n');
                    sb.append("description: " + res.getString("description") + '\n');
                    sb.append("brand: " + res.getString("brand") + '\n');
                    sb.append("category: " + res.getString("category") + '\n');
                    sb.append("price: " + res.getString("price") + '\n');
                    sb.append("specifications: " + res.getString("specifications") + '\n');
                    System.out.println(sb.toString());
                } else {
                    System.out.println("No product found with id " + product_id);
                }
            }
        }
    }

    public static void select(int product_id, int customer_id, int quantity) throws SQLException {
        if (!check_whether_available(product_id, quantity))
            return;
        String sql = "INSERT INTO shopping_cart (product_id, customer_id, quantity) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, product_id);
        pstmt.setInt(2, customer_id);
        pstmt.setInt(3, quantity);
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Product with id " + product_id + " has been added to your cart!");
        }
    }

    public static void buy(int product_id, int customer_id, int quantity, String payment_type, String delivery_city,
                           String delivery_details) throws SQLException {
        try {
            // Disable auto-commit mode
            conn.setAutoCommit(false);
            if (!check_whether_available(product_id, quantity))
                return;
            // Decrease the quantity
            String updateStockSql = "UPDATE product SET stock_qty = stock_qty - ? WHERE product_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateStockSql)) {
                pstmt.setInt(1, quantity);
                pstmt.setInt(2, product_id);
                pstmt.executeUpdate();
            }

            // Create the order
            String sql = "INSERT INTO or_der (create_time, payment_type, status, delivery_city, delivery_details, customer_id, product_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                pstmt.setDate(1, date); // current date
                pstmt.setString(2, payment_type);
                pstmt.setString(3, "PENPAY"); // initial status
                pstmt.setString(4, delivery_city);
                pstmt.setString(5, delivery_details);
                pstmt.setInt(6, customer_id);
                pstmt.setInt(7, product_id);
                pstmt.executeUpdate();
            }

            // Commit the transaction
            conn.commit();
            System.out.println("Order has been placed successfully!");
        } catch (SQLException e) {
            // rollback the transaction
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            throw e; // re-throw the original exception
        } finally {
            // Restore auto-commit mode
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
    }
    public static void  paymentConfirmation (){
        System.out.println("Please select an option:");
        System.out.println("1. Confirm payment (UPDATE):");
        System.out.println("2. Go back to the main page");
        System.out.println("3. Quit");
        System.out.print("Option: ");
        Console console = System.console();
        String option = console.readLine();
        switch (option) {
            case "1":
                System.out.println(/*payment_type*/);
                return;
            case "2":
                mainPage();
                return;
            case "3":
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
    }
    public static void customerReview (){

        return;
    }

    public void main(String args[]) throws SQLException, RuntimeException {
            Console console = System.console();
            System.out.print("Enter your username: "); // Your Oracle ID with double quote
            String username = console.readLine(); // e.g. "98765432d"
            System.out.print("Enter your password: "); // Password of your Oracle Account
            char[] password = console.readPassword();
            String pwd = String.valueOf(password);
            // Connection
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            OracleConnection conn =
                    (OracleConnection)DriverManager.getConnection(
                            "jdbc:oracle:thin:@studora.comp.polyu.edu.hk:1521:dbms",username,pwd);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT EMPNO, ENAME, JOB FROM EMP");
            while (rset.next())
            {
                System.out.println(rset.getInt(1)
                        + " " + rset.getString(2)
                        + " " + rset.getString(3));
            }
            System.out.println();
            conn.close();
//        ResultSet rset = null;
//        ResultSet rset1 = null;
//        ResultSet rset2 = null;
//
//        String option = "";
//        String command = "";
//        String selSQL = "";
//        String selSQL1 = "";
//        String insertSQL = "";

            checkInPage();

    }
}
