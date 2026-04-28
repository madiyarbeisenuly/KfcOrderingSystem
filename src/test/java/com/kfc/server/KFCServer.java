package com.kfc.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KFCServer {
    private static final int PORT = 9999;
    private static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("KFC Server запущен на порту " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новый клиент подключился");
                pool.execute(new OrderHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}