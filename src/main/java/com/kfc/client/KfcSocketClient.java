package com.kfc.client;

import com.kfc.model.Order;
import com.kfc.model.Product;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class KfcSocketClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        List<Product> cart = new ArrayList<>();
        cart.add(new Product(1, "Шефбургер Острый", 1850, "Бургеры", "..."));
        cart.add(new Product(2, "Боксмастер Комбо", 3700, "Комбо", "..."));

        Order order = new Order(cart);

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(order);
            String response = (String) in.readObject();
            System.out.println("Ответ сервера: " + response);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}