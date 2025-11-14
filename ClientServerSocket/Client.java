//package app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    Socket socketClient;

    BufferedReader in;
    PrintWriter out;

    public static void main(String[] args) throws IOException {
        // String host = "hills.ccsf.edu";
        Client c = new Client();
        c.start();
    }

    public void run() {
        try {
            socketClient = new Socket("127.0.0.1", 5555);
            // out = new PrintWriter(socketClient.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            // socketClient.setTcpNoDelay(true);
            Keyboard keyboard = new Keyboard(socketClient);
            keyboard.start();
            while (true) {
                try {
                    String message = in.readLine();
                    System.out.printf("Respopnse from server: %s\n", message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void message(String msg) {
        System.out.println("Message from server: " + msg);
    }

    public void send(String data) {

        try {
            System.out.println("Send to server: " + data);
            out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Keyboard extends Thread {
        Socket socketClient;

        public Keyboard() {
        }

        BufferedReader keyboard;

        public Keyboard(Socket socketClient) {
            this.socketClient = socketClient;
        }

        public void run() {
            try {
                out = new PrintWriter(socketClient.getOutputStream(), true);

                keyboard = new BufferedReader(new InputStreamReader(System.in));
                String userInput;
                System.out.println("Type messages. Type 'exit' to quit.");

                while ((userInput = keyboard.readLine()) != null) {
                    if (userInput.equals("exit")) {
                        System.exit(0);
                    }
                    System.out.println("userInput: " + userInput);
                    out.println(userInput);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}
