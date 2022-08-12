package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8080;

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while(true) {
                Socket clientSocket = serverSocket.accept();

                try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted");

                    final String name;
                    final String answer;

                    name = getString(in, out, "Write your name");
                    answer = getString(in, out, "Are you child? (yes/no)");

                    if (answer.equals("yes")) {
                        out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    } else {
                        out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a " +
                            "good working day!", name));
                    }
                }
            }
        }
    }

    private static String getString(BufferedReader in, PrintWriter out, String message) throws IOException {
        out.println(message);
        return in.readLine();
    }
}
