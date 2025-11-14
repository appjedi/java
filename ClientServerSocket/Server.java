//package app.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

;
/*
* Clicker: A: I really get it    B: No idea what you are talking about
* C: kind of following
*/

public class Server {
    // private Server thisServer;
    public static void main(String[] args) {
        new Server();

    }

    int count = 1;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;

    Server() {
        server = new TheServer();
        server.start();
    }

    public class TheServer extends Thread {

        public void run() {

            try (ServerSocket mysocket = new ServerSocket(5555);) {
                System.out.println("Server is waiting for a client!");

                while (true) {

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    // callback.accept("client has connected to server: " + "client #" + count);
                    System.out.println("client has connected to server: " + "client #" + count);
                    clients.add(c);
                    c.start();

                    count++;

                }
            } // end of try
            catch (Exception e) {
                // callback.accept("Server socket did not launch");
                System.err.println("Server socket did not launch");

            }
        }// end of while
    }

    class ClientThread extends Thread {
        Socket connection;
        int count;
        BufferedReader in;
        PrintWriter out;

        ClientThread(Socket s, int count) {
            this.connection = s;
            this.count = count;
        }

        public void updateClients(String message) {
            for (int i = 0; i < clients.size(); i++) {
                ClientThread t = clients.get(i);

                try {
                    System.out.printf("Send to client # %d - %s\n", i, message);
                    // t.out.println(message);
                    t.message(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void message(String msg) {
            System.out.println("Message from server: " + msg);
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                out = new PrintWriter(connection.getOutputStream(), true);
                connection.setTcpNoDelay(true);

                System.out.println("new client on server: client #" + count);
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    try {
                        System.out.println("received from client: " + count + " sent: " + inputLine);
                        updateClients("client #" + count + " said: " + inputLine);
                        out.println("Server: " + inputLine);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("OOOOPPs...Something wrong with the socket from client: " + count
                                + "....closing down!");
                        updateClients("Client #" + count + " has left the server!");
                        clients.remove(this);
                        break;
                    }
                }
            } // end of run
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Streams not open");
            }

        }
    }// end of client thread
}
