package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        startServer();
    }

    protected static String lastCity = "???";
    protected static String newCity;

    protected static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Сервер запущен");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("New connection accepted");
                    out.println(lastCity);
                    newCity = in.readLine();
                    if (lastCity.equals("???") & !lastCity.equals(newCity)) {
                        out.println("OK");
                        lastCity = newCity;
                    } else {
                        char[] lastCityChars = lastCity.toLowerCase().toCharArray();
                        char lastCityLastChar = lastCityChars[lastCityChars.length - 1];

                        char[] newCityChars = newCity.toLowerCase().toCharArray();
                        char newCityFirstChar = newCityChars[0];

                        if (lastCityLastChar == newCityFirstChar) {
                            out.println("OK");
                            lastCity = newCity;
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}