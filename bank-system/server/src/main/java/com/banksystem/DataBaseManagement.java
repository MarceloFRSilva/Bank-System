package com.banksystem;

import java.sql.*;

public class DataBaseManagement {

    private static String user = "root";
    private static String passwordDB = "password";

    public static boolean loginUser(String email, String password){
        System.out.println(email + "  " + password);
        String url = "jdbc:mysql://localhost:3306/bank_system?bank_system=false";

        String query = "SELECT idNum FROM User WHERE email = ? AND password = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, passwordDB);
            // create the mysql insert preparedstatement
            PreparedStatement statement =con.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery(query);
            System.out.println(rs.next());
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static int createNewUser(String first_name, String last_name, String email, String phone_number, String password){
        String url = "jdbc:mysql://localhost:3306/bank_system?bank_system=false";

        String query = "INSERT INTO User (idNum, password, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DriverManager.getConnection(url, user, passwordDB);

            int accountNumber = generateRandom(con);
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt     (1, accountNumber);
            preparedStmt.setString  (2, password);
            preparedStmt.setString  (3, first_name);
            preparedStmt.setString  (4, last_name);
            preparedStmt.setString  (5, email);
            preparedStmt.setInt     (6, Integer.parseInt(phone_number));

            // execute the preparedstatement
            preparedStmt.execute();

            con.close();
            return accountNumber;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;  //Error
        }
    }

    private static int generateRandom(Connection con){
        System.out.println("Generate new account number.");
        int upperBound = 1000000000;
        int lowerBound = 100000000;
        int random_int = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
        while(true) {
            try {
                if (!checkIfExistsAccountNumber(random_int, con)) break;
            } catch (SQLException throwables) {
                continue;
            }
            random_int = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
        }
        return random_int;
    }

    public static boolean checkIfExistsAccountNumber(int accountNumber, Connection con) throws SQLException {

        String query = "SELECT * FROM User WHERE idNum = ?";
        try {
            // create the mysql insert preparedstatement
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, accountNumber);
            ResultSet rs = statement.executeQuery(query);
            System.out.println(rs.next());
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}