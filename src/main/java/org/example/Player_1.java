package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player_1 {

    public static void main(String[] args) {
        clientStart();
    }


    protected static String HOST = "127.0.0.1";
    protected static int PORT = 8989;

    protected static String lastCity;
    protected static String inputCity;

    protected static void clientStart() {
        try (Socket clientSocket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            lastCity = in.readLine();
            if (lastCity.equals("???")) {
                System.out.println("Вы первый игрок\nВведите название города: ");
            } else {
                System.out.println("Последний город: " + lastCity + "\nВведите новый город");
            }
            try (Scanner scanner = new Scanner(System.in)) {
                inputCity = scanner.nextLine();
                out.println(inputCity);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
