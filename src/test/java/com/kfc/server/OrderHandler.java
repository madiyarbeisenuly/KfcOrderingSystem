package com.kfc.server;

import java.io.*;
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

            Object order = in.readObject();
            System.out.println("Получен заказ: " + order);

            // Сохраним заказ в БД позже
            out.writeObject("Заказ принят");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}