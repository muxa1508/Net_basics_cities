package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

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
//                    System.out.println("Последний отправленный город: " + lastCity);
                    out.println(lastCity);
                    newCity = in.readLine();
                    if (lastCity.equals("???") & !lastCity.equals(newCity)) {
                        out.println("OK");
//                        System.out.println("Новый введенный город: " + newCity);
                        lastCity = newCity;
                    } else {
                        char[] lastCityChars = lastCity.toLowerCase().toCharArray();
                        LinkedList<String> lastCityList = new LinkedList<>();
                        for (char c : lastCityChars) {
                            lastCityList.add(String.valueOf(c));
                        }
                        String lastCityLastChar = lastCityList.getLast();


                        char[] newCityChars = newCity.toLowerCase().toCharArray();
                        LinkedList<String> newCityList = new LinkedList<>();
                        for (char c : newCityChars) {
                            newCityList.add(String.valueOf(c));
                        }
                        String newCityFirstChar = newCityList.getFirst();

//                        System.out.println(lastCityLastChar);
//
//                        System.out.println(newCityFirstChar);

                        if (lastCityLastChar.equals(newCityFirstChar)) {
                            out.println("OK");
//                            System.out.println("Новый введенный город: " + newCity);
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