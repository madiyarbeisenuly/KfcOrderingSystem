package com.kfc.db;

import com.kfc.model.Order;
import com.kfc.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDAO {

    public static int saveOrder(Order order, int userId) {
        String orderSql = "INSERT INTO orders (user_id, total, status) VALUES (?, ?, ?)";
        String itemSql = "INSERT INTO order_items (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {

            orderStmt.setInt(1, userId);
            orderStmt.setDouble(2, order.getTotal());
            orderStmt.setString(3, "NEW");
            orderStmt.executeUpdate();

            ResultSet rs = orderStmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

            try (PreparedStatement itemStmt = conn.prepareStatement(itemSql)) {
                for (Product p : order.getItems()) {
                    itemStmt.setInt(1, generatedId);
                    itemStmt.setString(2, p.getName());
                    itemStmt.setInt(3, 1);
                    itemStmt.setDouble(4, p.getPrice());
                    itemStmt.addBatch();
                }
                itemStmt.executeBatch();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }
}