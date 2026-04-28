package com.kfc.server;

import com.kfc.db.OrderDAO;
import com.kfc.model.Order;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OrderHandler implements Runnable {
    private Socket socket;

    public OrderHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            Order order = (Order) in.readObject();
            System.out.println("Получен заказ: " + order);

            int userId = 1;
            OrderDAO.saveOrder(order, userId);

            out.writeObject("Заказ принят");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}