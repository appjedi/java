import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
	Socket socketClient;

	BufferedReader in;
	PrintWriter out;
	BufferedReader keyboard;

	public static void main(String[] args) throws IOException {
		// String host = "hills.ccsf.edu";

		Client c = new Client();
		c.start();
	}

	public void run() {
		try {
			socketClient = new Socket("127.0.0.1", 5555);
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(socketClient.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			// socketClient.setTcpNoDelay(true);

			String userInput;
			System.out.println("Type messages. Type 'exit' to quit.");
			while ((userInput = keyboard.readLine()) != null) {
				System.out.println("userInput: " + userInput);
				out.println(userInput);
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
	public void message(String msg)
	{
		System.out.println("Message from server: "+msg);
	}
	public void send(String data) {

		try {
			System.out.println("Send to server: "+data);
			out.println(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
