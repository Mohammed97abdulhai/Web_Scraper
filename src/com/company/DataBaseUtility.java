package com.company;

import java.sql.*;

public class DataBaseUtility {



    private static Connection connect() throws ClassNotFoundException {
        // SQLite connection string
        String url = "jdbc:mysql://localhost:3306/articles_db";
        Connection conn = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void selectAll() throws ClassNotFoundException {
        String sql = "SELECT * FROM article";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("title") +  "\t" +
                        rs.getString("category"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(int id,String title, String details, String category) throws ClassNotFoundException {
        String sql = "INSERT INTO article(id,title, details, category) VALUES(?,?,?,?)";

        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2, title);
            pstmt.setString(3, details);
            pstmt.setString(4, category);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}