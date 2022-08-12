package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        String host = "netology.homework";
        int port = 8080;

        try(Socket clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
            ) {
                interaction(reader, in, out, true);
                interaction(reader, in, out, true);
                interaction(reader, in, out, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void interaction(BufferedReader reader, BufferedReader in, PrintWriter out, Boolean clientAnswer)
        throws IOException {
        System.out.println(in.readLine());
        if (clientAnswer) {
            out.println(reader.readLine());
        }
    }
}
