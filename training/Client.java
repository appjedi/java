 

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        //String host = "hills.ccsf.edu";
        String host = "localhost";

        int port = 9876;
        Socket socket = new Socket(host, port);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String userInput;
        System.out.println("Type messages. Type 'exit' to quit.");
        while ((userInput = keyboard.readLine()) != null) {
            out.println(userInput);
            String response = in.readLine();
            System.out.println("Server: " + response);
            if ("exit".equalsIgnoreCase(userInput)) break;
        }

        in.close();
        out.close();
        socket.close();
        System.out.println("Connection closed.");
    }
} 
